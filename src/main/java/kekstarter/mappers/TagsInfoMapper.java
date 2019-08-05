package kekstarter.mappers;

import kekstarter.dto.TagsDto;
import kekstarter.models.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TagsInfoMapper {

    public List<TagsDto> makeList(List<Tag> tagList){
        return tagList.stream().map(this::makeDto).collect(toList());
    }

    private TagsDto makeDto(Tag tag){
        TagsDto tagsDto = new TagsDto();
        tagsDto.setId(tag.getId());
        tagsDto.setName(tag.getName());
        return tagsDto;
    }

}
