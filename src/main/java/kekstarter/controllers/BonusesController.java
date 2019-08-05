package kekstarter.controllers;

import kekstarter.dto.BonusesDto;
import kekstarter.dto.ResponseTextDto;
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
    public void addBonus(@PathVariable long idCampaign, @RequestBody BonusesDto bonusesDto){
        bonusesService.addBonus(bonusesDto, idCampaign);
    }

    @GetMapping("campaign/{idCampaign}")
    public List<BonusesDto> getBonusesByCampaign(@PathVariable long idCampaign){
        return bonusesService.getBonusesByCampaign(idCampaign);
    }

    @GetMapping("user/{idUser}")
    public List<BonusesDto> getBonusesByUser(@PathVariable long idUser){
        return bonusesService.getBonusesByUser(idUser);
    }

    @GetMapping("buy/{idBonus}")
    public ResponseTextDto buyBonus(@PathVariable long idBonus){
        return bonusesService.buyBonus(idBonus);
    }
}
