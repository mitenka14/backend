package kekstarter.services;

import kekstarter.dto.CommentsDto;
import kekstarter.mappers.commentsMapper.CommentsEditMapper;
import kekstarter.mappers.commentsMapper.CommentsInfoMapper;
import kekstarter.models.Campaign;
import kekstarter.models.Comment;
import kekstarter.repositories.CommentsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepo commentsRepo;
    private final CommentsEditMapper commentsEditMapper;
    private final CommentsInfoMapper commentsInfoMapper;
    private final CampaignsService campaignsService;
    private final UsersService usersService;

    public void addComment(CommentsDto commentsDto, long idCampaign) {
        Comment comment = commentsEditMapper.makeModel(commentsDto, campaignsService.findCampaignById(idCampaign), usersService.getUserFromToken());
        commentsRepo.save(comment);
    }

    public List<CommentsDto> getComments(long idCampaign) {
        return commentsInfoMapper.makeList(commentsRepo.findAllByCampaign(campaignsService.findCampaignById(idCampaign)));
    }



}
