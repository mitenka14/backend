package kekstarter.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@RequiredArgsConstructor

public class Campaigns {

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


}
