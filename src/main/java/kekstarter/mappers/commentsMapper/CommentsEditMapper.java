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
@RequiredArgsConstructor
public class CommentsEditMapper {

    private final UsersRepo usersRepo;
    private final CampaignsRepo campaignsRepo;

    public Comment makeModel(CommentsDto commentsDto, long idCampaign) {
        Comment comment = new Comment();
        comment.setId(commentsDto.getId());
        comment.setUser(this.usersRepo.findById(commentsDto.getId_user()));
        comment.setCampaign(this.campaignsRepo.findById(idCampaign));
        comment.setText(commentsDto.getText());
        return comment;

    }
}
