package kekstarter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BonusesDto {

    private long id;
    private String text;
    private int price;
    private long idCampaign;
    private String campaignName;

}
