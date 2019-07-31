package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import org.springframework.stereotype.Component;

@Component
public class CampaignsEditMapper {

    public Campaign makeModel(CampaignsDto campaignsDto, Campaign campaign) {
        campaign.setId(campaignsDto.getId());
        campaign.setName(campaignsDto.getName());
        campaign.setText(campaignsDto.getText());
        campaign.setImageUrl(campaignsDto.getImageUrl());
        return campaign;
    }

}
