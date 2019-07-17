package kekstarter.controllers;

import kekstarter.dto.UsersDto;
import kekstarter.mappers.usersMappers.UsersInfoMapper;
import kekstarter.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{username}")
    public UsersDto findUserByUsername(@PathVariable String username){
        return this.usersService.findUserByUsername(username);
    }
}
