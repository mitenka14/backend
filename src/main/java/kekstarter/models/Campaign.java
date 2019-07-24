package kekstarter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    @JsonBackReference(value = "campaign-comments")
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;



}
