package kekstarter.services;

import kekstarter.dto.CampaignsDto;
import kekstarter.mappers.campaignsMappers.CampaignsEditMapper;
import kekstarter.mappers.campaignsMappers.CampaignsInfoMapper;
import kekstarter.models.Campaign;
import kekstarter.models.User;
import kekstarter.models.UserRole;
import kekstarter.repositories.CampaignsRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignsService {

    private final CampaignsRepo campaignsRepo;
    private final CampaignsInfoMapper campaignsInfoMapper;
    private final CampaignsEditMapper campaignsEditMapper;
    private final UsersService usersService;

    public void addCampaigns(CampaignsDto campaignsDto) {
        Campaign campaign = campaignsEditMapper.makeModel(campaignsDto, usersService.getUserFromToken());
        campaignsRepo.save(campaign);
    }

    public CampaignsDto getCampaignById(long idCampaign) {
        return campaignsInfoMapper.makeDto(findCampaignById(idCampaign));
    }

    public Campaign findCampaignById(long idCampaign){
        return campaignsRepo.findById(idCampaign);
    }

    public List<CampaignsDto> getCampaigns() {
        return campaignsInfoMapper.makeList(campaignsRepo.findAll());
    }

    public void deleteCampaign(long idCampaign){
        Campaign campaign = campaignsRepo.findById(idCampaign);
        User user = usersService.getUserFromToken();
        if(campaign.getUser() == user || user.getRole()== UserRole.ROLE_ADMIN){
            campaignsRepo.delete(campaign);
        }
    }

    public List<CampaignsDto> getCampaignsByUserId(long idUser){
        return campaignsInfoMapper.makeList(campaignsRepo.findAllByUser(usersService.findUserById(idUser)));
    }
}
