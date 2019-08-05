package kekstarter.repositories;

import kekstarter.models.Bonus;
import kekstarter.models.Campaign;
import kekstarter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonusesRepo extends JpaRepository<Bonus, Long> {

    List<Bonus> findAllByCampaign(Campaign campaign);

    Bonus findById(long id);

    List<Bonus> findAllByUsers(User user);

}
