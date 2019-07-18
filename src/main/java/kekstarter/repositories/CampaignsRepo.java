package kekstarter.repositories;

import kekstarter.models.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignsRepo extends JpaRepository<Campaign, Long> {

}
