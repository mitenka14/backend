package kekstarter.services.impl;

import kekstarter.dto.TagsDto;
import kekstarter.mappers.TagsInfoMapper;
import kekstarter.models.Campaign;
import kekstarter.models.Tag;
import kekstarter.repositories.TagsRepo;
import kekstarter.services.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {

    private final TagsRepo tagsRepo;
    private final TagsInfoMapper tagsInfoMapper;

    @Override
    public Set<Tag> addTags(final String tagsLine) {
        final String refactoredTagsLine = tagsLine.trim().replaceAll("\\s+", " ");
        return Stream.of(refactoredTagsLine.split(" "))
                .map(this::setTagName)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteTags(final Campaign campaign){
        tagsRepo.findAllByCampaigns(campaign).forEach(this::decrementTagCounter);
    }

    @Override
    public List<TagsDto> getTopTags(){
        return tagsInfoMapper.makeList(tagsRepo.findTop10ByOrderByCounterDesc());
    }

    @Override
    public Tag getTag(long id){
        return tagsRepo.findById(id);
    }

    private void decrementTagCounter(Tag tag){
        tag.setCounter(tag.getCounter()-1);
        if (tag.getCounter() == 0){
            tagsRepo.delete(tag);
        } else {
            tagsRepo.save(tag);
        }
    }

    private Tag setTagName(final String name){
        Tag tag;
        if (tagsRepo.existsByName(name)){
            tag = tagsRepo.findByName(name);
            tag.setCounter(tag.getCounter()+1);
        }
        else {
            tag = new Tag();
            tag.setName(name);
        }
        return tag;
    }

}
