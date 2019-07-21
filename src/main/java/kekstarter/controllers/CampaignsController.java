package kekstarter.controllers;

import kekstarter.dto.CampaignsDto;
import kekstarter.dto.CommentsDto;
import kekstarter.services.CampaignsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "campaigns",produces = MediaType.APPLICATION_JSON_VALUE)
public class CampaignsController {

    private final CampaignsService campaignsService;

    public CampaignsController(CampaignsService campaignsService) {
        this.campaignsService = campaignsService;
    }

    @GetMapping("/list")
    public List<CampaignsDto> getCampaigns() {
        return this.campaignsService.getCampaigns();
    }

    @PostMapping("/new/addcampaign")
    public void addCampaigns(@RequestBody CampaignsDto campaignsDto) {
        this.campaignsService.addCampaigns(campaignsDto);
    }

    @GetMapping("/campaign/{idCampaign}")
    public CampaignsDto getCampaignById(@PathVariable long idCampaign) {
        return this.campaignsService.getCampaignById(idCampaign);
    }

    @PostMapping("/campaign/{idCampaign}/comments/add")
    public void addComment(@RequestBody CommentsDto commentsDto, @PathVariable long idCampaign){
        this.campaignsService.addComment(commentsDto, idCampaign);
    }

    @GetMapping("/campaign/{idCampaign}/comments")
    public List<CommentsDto> getComments(@PathVariable long idCampaign) {
        return this.campaignsService.getComments(idCampaign);
    }
}
