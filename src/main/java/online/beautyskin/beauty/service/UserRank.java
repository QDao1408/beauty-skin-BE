package online.beautyskin.beauty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.repository.UserRankRepository;
import online.beautyskin.beauty.repository.UserRepository;

@Service
public class UserRank {

    

    @Autowired
    private UserRankRepository userRankRepository;

    @Autowired
    private UserRepository userRepository;

    double SILVER_POINT = 5000000;
    double GOLD_POINT = 10000000;
    double DIAMOND_POINT = 20000000;

    public List<online.beautyskin.beauty.entity.UserRank> getAll() {
        return userRankRepository.findAll();
    }

    public List<User> getUserByRank(long id) {
        online.beautyskin.beauty.entity.UserRank userRank = userRankRepository.getReferenceById(id);
        return userRepository.findByUserRankAndIsDeletedFalse(userRank);
    }

    public void updateRankForUser(User user) {
        long rankId = calculateRank(user.getTotalAmount());
        online.beautyskin.beauty.entity.UserRank rank = userRankRepository.getReferenceById(rankId);
        user.setUserRank(rank);
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

    public online.beautyskin.beauty.entity.UserRank createRank(online.beautyskin.beauty.entity.UserRank userRank) {
        return userRankRepository.save(userRank);
    }
}
