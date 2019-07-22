package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CampaignsInfoMapper {

    private final CampaignsAddMapper campaignsAddMapper;

    public CampaignsInfoMapper(CampaignsAddMapper campaignsAddMapper) {
        this.campaignsAddMapper = campaignsAddMapper;
    }


    public CampaignsDto makeDto(Campaign campaign) {
        CampaignsDto campaignsDto = new CampaignsDto();
        campaignsDto.setId(campaign.getId());
        campaignsDto.setId_user(campaign.getUser().getId());
        campaignsDto.setUsername(campaign.getUser().getUsername());
        campaignsDto.setName(campaign.getName());
        campaignsDto.setText(campaign.getText());
        campaignsDto.setImageUrl(campaign.getImageUrl());
        return campaignsDto;
    }

    public List<CampaignsDto> makeList(List<Campaign> campaignsList) {
        List<CampaignsDto> campaignsDtoList = new ArrayList<>();
        for (Campaign campaign : campaignsList) {
            campaignsDtoList.add(makeDto(campaign));
        }
        return campaignsDtoList;
    }
}
