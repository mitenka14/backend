package kekstarter.mappers.bonusesMappers;

import kekstarter.dto.BonusesDto;
import kekstarter.models.Bonus;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class BonusInfoMapper {

    public List<BonusesDto> makeListByCampaign(List<Bonus> bonusList){
        return bonusList.stream().map(this::makeDto).collect(toList());
    }

    private BonusesDto makeDto(Bonus bonus){
        BonusesDto bonusesDto = new BonusesDto();
        bonusesDto.setId(bonus.getId());
        bonusesDto.setPrice(bonus.getPrice());
        bonusesDto.setText(bonus.getText());
        return bonusesDto;
    }

    public List<BonusesDto> makeListByUser(List<Bonus> bonusList){
        return bonusList.stream().map(this::makeDtoByUser).collect(toList());
    }

    private BonusesDto makeDtoByUser(Bonus bonus){
        BonusesDto bonusesDto = makeDto(bonus);
        bonusesDto.setIdCampaign(bonus.getCampaign().getId());
        bonusesDto.setCampaignName(bonus.getCampaign().getName());
        return bonusesDto;
    }
}
