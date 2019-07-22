package kekstarter.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@RequiredArgsConstructor

public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 4)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "text")
    private String text;

    @Column(name = "imageUrl")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


}
