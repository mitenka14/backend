package kekstarter.dto;

import kekstarter.models.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CampaignsDto {

    private long id;
    private String name;
    private String text;
    private String imageUrl;
    private long id_user;
    private String username;
    private String tagsString;
    private Set<Tag> tags;
    private int collectedFunds;
    private int goal;
}
