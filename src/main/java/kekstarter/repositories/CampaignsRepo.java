package kekstarter.repositories;

import kekstarter.models.Campaigns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignsRepo extends JpaRepository<Campaigns, Long> {

}
