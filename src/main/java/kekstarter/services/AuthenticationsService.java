package kekstarter.services;

import kekstarter.dto.loginDto.LoginRequestDto;
import kekstarter.dto.loginDto.LoginResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationsService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    @Transactional(readOnly = true)
    String getName();
}
