package kekstarter.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
    private long id;
    private String firstName;
    private String secondName;
    private String email;
    private String username;
    private String password;
    private String role;
    private String imageUrl;



}
