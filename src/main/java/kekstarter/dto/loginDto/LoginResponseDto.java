package kekstarter.dto.loginDto;

import kekstarter.dto.ResponseTextDto;
import kekstarter.models.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private String token;
    private ResponseTextDto responseTextDto;
    private long userId;
    private String username;
    private String userRole;


    public LoginResponseDto(String token, ResponseTextDto responseTextDto, Users user) {
        this.token = token;
        this.responseTextDto = responseTextDto;
        if (user != null) {
            this.userId = user.getId();
            this.username = user.getUsername();
            this.userRole = user.getRole().toString();
        }
    }
}

