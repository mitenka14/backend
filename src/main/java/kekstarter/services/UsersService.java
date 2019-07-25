package kekstarter.services;

import kekstarter.mappers.usersMappers.UsersInfoMapper;
import kekstarter.models.UserRole;
import kekstarter.responseMessage.ResponseMessages;
import kekstarter.dto.ResponseTextDto;
import kekstarter.dto.UsersDto;
import kekstarter.mappers.usersMappers.UsersEditMapper;
import kekstarter.models.User;
import kekstarter.repositories.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final MailService mailService;
    private final UsersRepo usersRepo;
    private final UsersEditMapper usersEditMapper;
    private final PasswordEncoder passwordEncoder;
    private final UsersInfoMapper usersInfoMapper;
    private final AuthenticationsService authenticationsService;



    public ResponseTextDto addUser(UsersDto usersDto){
        User user = usersEditMapper.makeModel(usersDto);
        if (usersRepo.existsByUsernameAndEmail(user.getUsername(), user.getEmail())) {
            return new ResponseTextDto(ResponseMessages.ALREADY_EXISTS);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        newActivationCode(user);
        usersRepo.save(user);
        mailService.send(user.getEmail(),  user.getActivationCode());
        return new ResponseTextDto(ResponseMessages.SUCCESS);
    }

    public void activateUser(String code) {
        User user = usersRepo.findByActivationCode(code);
            user.setBlocked(false);
            user.setActivationCode(null);
            usersRepo.save(user);
    }

    public UsersDto getUserById(long id){
        return usersInfoMapper.makeDto(findUserById(id));
    }

    public User findUserById(long id){
        return usersRepo.findById(id);
    }

    public User getUserFromToken(){
        return usersRepo.findByUsername(authenticationsService.getName());
    }

    public void deleteUserById(long id) {
        User user = usersRepo.findById(id);
        if(user.getRole() == UserRole.ROLE_USER) {
            usersRepo.deleteById(id);
        }
    }

    public List<UsersDto> getUsers(){
        return usersInfoMapper.makeList(usersRepo.findAll());
    }

    public ResponseTextDto editUser(UsersDto usersDto, long id) {
        User editor = usersRepo.findByUsername(authenticationsService.getName());
        if (editor.getRole() != UserRole.ROLE_ADMIN || editor.getId() != id){
            return new ResponseTextDto(ResponseMessages.DONT_HAVE_PERMISSION);
        }
//        if (usersRepo.existsByUsernameAndEmail(usersDto.getUsername(), usersDto.getEmail())) {
//            return new ResponseTextDto(ResponseMessages.ALREADY_EXISTS);
//        }
        usersDto.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        User user = usersRepo.findById(id);
        if (user.getPassword() != usersDto.getPassword()) {
            return new ResponseTextDto(ResponseMessages.PASSWORDS_DONT_MATCH);
        }
        user = usersEditMapper.editUser(usersDto, user);
        usersRepo.save(user);
        return new ResponseTextDto(ResponseMessages.SUCCESS);
    }


    private void newActivationCode(User user){
        user.setActivationCode(UUID.randomUUID().toString());
    }

}
