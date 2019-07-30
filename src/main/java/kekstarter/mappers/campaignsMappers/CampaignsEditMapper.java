package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import kekstarter.models.User;
import kekstarter.repositories.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

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
