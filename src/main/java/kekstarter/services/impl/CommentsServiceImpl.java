package kekstarter.services.impl;

import kekstarter.dto.CommentsDto;
import kekstarter.mappers.commentsMapper.CommentsEditMapper;
import kekstarter.mappers.commentsMapper.CommentsInfoMapper;
import kekstarter.models.Comment;
import kekstarter.repositories.CommentsRepo;
import kekstarter.services.CampaignsService;
import kekstarter.services.CommentsService;
import kekstarter.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentsRepo commentsRepo;
    private final CommentsEditMapper commentsEditMapper;
    private final CommentsInfoMapper commentsInfoMapper;
    private final CampaignsService campaignsService;
    private final UsersService usersService;

    @Override
    public void addComment(CommentsDto commentsDto, long idCampaign) {
        Comment comment = commentsEditMapper.makeModel(commentsDto, campaignsService.findCampaignById(idCampaign), usersService.getUserFromToken());
        commentsRepo.save(comment);
    }

    @Override
    public List<CommentsDto> getComments(long idCampaign) {
        return commentsInfoMapper.makeList(commentsRepo.findAllByCampaign(campaignsService.findCampaignById(idCampaign)));
    }



}
