package online.beautyskin.beauty.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.beautyskin.beauty.entity.LoyaltyPoint;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.repository.LoyaltyPointRepository;
import online.beautyskin.beauty.repository.UserRepository;

@Service
public class LoyaltyPointService {

    

    @Autowired
    private LoyaltyPointRepository loyaltyPointRepository;

    @Autowired
    private UserRepository userRepository;

    double SILVER_POINT = 5000000;
    double GOLD_POINT = 10000000;
    double DIAMOND_POINT = 20000000;

    public List<LoyaltyPoint> getAll() {
        return loyaltyPointRepository.findAll();
    }

    public List<User> getUserByRank(long id) {
        //LoyaltyPoint loyaltyPoint = loyaltyPointRepository.getReferenceById(id);
        List<User> users = userRepository.findByLoyaltyPointsIdAndIsDeletedFalse(id);
        return users;
    }

    public void updateRankForUser(User user) {
        LoyaltyPoint rank = loyaltyPointRepository.getReferenceById(calculateRank(user.getTotalAmount()));
        List<LoyaltyPoint> ranks = new ArrayList<>();
        ranks.add(rank);
        user.setLoyaltyPoints(ranks);
        userRepository.save(user);
    }

    public Long calculateRank(double point) {
        long rankId = 1;
        if(point > SILVER_POINT && point <= GOLD_POINT) {
            rankId = 2;
        } else if(point > GOLD_POINT && point <= DIAMOND_POINT) {
            rankId = 3;
        } else if(point > DIAMOND_POINT){
            rankId = 4;
        }
        return rankId;
    }

}
