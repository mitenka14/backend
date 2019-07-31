package kekstarter.controllers;

import kekstarter.dto.CampaignsDto;
import kekstarter.services.CampaignsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "campaigns",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CampaignsController {

    private final CampaignsService campaignsService;

    @GetMapping
    public List<CampaignsDto> getCampaigns() {
        return campaignsService.getCampaigns();
    }

    @GetMapping("users/{idUser}")
    public List<CampaignsDto> getCampaignsByUserId(@PathVariable long idUser){
        return campaignsService.getCampaignsByUserId(idUser);
    }

    @GetMapping("tag/{id}")
    public List<CampaignsDto> getCampaignsByTag(@PathVariable long id) {
        return campaignsService.getCampaignsByTagId(id);
    }

    @GetMapping("search/{text}")
    public List<CampaignsDto> searchCampaigns(@PathVariable String text){
        return campaignsService.searchCampaigns(text);
    }

    @PostMapping
    public void addCampaigns(@RequestBody CampaignsDto campaignsDto) {
        campaignsService.addCampaigns(campaignsDto);
    }

    @GetMapping("campaign/{idCampaign}")
    public CampaignsDto getCampaignById(final @PathVariable long idCampaign) {
        return campaignsService.getCampaignById(idCampaign);
    }

    @PostMapping("campaign/{idCampaign}")
    public void editCampaign(@RequestBody CampaignsDto campaignsDto){
        campaignsService.editCampaign(campaignsDto);
    }

    @DeleteMapping("campaign/{idCampaign}")
    public void deleteCampaign(final @PathVariable long idCampaign) {
        campaignsService.deleteCampaign(idCampaign);
    }

}
