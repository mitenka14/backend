package kekstarter.services.impl;

import kekstarter.dto.CampaignsDto;
import kekstarter.mappers.campaignsMappers.CampaignsEditMapper;
import kekstarter.mappers.campaignsMappers.CampaignsInfoMapper;
import kekstarter.models.Campaign;
import kekstarter.models.User;
import kekstarter.models.UserRole;
import kekstarter.repositories.CampaignsRepo;
import kekstarter.services.AuthenticationsService;
import kekstarter.services.CampaignsService;
import kekstarter.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignsServiceImpl implements CampaignsService {

    private final CampaignsRepo campaignsRepo;
    private final CampaignsInfoMapper campaignsInfoMapper;
    private final CampaignsEditMapper campaignsEditMapper;
    private final UsersService usersService;
    private final AuthenticationsService authenticationsService;

    @Override
    public void addCampaigns(CampaignsDto campaignsDto) {
        Campaign campaign = new Campaign();
        campaign = campaignsEditMapper.makeModel(campaignsDto, campaign);
        campaign.setUser(usersService.getUserFromToken());
        campaignsRepo.save(campaign);
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
            campaignsRepo.delete(campaign);
        }
    }

    @Override
    public void editCampaign(CampaignsDto campaignsDto){
        if (authenticationsService.getName().equals(campaignsDto.getUsername()) || usersService.getUserFromToken().getRole() == UserRole.ROLE_ADMIN) {
            Campaign campaign = campaignsRepo.findById(campaignsDto.getId());
            campaign = campaignsEditMapper.makeModel(campaignsDto, campaign);
            campaignsRepo.save(campaign);
        }
    }

    @Override
    public List<CampaignsDto> getCampaignsByUserId(long idUser){
        return campaignsInfoMapper.makeList(campaignsRepo.findAllByUser(usersService.findUserById(idUser)));
    }
}
