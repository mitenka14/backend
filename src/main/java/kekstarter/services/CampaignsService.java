package kekstarter.services;

import kekstarter.dto.CampaignsDto;
import kekstarter.dto.CommentsDto;
import kekstarter.mappers.campaignsMappers.CampaignsAddMapper;
import kekstarter.mappers.campaignsMappers.CampaignsInfoMapper;
import kekstarter.mappers.commentsMapper.CommentsAddMapper;
import kekstarter.mappers.commentsMapper.CommentsInfoMapper;
import kekstarter.models.Campaign;
import kekstarter.models.Comment;
import kekstarter.repositories.CampaignsRepo;
import kekstarter.repositories.CommentsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampaignsService {

    private final CampaignsRepo campaignsRepo;
    private final CommentsRepo commentsRepo;
    private final CampaignsInfoMapper campaignsInfoMapper;
    private final CampaignsAddMapper campaignsAddMapper;
    private final CommentsAddMapper commentsAddMapper;
    private final CommentsInfoMapper commentsInfoMapper;


    public void addCampaigns(CampaignsDto campaignsDto) {
        Campaign campaign = this.campaignsAddMapper.makeModel(campaignsDto);
        this.campaignsRepo.save(campaign);
    }

    public CampaignsDto getCampaignById(long idCampaign) {
        Campaign campaign = this.campaignsRepo.findById(idCampaign);
        return this.campaignsInfoMapper.makeDto(campaign);
    }

    public List<CampaignsDto> getCampaigns() {
        return this.campaignsInfoMapper.makeList(campaignsRepo.findAll());
    }

    public void addComment(CommentsDto commentsDto, long idCampaign) {
        Comment comment = this.commentsAddMapper.makeModel(commentsDto, idCampaign);
        this.commentsRepo.save(comment);
    }

    public List<CommentsDto> getComments(long idCampaign) {
        return this.commentsInfoMapper.makeList(commentsRepo.findAllByCampaign(campaignsRepo.findById(idCampaign)));
    }
}
