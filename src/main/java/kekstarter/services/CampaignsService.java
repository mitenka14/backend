package kekstarter.services;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import kekstarter.models.Comment;
import kekstarter.models.Tag;

import java.util.List;
import java.util.Set;

public interface CampaignsService {
    Set<Tag> addCampaigns(CampaignsDto campaignsDto);

    CampaignsDto getCampaignById(long idCampaign);

    Campaign findCampaignById(long idCampaign);

    List<CampaignsDto> getCampaigns();

    void deleteCampaign(long idCampaign);

    List<CampaignsDto> getCampaignsByUserId(long idUser);

    List<CampaignsDto> getCampaignsByTagId(long id);

    List<CampaignsDto> searchCampaigns(String text);

    void editCampaign(CampaignsDto campaignsDto);


}
