package kekstarter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
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

    @NotNull
    @Column(name = "imageUrl")
    private String imageUrl;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @JsonBackReference(value = "campaigns-tags")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "campaigns_tags", joinColumns = @JoinColumn(name = "id_campaign"), inverseJoinColumns = @JoinColumn(name = "id_tag"))
    private Set<Tag> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return Objects.equals(id, campaign.id) &&
                Objects.equals(name, campaign.name) &&
                Objects.equals(text, campaign.text) &&
                Objects.equals(imageUrl, campaign.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, imageUrl);
    }
}
