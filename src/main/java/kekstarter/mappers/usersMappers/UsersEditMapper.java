package kekstarter.mappers.usersMappers;

import kekstarter.dto.UsersDto;
import kekstarter.models.User;
import org.springframework.stereotype.Component;

@Component
public class UsersEditMapper {

    public User makeModel(UsersDto usersDto, User user) {
        user.setFirstName(usersDto.getFirstName());
        user.setSecondName(usersDto.getSecondName());
        user.setUsername(usersDto.getUsername());
        user.setEmail(usersDto.getEmail());
        return user;
    }

}
