package kekstarter.services.impl;

import kekstarter.dto.ResponseTextDto;
import kekstarter.dto.UsersDto;
import kekstarter.mappers.usersMappers.UsersEditMapper;
import kekstarter.mappers.usersMappers.UsersInfoMapper;
import kekstarter.models.Campaign;
import kekstarter.models.User;
import kekstarter.models.UserRole;
import kekstarter.repositories.CampaignsRepo;
import kekstarter.repositories.UsersRepo;
import kekstarter.responseMessage.ResponseMessages;
import kekstarter.services.AuthenticationsService;
import kekstarter.services.MailService;
import kekstarter.services.TagsService;
import kekstarter.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final MailService mailService;
    private final UsersRepo usersRepo;
    private final UsersEditMapper usersEditMapper;
    private final PasswordEncoder passwordEncoder;
    private final UsersInfoMapper usersInfoMapper;
    private final AuthenticationsService authenticationsService;
    private final TagsService tagsService;
    private final CampaignsRepo campaignsRepo;



    @Override
    public ResponseTextDto addUser(UsersDto usersDto){
        User user = new User();
        user = usersEditMapper.makeModel(usersDto, user);
        if (usersRepo.existsByUsernameAndEmail(user.getUsername(), user.getEmail())) {
            return new ResponseTextDto(ResponseMessages.ALREADY_EXISTS);
        }
        user.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        newActivationCode(user);
        usersRepo.save(user);
        mailService.send(user.getEmail(),  user.getActivationCode());
        return new ResponseTextDto(ResponseMessages.SUCCESS);
    }

    @Override
    public void activateUser(String code) {
        User user = usersRepo.findByActivationCode(code);
            user.setBlocked(false);
            user.setActivationCode(null);
            usersRepo.save(user);
    }

    @Override
    public UsersDto getUserById(long id){
        return usersInfoMapper.makeDto(findUserById(id));
    }

    @Override
    public User findUserById(long id){
        return usersRepo.findById(id);
    }

    @Override
    public User getUserFromToken(){
        return usersRepo.findByUsername(authenticationsService.getName());
    }

    @Override
    public void deleteUserById(long id) {
        User user = usersRepo.findById(id);
        if(user.getRole() == UserRole.ROLE_USER) {
            campaignsRepo.findAllByUser(user).forEach(tagsService::deleteTags);
            usersRepo.delete(user);
        }
    }

    @Override
    public List<UsersDto> getUsers(){
        return usersInfoMapper.makeList(usersRepo.findAll());
    }

    @Override
    public ResponseTextDto editUser(UsersDto usersDto, long id) {
        User editor = usersRepo.findByUsername(authenticationsService.getName());
        if (editor.getRole() != UserRole.ROLE_ADMIN && editor.getId() != id){
            return new ResponseTextDto(ResponseMessages.DONT_HAVE_PERMISSION);
        }
        User user = usersRepo.findById(id);
        if (user.getRole() == UserRole.ROLE_ADMIN && editor.getId()!= id){
            return new ResponseTextDto(ResponseMessages.CANT_BE_EDITED);
        }
        if ((editor.getRole() != UserRole.ROLE_ADMIN || user.getRole() == UserRole.ROLE_ADMIN) && !passwordEncoder.matches(usersDto.getPassword(), user.getPassword())) {
            return new ResponseTextDto(ResponseMessages.INVALID_PASSWORD);
        }
        if(!user.getUsername().equals(usersDto.getUsername()) && usersRepo.existsByUsername(usersDto.getUsername())){
            return new ResponseTextDto(ResponseMessages.ALREADY_EXISTS);
        }
        if(!user.getEmail().equals(usersDto.getEmail()) && usersRepo.existsByEmail(usersDto.getEmail())){
            return new ResponseTextDto(ResponseMessages.ALREADY_EXISTS);
        }
        if (usersDto.getNewPassword()!= null){
            user.setPassword(passwordEncoder.encode(usersDto.getNewPassword()));
        }
        user = usersEditMapper.makeModel(usersDto, user);
        usersRepo.save(user);
        return new ResponseTextDto(ResponseMessages.SUCCESS);
    }

    @Override
    public void makeAdmin(long id) {
        User user = usersRepo.findById(id);
        user.setRole(UserRole.ROLE_ADMIN);
        usersRepo.save(user);
    }

    @Override
    public ResponseTextDto blockUser(long id){
        User user = usersRepo.findById(id);
        if (user.getRole() == UserRole.ROLE_ADMIN){
            return new ResponseTextDto(ResponseMessages.CANT_BE_EDITED);
        }
        if (user.getBlocked() == false){
            user.setBlocked(true);
        }
        else {
            user.setBlocked(false);
        }
        return new ResponseTextDto(ResponseMessages.SUCCESS);
    }

    private void newActivationCode(User user){
        user.setActivationCode(UUID.randomUUID().toString());
    }



}
