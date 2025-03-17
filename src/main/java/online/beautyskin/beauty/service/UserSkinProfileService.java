package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.SkinType;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.UserSkinProfile;
import online.beautyskin.beauty.exception.NotFoundException;
import online.beautyskin.beauty.repository.SkinTypeRepository;
import online.beautyskin.beauty.repository.UserSkinProfileRepository;
import online.beautyskin.beauty.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserSkinProfileService {

    @Autowired
    private UserSkinProfileRepository userSkinProfileRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private SkinTypeRepository skinTypeRepository;

    public UserSkinProfile create(int skinPoint) {
        long skinTypeId = 0;
        if(skinPoint <= 11) {
            skinTypeId = 2;
        } else if(skinPoint > 11 && skinPoint <= 20) {
            skinTypeId = 5;
        } else if(skinPoint > 20 && skinPoint <= 26) {
            skinTypeId = 1;
        } else if(skinPoint > 26 && skinPoint <= 35) {
            skinTypeId = 4;
        } else {
            skinTypeId = 3;
        }
        SkinType skinType = skinTypeRepository.findById(skinTypeId);
        User user = userUtils.getCurrentUser();
        UserSkinProfile profile = userSkinProfileRepository.findByUser(user);
        if(profile != null) {
            profile.setLastUpdate(LocalDateTime.now());
            profile.setSkinType(skinType);
            return userSkinProfileRepository.save(profile);
        } else {
            UserSkinProfile profile1 = new UserSkinProfile();
            profile1.setLastUpdate(LocalDateTime.now());
            profile1.setSkinType(skinType);
            profile1.setUser(user);
            return userSkinProfileRepository.save(profile1);
        }
    }

    public List<UserSkinProfile> getAll() {
        return userSkinProfileRepository.findAll();
    }

    public UserSkinProfile getById(long id) {
        return userSkinProfileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Skin Profile not found"));
    }

}
