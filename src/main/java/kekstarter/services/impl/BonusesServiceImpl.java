package kekstarter.services.impl;

import kekstarter.dto.BonusesDto;
import kekstarter.dto.ResponseTextDto;
import kekstarter.mappers.bonusesMappers.BonusEditMapper;
import kekstarter.mappers.bonusesMappers.BonusInfoMapper;
import kekstarter.models.Bonus;
import kekstarter.models.Campaign;
import kekstarter.models.User;
import kekstarter.models.UserRole;
import kekstarter.repositories.BonusesRepo;
import kekstarter.responseMessage.ResponseMessages;
import kekstarter.services.BonusesService;
import kekstarter.services.CampaignsService;
import kekstarter.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BonusesServiceImpl implements BonusesService {

    private final BonusesRepo bonusesRepo;
    private final CampaignsService campaignsService;
    private final UsersService usersService;
    private final BonusEditMapper bonusEditMapper;
    private final BonusInfoMapper bonusInfoMapper;

    @Override
    public void addBonus(BonusesDto bonusesDto, long idCampaign){
        Campaign campaign = campaignsService.findCampaignById(idCampaign);
        User user = usersService.getUserFromToken();
        if (user.getRole() == UserRole.ROLE_ADMIN || user.getId() == campaign.getUser().getId()){
            Bonus bonus = bonusEditMapper.makeModel(bonusesDto);
            bonus.setCampaign(campaign);
            bonusesRepo.save(bonus);
        }
    }

    @Override
    public List<BonusesDto> getBonusesByCampaign(long idCampaign){
        return bonusInfoMapper.makeListByCampaign(bonusesRepo.findAllByCampaign(campaignsService.findCampaignById(idCampaign)));
    }

    @Override
    public List<BonusesDto> getBonusesByUser(long idUser){
        return bonusInfoMapper.makeListByUser(bonusesRepo.findAllByUsers(usersService.findUserById(idUser)));
    }

    @Override
    public ResponseTextDto buyBonus(long id){
        Bonus bonus = bonusesRepo.findById(id);
        User user = usersService.getUserFromToken();
        if (bonus.getPrice() > user.getMoney()){
            return new ResponseTextDto(ResponseMessages.NOT_ENOUGH_MONEY);
        }
        user.setMoney(user.getMoney() - bonus.getPrice());
        Set<User> userSet = bonus.getUsers();
        userSet.add(user);
        bonus.setUsers(userSet);
        Campaign campaign = campaignsService.findCampaignByBonus(bonus);
        campaign.setCollectedFunds(campaign.getCollectedFunds()+bonus.getPrice());
        bonus.setCampaign(campaign);
        bonusesRepo.save(bonus);
        return new ResponseTextDto(ResponseMessages.SUCCESS);
    }

}
