package kekstarter.repositories;

import kekstarter.models.Campaign;
import kekstarter.models.Tag;
import kekstarter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignsRepo extends JpaRepository<Campaign, Long> {

    Campaign findById(long id);

    List<Campaign> findAllByUser(User user);

    List<Campaign> findAllByTags(Tag tag);

}
