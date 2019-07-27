package kekstarter.services;

import kekstarter.dto.CommentsDto;

import java.util.List;

public interface CommentsService {
    void addComment(CommentsDto commentsDto, long idCampaign);

    List<CommentsDto> getComments(long idCampaign);
}
