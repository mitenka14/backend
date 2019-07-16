package kekstarter.controllers;

import kekstarter.dto.CampaignsDto;
import kekstarter.services.CampaignsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CampaignsController {

    private final CampaignsService campaignsService;

    public CampaignsController(CampaignsService campaignsService) {
        this.campaignsService = campaignsService;
    }

    @GetMapping("/list")
    public List<CampaignsDto> getCampaigns() {
        return this.campaignsService.getCampaigns();
    }

    @PostMapping("/addcampaign")
    public void addCampaigns(@RequestBody CampaignsDto campaignsDto) {
        this.campaignsService.addCampaigns(campaignsDto);
    }
}
