package kekstarter.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Campaigns {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "text")
    private String text;

    public Campaigns(@NotNull String name, @NotNull String text) {
        this.name = name;
        this.text = text;
    }

    public Campaigns() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
