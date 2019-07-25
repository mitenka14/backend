package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampaignsInfoMapper {


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

    public List<CampaignsDto> makeList(final List<Campaign> campaignsList) {
        return campaignsList.stream().map(this::makeDto).collect(Collectors.toList());
    }
}
