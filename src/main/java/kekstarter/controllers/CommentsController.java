package kekstarter.controllers;

import kekstarter.dto.CommentsDto;
import kekstarter.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "comments",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/{idCampaign}")
    public void addComment(@RequestBody CommentsDto commentsDto, @PathVariable long idCampaign){
        commentsService.addComment(commentsDto, idCampaign);
    }

    @GetMapping("{idCampaign}")
    public List<CommentsDto> getComments(@PathVariable long idCampaign) {
        return commentsService.getComments(idCampaign);
    }
}
