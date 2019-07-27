package kekstarter.services;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;

import java.util.List;

public interface CampaignsService {
    void addCampaigns(CampaignsDto campaignsDto);

    CampaignsDto getCampaignById(long idCampaign);

    Campaign findCampaignById(long idCampaign);

    List<CampaignsDto> getCampaigns();

    void deleteCampaign(long idCampaign);

    List<CampaignsDto> getCampaignsByUserId(long idUser);

    void editCampaign(CampaignsDto campaignsDto);
}
