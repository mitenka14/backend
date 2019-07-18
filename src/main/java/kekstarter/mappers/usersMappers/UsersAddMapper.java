package kekstarter.mappers.usersMappers;

import kekstarter.dto.UsersDto;
import kekstarter.models.User;
import org.springframework.stereotype.Component;

@Component
public class UsersAddMapper {

    public User makeModel(UsersDto usersDto) {
        User user = new User();
        user.setFirstName(usersDto.getFirstName());
        user.setSecondName(usersDto.getSecondName());
        user.setUsername(usersDto.getUsername());
        user.setPassword(usersDto.getPassword());
        user.setEmail(usersDto.getEmail());
        return user;
    }

}
