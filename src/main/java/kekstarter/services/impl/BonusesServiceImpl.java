package kekstarter.services.impl;

import kekstarter.dto.ResponseTextDto;
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

    @Override
    public void addBonus(Bonus bonus, long idCampaign){
        Campaign campaign = campaignsService.findCampaignById(idCampaign);
        User user = usersService.getUserFromToken();
        if (user.getRole() == UserRole.ROLE_ADMIN || user.getId() == campaign.getUser().getId()){
            bonus.setCampaign(campaign);
            bonusesRepo.save(bonus);
        }
    }

    @Override
    public List<Bonus> getBonusesByCampaign(long idCampaign){
        return bonusesRepo.findAllByCampaign(campaignsService.findCampaignById(idCampaign));
    }

    @Override
    public List<Bonus> getBonusesByUser(long idUser){
        return bonusesRepo.findAllByUsers(usersService.findUserById(idUser));
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
        bonusesRepo.save(bonus);
        return new ResponseTextDto(ResponseMessages.SUCCESS);
    }
}
