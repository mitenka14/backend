package kekstarter.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=4, max=30)
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Column(name = "secondName")
    private String secondName;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_USER;

    @Column(name = "activationCode")
    private String activationCode;

    @Column(name = "blocked")
    private Boolean blocked = Boolean.TRUE;

}
