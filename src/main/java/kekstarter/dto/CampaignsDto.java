package kekstarter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CampaignsDto {

    private long id;
    private String name;
    private String text;
    private String imageUrl;
    private long id_user;
    private String username;
}
