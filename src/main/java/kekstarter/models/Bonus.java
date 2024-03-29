package kekstarter.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Indexed
public class Bonus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=10)
    @Column(name = "text")
    @Field(index = Index.YES)
    private String text;

    @NotNull
    @Min(1)
    @Column(name = "price")
    private Integer price;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_campaign")
    private Campaign campaign;

    @JsonBackReference(value = "bonuses-users")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "bonuses_users", joinColumns = @JoinColumn(name = "id_bonus"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonus bonus = (Bonus) o;
        return Objects.equals(id, bonus.id) &&
                Objects.equals(text, bonus.text) &&
                Objects.equals(price, bonus.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, price);
    }

}
