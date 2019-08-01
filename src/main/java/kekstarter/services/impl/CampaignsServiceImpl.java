package kekstarter.services.impl;

import kekstarter.dto.CampaignsDto;
import kekstarter.mappers.campaignsMappers.CampaignsEditMapper;
import kekstarter.mappers.campaignsMappers.CampaignsInfoMapper;
import kekstarter.models.*;
import kekstarter.repositories.CampaignsRepo;
import kekstarter.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CampaignsServiceImpl implements CampaignsService {

    private final CampaignsRepo campaignsRepo;
    private final CampaignsInfoMapper campaignsInfoMapper;
    private final CampaignsEditMapper campaignsEditMapper;
    private final UsersService usersService;
    private final AuthenticationsService authenticationsService;
    private final TagsService tagsService;
    private final SearchService searchService;

    @Override
    public Set<Tag> addCampaigns(CampaignsDto campaignsDto) {
        Campaign campaign = new Campaign();
        if (campaignsDto.getImageUrl().equals("")){
            campaignsDto.setImageUrl("https://www.dropbox.com/s/rqjmneh4dwb1bys/cadf1f02-89da-4abb-84f4-ba508f06c8b6?raw=1");
        }
        campaign = campaignsEditMapper.makeModel(campaignsDto, campaign);
        campaign.setUser(usersService.getUserFromToken());
        campaign.setTags(tagsService.addTags(campaignsDto.getTagsString()));
        campaignsRepo.save(campaign);
        return campaign.getTags();
    }

    @Override
    public CampaignsDto getCampaignById(long idCampaign) {
        return campaignsInfoMapper.makeDto(findCampaignById(idCampaign));
    }

    @Override
    public Campaign findCampaignById(long idCampaign){
        return campaignsRepo.findById(idCampaign);
    }

    @Override
    public List<CampaignsDto> getCampaigns() {
        return campaignsInfoMapper.makeList(campaignsRepo.findAll());
    }

    @Override
    public void deleteCampaign(long idCampaign){
        Campaign campaign = campaignsRepo.findById(idCampaign);
        User user = usersService.getUserFromToken();
        if(campaign.getUser() == user || user.getRole()== UserRole.ROLE_ADMIN){
            tagsService.deleteTags(campaign);
            campaignsRepo.delete(campaign);
        }
    }

    @Override
    public void editCampaign(CampaignsDto campaignsDto){
        if (authenticationsService.getName().equals(campaignsDto.getUsername()) || usersService.getUserFromToken().getRole() == UserRole.ROLE_ADMIN) {
            Campaign campaign = campaignsRepo.findById(campaignsDto.getId());
            campaign = campaignsEditMapper.makeModel(campaignsDto, campaign);
            tagsService.deleteTags(campaign);
            campaign.setTags(tagsService.addTags(campaignsDto.getTagsString()));
            campaignsRepo.save(campaign);
        }
    }

    @Override
    public List<CampaignsDto> getCampaignsByUserId(long idUser){
        return campaignsInfoMapper.makeList(campaignsRepo.findAllByUser(usersService.findUserById(idUser)));
    }

    @Override
    public List<CampaignsDto> getCampaignsByTagId(long id){
        return campaignsInfoMapper.makeList(campaignsRepo.findAllByTags(tagsService.getTag(id)));
    }

    @Override
    public List<CampaignsDto> searchCampaigns(String text){
        String campaignFields[] = new String[]{"name","text"};
        List<Campaign> campaignList = searchService.search(text, campaignFields, Campaign.class);
        String commentAndBonusFields[] = new String[]{"text"};
        List<Comment> commentList = searchService.search(text, commentAndBonusFields, Comment.class);
        List<Campaign> campaignListByComments = commentList.stream().map(comment -> comment.getCampaign()).collect(Collectors.toList());
        List<Bonus> bonusList = searchService.search(text, commentAndBonusFields, Bonus.class);
        List<Campaign> campaignListByBonuses = bonusList.stream().map(bonus -> bonus.getCampaign()).collect(Collectors.toList());
        campaignList.addAll(campaignListByComments);
        campaignList.addAll(campaignListByBonuses);
        return campaignsInfoMapper.makeList(campaignList);
    }

}
