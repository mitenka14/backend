package kekstarter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "money")
    private Integer money = 1000;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Campaign> campaigns;

    @JsonBackReference(value = "bonuses-users")
    @ManyToMany(mappedBy = "users")
    private Set<Bonus> bonuses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(activationCode, user.activationCode) &&
                Objects.equals(blocked, user.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, secondName, email, password, activationCode, blocked);
    }
}
