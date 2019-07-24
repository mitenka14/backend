package kekstarter.controllers;

import kekstarter.dto.ResponseTextDto;
import kekstarter.dto.UsersDto;
import kekstarter.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    private final UsersService usersService;

    @PostMapping("registration")
    public ResponseTextDto addUser (@RequestBody UsersDto usersDto) {
        return usersService.addUser(usersDto);
    }

    @GetMapping("activation/{code}")
    public void activation(@PathVariable String code) {
        usersService.activateUser(code);
    }

    @GetMapping("{id}")
    public UsersDto findUserByUsername(final @PathVariable long id){
        return usersService.getUserById(id);
    }
}
