package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import kekstarter.models.User;
import kekstarter.repositories.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CampaignsAddMapper {

    private final UsersRepo usersRepo;

    public Campaign makeModel(CampaignsDto campaignsDto) {
        Campaign campaign = new Campaign();
        campaign.setId(campaignsDto.getId());
        campaign.setUser(this.usersRepo.findById(campaignsDto.getId_user()));
        campaign.setName(campaignsDto.getName());
        campaign.setText(campaignsDto.getText());
        campaign.setImageUrl(campaignsDto.getImageUrl());
        return campaign;
    }

}
