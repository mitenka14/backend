package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import kekstarter.models.User;
import kekstarter.repositories.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CampaignsEditMapper {

    public Campaign makeModel(CampaignsDto campaignsDto, User user) {
        Campaign campaign = new Campaign();
        campaign.setId(campaignsDto.getId());
        campaign.setUser(user);
        campaign.setName(campaignsDto.getName());
        campaign.setText(campaignsDto.getText());
        campaign.setImageUrl(campaignsDto.getImageUrl());
        return campaign;
    }

}
