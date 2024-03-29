package kekstarter.services;

import kekstarter.dto.TagsDto;
import kekstarter.models.Campaign;
import kekstarter.models.Tag;

import java.util.List;
import java.util.Set;

public interface TagsService {

    Set<Tag> addTags(String tagsString);

    void deleteTags(Campaign campaign);

    List<TagsDto> getTopTags();

    Tag getTag(long id);

}
