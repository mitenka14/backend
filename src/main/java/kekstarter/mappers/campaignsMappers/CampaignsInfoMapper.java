package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import kekstarter.models.User;
import kekstarter.repositories.UsersRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CampaignsInfoMapper {

    private final CampaignsAddMapper campaignsAddMapper;

    public CampaignsInfoMapper(CampaignsAddMapper campaignsAddMapper) {
        this.campaignsAddMapper = campaignsAddMapper;
    }


    public CampaignsDto makeConversion(Campaign campaign) {
        CampaignsDto campaignsDto = new CampaignsDto();
        campaignsDto.setId(campaign.getId());
        campaignsDto.setId_user(campaign.getUser().getId());
        campaignsDto.setUsername(campaign.getUser().getUsername());
        campaignsDto.setName(campaign.getName());
        campaignsDto.setText(campaign.getText());
        return campaignsDto;
    }

    public List<CampaignsDto> makeList(List<Campaign> campaignsList) {
        List<CampaignsDto> companiesInfoDtoList = new ArrayList<>();
        for (Campaign campaign : campaignsList) {
            companiesInfoDtoList.add(makeConversion(campaign));
        }
        return companiesInfoDtoList;
    }
}
