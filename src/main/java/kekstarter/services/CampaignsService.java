package kekstarter.services;

import kekstarter.dto.CampaignsDto;
import kekstarter.dto.CommentsDto;
import kekstarter.mappers.campaignsMappers.CampaignsEditMapper;
import kekstarter.mappers.campaignsMappers.CampaignsInfoMapper;
import kekstarter.mappers.commentsMapper.CommentsEditMapper;
import kekstarter.mappers.commentsMapper.CommentsInfoMapper;
import kekstarter.models.Campaign;
import kekstarter.models.Comment;
import kekstarter.repositories.CampaignsRepo;
import kekstarter.repositories.CommentsRepo;
import kekstarter.repositories.UsersRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CampaignsService {

    private final CampaignsRepo campaignsRepo;
    private final CommentsRepo commentsRepo;
    private final CampaignsInfoMapper campaignsInfoMapper;
    private final CampaignsEditMapper campaignsEditMapper;
    private final CommentsEditMapper commentsEditMapper;
    private final CommentsInfoMapper commentsInfoMapper;
    private final UsersRepo usersRepo;



    public void addCampaigns(CampaignsDto campaignsDto) {
        Campaign campaign = this.campaignsEditMapper.makeModel(campaignsDto);
        this.campaignsRepo.save(campaign);
    }

    public CampaignsDto getCampaignById(long idCampaign) {
        Campaign campaign = this.campaignsRepo.findById(idCampaign);
        return this.campaignsInfoMapper.makeDto(campaign);
    }

    public List<CampaignsDto> getCampaigns() {
        return this.campaignsInfoMapper.makeList(campaignsRepo.findAll());
    }

    public void deleteCampaign(long idCampaign){
        this.commentsRepo.deleteAllByCampaign(campaignsRepo.findById(idCampaign));
        this.campaignsRepo.deleteById(idCampaign);

    }

    public void addComment(CommentsDto commentsDto, long idCampaign) {
        Comment comment = this.commentsEditMapper.makeModel(commentsDto, idCampaign);
        this.commentsRepo.save(comment);
    }

    public List<CommentsDto> getComments(long idCampaign) {
        return this.commentsInfoMapper.makeList(commentsRepo.findAllByCampaign(campaignsRepo.findById(idCampaign)));
    }

    public List<CampaignsDto> getCampaignsByUserId(long idUser){
        return this.campaignsInfoMapper.makeList(campaignsRepo.findAllByUser(usersRepo.findById(idUser)));
    }
}
