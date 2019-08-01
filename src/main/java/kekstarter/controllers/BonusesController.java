package kekstarter.controllers;

import kekstarter.dto.ResponseTextDto;
import kekstarter.models.Bonus;
import kekstarter.services.BonusesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "bonuses", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BonusesController {

    private final BonusesService bonusesService;

    @PostMapping("campaign/{idCampaign}")
    public void addBonus(@PathVariable long idCampaign, @RequestBody Bonus bonus){
        bonusesService.addBonus(bonus, idCampaign);
    }

    @GetMapping("campaign/{idCampaign}")
    public List<Bonus> getBonusesByCampaign(@PathVariable long idCampaign){
        return bonusesService.getBonusesByCampaign(idCampaign);
    }

    @GetMapping("user/{idUser}")
    public List<Bonus> getBonusesByUser(@PathVariable long idUser){
        return bonusesService.getBonusesByUser(idUser);
    }

    @GetMapping("buy/{idBonus}")
    public ResponseTextDto buyBonus(@PathVariable long idBonus){
        return bonusesService.buyBonus(idBonus);
    }
}
