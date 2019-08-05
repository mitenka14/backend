package kekstarter.services;

import kekstarter.dto.BonusesDto;
import kekstarter.dto.ResponseTextDto;

import java.util.List;

public interface BonusesService {

    void addBonus(BonusesDto bonusesDto, long idCampaign);

    List<BonusesDto> getBonusesByCampaign(long idCampaign);

    List<BonusesDto> getBonusesByUser(long idUser);

    ResponseTextDto buyBonus(long id);

}
