package kekstarter.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Users {

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
    private UsersRole role;

    @Column(name = "activationCode")
    private String activationCode;

    @Column(name ="isActive")
    private Boolean isActive;

    @Column(name = "isBlocked")
    private boolean isBlocked;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsersRole getRole() {
        return role;
    }

    public void setRole(UsersRole role) {
        this.role = role;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Users(@NotNull @Size(min = 4, max = 30) String username, @NotNull String firstName, @NotNull String secondName, @NotNull String email, @NotNull String password, UsersRole role, String activationCode, Boolean isActive, boolean isBlocked) {
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.activationCode = activationCode;
        this.isActive = isActive;
        this.isBlocked = isBlocked;



    }

    public Users() {
    }
}
