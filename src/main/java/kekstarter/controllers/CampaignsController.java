package kekstarter.controllers;

import kekstarter.dto.CampaignsDto;
import kekstarter.dto.CommentsDto;
import kekstarter.services.CampaignsService;
import kekstarter.services.DropboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "campaigns",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CampaignsController {

    private final DropboxService dropboxService;
    private final CampaignsService campaignsService;



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

    @PostMapping("/new/addcampaign/addimage")
    public String addImage(@RequestParam("file") MultipartFile image) {
        return this.dropboxService.uploadImage(image);
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
