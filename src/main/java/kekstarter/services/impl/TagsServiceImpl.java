package kekstarter.services.impl;

import kekstarter.models.Campaign;
import kekstarter.models.Tag;
import kekstarter.repositories.TagsRepo;
import kekstarter.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.lang.*;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {

    private final TagsRepo tagsRepo;

    @Override
    public Set<Tag> addTags(String tagsString) {
        while(true){
            if (tagsString.charAt(0) == ' ') {
                tagsString = tagsString.substring(1);
            }
            else break;
        }
        return Arrays.stream(tagsString.split(" ")).map(this::setTagName).collect(Collectors.toSet());
    }

    @Override
    public void deleteTags(Campaign campaign){
        Set<Tag> tags = tagsRepo.findAllByCampaigns(campaign);
        for(Tag tag : tags){
            decrementTagCounter(tag);
        }
    }

    @Override
    public Set<Tag> getTopTags(){
        return tagsRepo.findTop10ByOrderByCounterDesc();
    }

    private void decrementTagCounter(Tag tag){
        tag.setCounter(tag.getCounter()-1);
        if (tag.getCounter() == 0){
            tagsRepo.delete(tag);
        }
        else {
            tagsRepo.save(tag);
        }
    }

    private Tag setTagName(String name){
        Tag tag;
        if (tagsRepo.existsByName(name)){
            tag = tagsRepo.findByName(name);
            tag.setCounter(tag.getCounter()+1);
            return tag;
        }
        else {
            tag = new Tag();
            tag.setName(name);
            tag.setCounter((long) 1);
            return tag;
        }
    }
}
