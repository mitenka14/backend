package kekstarter.mappers.bonusesMappers;

import kekstarter.dto.BonusesDto;
import kekstarter.models.Bonus;
import org.springframework.stereotype.Component;

@Component
public class BonusEditMapper {

    public Bonus makeModel(BonusesDto bonusesDto){
        Bonus bonus = new Bonus();
        bonus.setPrice(bonusesDto.getPrice());
        bonus.setText(bonusesDto.getText());
        return bonus;
    }

}
