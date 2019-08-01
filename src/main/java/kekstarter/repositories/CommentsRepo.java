package kekstarter.repositories;

import kekstarter.models.Campaign;
import kekstarter.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepo extends JpaRepository<Comment, Long> {

    List<Comment> findAllByCampaign(Campaign campaign);

}
