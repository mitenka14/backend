package kekstarter.mappers.commentsMapper;

import kekstarter.dto.CommentsDto;
import kekstarter.models.Campaign;
import kekstarter.models.Comment;
import kekstarter.models.User;
import kekstarter.repositories.CampaignsRepo;
import kekstarter.repositories.CommentsRepo;
import kekstarter.repositories.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class CommentsEditMapper {

    public Comment makeModel(CommentsDto commentsDto, Campaign campaign, User user) {
        Comment comment = new Comment();
        comment.setId(commentsDto.getId());
        comment.setUser(user);
        comment.setCampaign(campaign);
        comment.setText(commentsDto.getText());
        return comment;

    }
}
