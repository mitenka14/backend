package kekstarter.services;

import kekstarter.mappers.usersMappers.UsersInfoMapper;
import kekstarter.responseMessage.ResponseMessages;
import kekstarter.dto.ResponseTextDto;
import kekstarter.dto.UsersDto;
import kekstarter.mappers.usersMappers.UsersAddMapper;
import kekstarter.models.User;
import kekstarter.repositories.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final MailService mailService;
    private final UsersRepo usersRepo;
    private final UsersAddMapper usersAddMapper;
    private final PasswordEncoder passwordEncoder;
    private final UsersInfoMapper usersInfoMapper;



    public ResponseTextDto addUser(UsersDto usersDto){
        User user = usersAddMapper.makeModel(usersDto);
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

    public UsersDto findUserById(long id){
        User user = usersRepo.findById(id);
        return this.usersInfoMapper.makeDto(user);
    }


    private void newActivationCode(User user){
        user.setActivationCode(UUID.randomUUID().toString());
    }

}
