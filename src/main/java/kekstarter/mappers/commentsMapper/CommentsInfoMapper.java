package kekstarter.mappers.commentsMapper;

import kekstarter.dto.CommentsDto;
import kekstarter.models.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentsInfoMapper {

    private CommentsDto makeDto(Comment comment){
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setId(comment.getId());
        commentsDto.setText(comment.getText());
        commentsDto.setId_campaign(comment.getCampaign().getId());
        commentsDto.setId_user(comment.getUser().getId());
        commentsDto.setUsername(comment.getUser().getUsername());
        return commentsDto;
    }

    public List<CommentsDto> makeList(List<Comment> commentsList){
        return commentsList.stream().map(this::makeDto).collect(Collectors.toList());
    }
}
