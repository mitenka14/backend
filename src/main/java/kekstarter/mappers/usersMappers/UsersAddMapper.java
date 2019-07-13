package kekstarter.mappers.usersMappers;

import kekstarter.dto.UsersDto;
import kekstarter.models.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Component
public class UsersAddMapper {

    public Users makeModel(UsersDto usersDto) {
        Users user = new Users();
        user.setFirstName(usersDto.getFirstName());
        user.setSecondName(usersDto.getSecondName());
        user.setUsername(usersDto.getUsername());
        user.setPassword(usersDto.getPassword());
        user.setEmail(usersDto.getEmail());
        return user;
    }

}
