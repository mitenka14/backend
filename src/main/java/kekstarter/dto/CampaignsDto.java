package kekstarter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignsDto {

    private Long id;
    private String name;
    private String text;
    private long id_user;
    private String username;
}
