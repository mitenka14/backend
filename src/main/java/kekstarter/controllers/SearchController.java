package kekstarter.controllers;

import kekstarter.models.Tag;
import kekstarter.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Set;

@RestController
@RequestMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SearchController {

    private final TagsService tagsService;

    @GetMapping
    public Set<Tag> getTags(){
        return tagsService.getTopTags();
    }
}
