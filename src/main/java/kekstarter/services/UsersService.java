package kekstarter.services;

import kekstarter.abbrev.Abbreviation;
import kekstarter.dto.ErrorsDto;
import kekstarter.dto.UsersDto;
import kekstarter.mappers.usersMappers.UsersAddMapper;
import kekstarter.models.Users;
import kekstarter.models.UsersRole;
import kekstarter.repositories.UsersRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UsersService {

    private final  MailService mailService;
    private final UsersRepo usersRepo;
    private final UsersAddMapper usersAddMapper;

    public UsersService(MailService mailService, UsersRepo usersRepo, UsersAddMapper usersAddMapper) {
        this.mailService = mailService;
        this.usersRepo = usersRepo;
        this.usersAddMapper = usersAddMapper;
    }

    public ErrorsDto addUser(UsersDto usersDto){
        Users user = usersAddMapper.makeModel(usersDto);
        if (!this.uniqueUsername(user.getUsername())) {
            return new ErrorsDto(Abbreviation.ERROR, Abbreviation.ERROR_ISNT_UNIQUE_USERNAME);
        }
        if (!this.uniqueEmail(user.getEmail())) {
            return new ErrorsDto(Abbreviation.ERROR, Abbreviation.ERROR_ISNT_UNIQUE_EMAIL);
        }
        setDefaultUser(user);
        usersRepo.save(user);
        mailService.send(user.getEmail(), Abbreviation.SUBJECT_ACTIVATION_CODE, activationMessage(user.getUsername(), user.getActivationCode()));
        return new ErrorsDto(Abbreviation.SUCCESS, Abbreviation.SUCCESS_REGISTRATION);
    }

    private Boolean uniqueEmail(String email) {
        return usersRepo.findByEmail(email) == null;
    }

    private Boolean uniqueUsername(String username) {
        return usersRepo.findByUsername(username) == null;
    }

    private void encoder(Users user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
    }

    private void newActivationCode(Users user){
        user.setActivationCode(UUID.randomUUID().toString());
    }

    private void setDefaultUser(Users user) {
        encoder(user);
        newActivationCode(user);
        user.setActive(false);
        user.setBlocked(false);
        user.setRole(UsersRole.ROLE_USER);

    }

    private String activationMessage(String user, String code){
        return ("Hello, "+user+"!\nWelcome to KEKstarter! Please, visit next link: http://localhost:4200/auth/activate/"+code);
    }
}
