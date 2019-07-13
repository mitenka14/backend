package kekstarter.mappers.campaignsMappers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaigns;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CampaignsInfoMapper {

    private final CampaignsAddMapper campaignsAddMapper;

    public CampaignsInfoMapper(CampaignsAddMapper campaignsAddMapper) {
        this.campaignsAddMapper = campaignsAddMapper;
    }


    public CampaignsDto makeConversion(Campaigns campaigns) {
        CampaignsDto companiesAddDto = new CampaignsDto();
        companiesAddDto.setId(campaigns.getId());
        companiesAddDto.setName(campaigns.getName());
        companiesAddDto.setText(campaigns.getText());
        return companiesAddDto;
    }

    public List<CampaignsDto> makeList(List<Campaigns> campaignsList) {
        List<CampaignsDto> companiesInfoDtoList = new ArrayList<>();
        for (Campaigns campaigns : campaignsList) {
            companiesInfoDtoList.add(makeConversion(campaigns));
        }
        return companiesInfoDtoList;
    }
}
