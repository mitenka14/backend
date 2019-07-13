package kekstarter.controllers;

import kekstarter.dto.ErrorsDto;
import kekstarter.dto.UsersDto;
import kekstarter.services.UsersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final UsersService usersService;

    public AuthController( UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/registration")
    public ErrorsDto addUser (@RequestBody UsersDto usersDto) {
        return usersService.addUser(usersDto);
    }
}
