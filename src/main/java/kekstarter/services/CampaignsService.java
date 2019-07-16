package kekstarter.services;

import kekstarter.dto.CampaignsDto;
import kekstarter.mappers.campaignsMappers.CampaignsAddMapper;
import kekstarter.mappers.campaignsMappers.CampaignsInfoMapper;
import kekstarter.models.Campaigns;
import kekstarter.repositories.CampaignsRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampaignsService {

    private final CampaignsRepo campaignsRepo;
    private final CampaignsInfoMapper campaignsInfoMapper;
    private final CampaignsAddMapper campaignsAddMapper;


    public void addCampaigns(CampaignsDto campaignsDto) {
        Campaigns company = campaignsAddMapper.makeModel(campaignsDto);
        this.campaignsRepo.save(company);
    }

    public List<CampaignsDto> getCampaigns() {
        return campaignsInfoMapper.makeList(campaignsRepo.findAll());
    }
}
