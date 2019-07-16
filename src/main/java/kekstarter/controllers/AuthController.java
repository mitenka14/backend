package kekstarter.controllers;

import kekstarter.dto.ResponseTextDto;
import kekstarter.dto.UsersDto;
import kekstarter.services.UsersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final UsersService usersService;



    public AuthController( UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/registration")
    public ResponseTextDto addUser (@RequestBody UsersDto usersDto) {
        return usersService.addUser(usersDto);
    }

    @GetMapping("/activation/{code}")
    public void activation(@PathVariable String code) {
        usersService.activateUser(code);
    }
}
