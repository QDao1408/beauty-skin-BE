package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.LoyaltyPoint;
import online.beautyskin.beauty.entity.Promotion;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.PromoRequest;
import online.beautyskin.beauty.repository.LoyaltyPointRepository;
import online.beautyskin.beauty.repository.OrderRepository;
import online.beautyskin.beauty.repository.PromotionRepository;
import online.beautyskin.beauty.utils.UserUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private LoyaltyPointRepository loyaltyPointRepository;

    @Autowired
    private UserUtils userUtils;

    public List<Promotion> getValidPromotions() {
        List<Promotion> promotions = promotionRepository
                .findAllByNumOfPromoIsGreaterThanAndIsOutDateFalseAndIsDeletedFalse(0);
        return promotions;
    }

    public List<Promotion> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAllByIsDeletedFalse();
        return promotions;
    }

    public Promotion createPromotion(PromoRequest request) {
        Promotion promotion = new Promotion();
        promotion.setDeleted(false);
        promotion.setDescription(request.getDescription());
        promotion.setEndDate(request.getEndDate());
        promotion.setLoyaltyPoint(getLoyaltyPoint(request.getRank()));
        promotion.setStartDate(request.getStartDate());
        promotion.setName(request.getName());
        promotion.setNumOfPromo(request.getNumOfPromo());
        promotion.setPromoAmount(request.getPromoAmount());
        promotion.setOrderPrice(request.getOrderPrice());
        promotion.setOutDate();
        return promotionRepository.save(promotion);
    }

    @Scheduled(fixedRate = 60000 * 60) // run every hour
    public void updateOutDate() {
        List<Promotion> promotions = promotionRepository
                .findAllByNumOfPromoIsGreaterThanAndIsOutDateFalseAndIsDeletedFalse(0);
        for (Promotion promotion : promotions) {
            promotion.setOutDate();
        }
    }

    public Promotion updatePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Promotion deletePromotion(Promotion promotion) {
        promotion.setDeleted(true);
        return promotionRepository.save(promotion);
    }

    public LoyaltyPoint getLoyaltyPoint(long rank) {
        return loyaltyPointRepository.getReferenceById(rank);
    }

    public List<Promotion> getByUserRank() {
        User user = userUtils.getCurrentUser();
        long id = user.getLoyaltyPoint().getId();
        return promotionRepository.findAllByLoyaltyPointId(id);
    }

}
