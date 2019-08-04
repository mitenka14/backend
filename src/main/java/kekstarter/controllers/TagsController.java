package kekstarter.controllers;

import kekstarter.dto.TagsDto;
import kekstarter.models.Campaign;
import kekstarter.models.Tag;
import kekstarter.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "tags", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TagsController {

    private final TagsService tagsService;

    @GetMapping
    public List<TagsDto> getTags(){
        return tagsService.getTopTags();
    }


}
