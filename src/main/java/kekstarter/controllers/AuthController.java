package kekstarter.controllers;

import kekstarter.dto.loginDto.LoginRequestDto;
import kekstarter.dto.ResponseTextDto;
import kekstarter.dto.UsersDto;
import kekstarter.dto.loginDto.LoginResponseDto;
import kekstarter.services.AuthenticationsService;
import kekstarter.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final UsersService usersService;
    private final AuthenticationsService authenticationsService;


    @PostMapping("login")
    public LoginResponseDto login(@RequestBody final LoginRequestDto loginRequestDto){
        return authenticationsService.login(loginRequestDto);
    }

    @PostMapping("registration")
    public ResponseTextDto addUser (@RequestBody UsersDto usersDto) {
        return usersService.addUser(usersDto);
    }

    @GetMapping("activation/{code}")
    public void activation(@PathVariable String code) {
        usersService.activateUser(code);
    }
}
