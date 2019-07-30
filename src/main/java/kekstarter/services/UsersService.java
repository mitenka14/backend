package kekstarter.services;

import kekstarter.dto.ResponseTextDto;
import kekstarter.dto.UsersDto;
import kekstarter.models.User;

import java.util.List;

public interface UsersService {
    ResponseTextDto addUser(UsersDto usersDto);

    void activateUser(String code);

    UsersDto getUserById(long id);

    User findUserById(long id);

    User getUserFromToken();

    void deleteUserById(long id);

    List<UsersDto> getUsers();

    ResponseTextDto editUser(UsersDto usersDto, long id);

    void makeAdmin(long id);

    ResponseTextDto blockUser(long id);
}
