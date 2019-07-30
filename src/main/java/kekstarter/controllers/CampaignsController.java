package kekstarter.controllers;

import kekstarter.dto.CampaignsDto;
import kekstarter.models.Campaign;
import kekstarter.models.Tag;
import kekstarter.services.CampaignsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "campaigns",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CampaignsController {

    private final CampaignsService campaignsService;


    @GetMapping("list")
    public List<CampaignsDto> getCampaigns() {
        return campaignsService.getCampaigns();
    }

    @GetMapping("userlist/{idUser}")
    public List<CampaignsDto> getCampaignsByUserId(@PathVariable long idUser){
        return campaignsService.getCampaignsByUserId(idUser);
    }

    @PostMapping("add")
    public Set<Tag> addCampaigns(@RequestBody CampaignsDto campaignsDto) {
        return campaignsService.addCampaigns(campaignsDto);
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
