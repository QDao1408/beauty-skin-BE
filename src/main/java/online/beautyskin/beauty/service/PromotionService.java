package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Promotion;
import online.beautyskin.beauty.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> getValidPromotions() {
        List<Promotion> promotions = promotionRepository.findAllByIsOutDateFalseAndIsDeletedFalse();
        return promotions;
    }

    public List<Promotion> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAllByIsDeletedFalse();
        return promotions;
    }

    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Promotion updatePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Promotion deletePromotion(Promotion promotion) {
        promotion.setDeleted(true);
        return promotionRepository.save(promotion);
    }
}
