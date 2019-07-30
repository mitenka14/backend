package kekstarter.services;

import kekstarter.models.Campaign;
import kekstarter.models.Tag;

import java.util.Set;

public interface TagsService {
    Set<Tag> addTags(String tagsString);

    void deleteTags(Campaign campaign);

    Set<Tag> getTopTags();
}
