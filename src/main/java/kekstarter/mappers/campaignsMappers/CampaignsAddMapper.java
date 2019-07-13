package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaigns;
import org.springframework.stereotype.Component;

@Component
public class CampaignsAddMapper {

    public Campaigns makeModel(CampaignsDto campaignsDto) {
        Campaigns campaigns = new Campaigns();
        campaigns.setId(campaignsDto.getId());
        campaigns.setName(campaignsDto.getName());
        campaigns.setText(campaignsDto.getText());
        return campaigns;
    }

}
