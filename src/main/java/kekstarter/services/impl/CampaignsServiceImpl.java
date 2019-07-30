package kekstarter.services.impl;

import kekstarter.dto.CampaignsDto;
import kekstarter.mappers.campaignsMappers.CampaignsEditMapper;
import kekstarter.mappers.campaignsMappers.CampaignsInfoMapper;
import kekstarter.models.Campaign;
import kekstarter.models.Tag;
import kekstarter.models.User;
import kekstarter.models.UserRole;
import kekstarter.repositories.CampaignsRepo;
import kekstarter.services.AuthenticationsService;
import kekstarter.services.CampaignsService;
import kekstarter.services.TagsService;
import kekstarter.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CampaignsServiceImpl implements CampaignsService {

    private final CampaignsRepo campaignsRepo;
    private final CampaignsInfoMapper campaignsInfoMapper;
    private final CampaignsEditMapper campaignsEditMapper;
    private final UsersService usersService;
    private final AuthenticationsService authenticationsService;
    private final TagsService tagsService;

    @Override
    public Set<Tag> addCampaigns(CampaignsDto campaignsDto) {
        Campaign campaign = new Campaign();
        if (campaignsDto.getImageUrl() == ""){
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
}
