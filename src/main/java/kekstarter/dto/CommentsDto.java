package kekstarter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsDto {

    private long id;
    private String text;
    private long id_campaign;
    private long id_user;
    private String username;
}
