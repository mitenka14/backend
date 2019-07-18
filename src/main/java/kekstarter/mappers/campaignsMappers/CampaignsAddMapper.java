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
        User user = this.usersRepo.findByUsername(campaignsDto.getUsername());
        campaign.setUser(user);
        campaign.setName(campaignsDto.getName());
        campaign.setText(campaignsDto.getText());
        return campaign;
    }

}
