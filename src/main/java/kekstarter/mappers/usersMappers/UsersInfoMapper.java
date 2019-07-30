package kekstarter.mappers.usersMappers;

import kekstarter.dto.UsersDto;
import kekstarter.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersInfoMapper {

    public UsersDto makeDto(final User user){
        UsersDto usersDto = new UsersDto();
        usersDto.setId(user.getId());
        usersDto.setUsername(user.getUsername());
        usersDto.setFirstName(user.getFirstName());
        usersDto.setSecondName(user.getSecondName());
        usersDto.setEmail(user.getEmail());
        usersDto.setRole(user.getRole().name());
        usersDto.setBlocked(user.getBlocked());
        return usersDto;
    }

    public List<UsersDto> makeList(List<User> userList) {
        return userList.stream().map(this::makeDto).collect(Collectors.toList());
    }
}
