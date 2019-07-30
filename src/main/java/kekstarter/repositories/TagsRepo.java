package kekstarter.repositories;

import kekstarter.models.Campaign;
import kekstarter.models.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagsRepo extends JpaRepository<Tag, Long> {

    boolean existsByName(String name);

    Set<Tag> findAllByCampaigns(Campaign campaign);

    Tag findByName(String name);

    Set<Tag> findTop10ByOrderByCounterDesc();
}
