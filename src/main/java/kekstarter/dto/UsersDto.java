package kekstarter.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String username;
    private String password;
    private String role;
    private boolean blocked;


}
