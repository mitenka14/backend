package kekstarter.controllers;

import kekstarter.dto.loginDto.LoginRequestDto;
import kekstarter.dto.loginDto.LoginResponseDto;
import kekstarter.services.AuthenticationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationsService authenticationsService;


    @PostMapping("login")
    public LoginResponseDto login(@RequestBody final LoginRequestDto loginRequestDto){
        return authenticationsService.login(loginRequestDto);
    }


}
