package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

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
        campaignsDto.setTags(campaign.getTags());
        return campaignsDto;
    }

    public List<CampaignsDto> makeList(final List<Campaign> campaignsList) {
        return campaignsList.stream()
                .map(this::makeDto)
                .collect(toList());
    }
}
