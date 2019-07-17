package kekstarter.mappers.usersMappers;

import kekstarter.dto.UsersDto;
import kekstarter.models.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersInfoMapper {

    public UsersDto makeDto(final Users user){
        UsersDto usersDto = new UsersDto();
        usersDto.setId(user.getId());
        usersDto.setUsername(user.getUsername());
        usersDto.setFirstName(user.getFirstName());
        usersDto.setSecondName(user.getSecondName());
        return usersDto;
    }
}
