package kekstarter.services;

import kekstarter.dto.ResponseTextDto;
import kekstarter.models.Bonus;

import java.util.List;

public interface BonusesService {
    void addBonus(Bonus bonus, long idCampaign);

    List<Bonus> getBonusesByCampaign(long idCampaign);

    List<Bonus> getBonusesByUser(long idUser);

    ResponseTextDto buyBonus(long id);
}
