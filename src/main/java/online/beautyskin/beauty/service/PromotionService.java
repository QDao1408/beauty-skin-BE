package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.UserRank;
import online.beautyskin.beauty.entity.Promotion;
import online.beautyskin.beauty.entity.request.PromoRequest;
import online.beautyskin.beauty.entity.respone.PromoResponse;
import online.beautyskin.beauty.entity.respone.UserRankResponse;
import online.beautyskin.beauty.repository.UserRankRepository;
import online.beautyskin.beauty.repository.PromotionRepository;
import online.beautyskin.beauty.utils.UserUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private UserRankRepository userRankRepository;

    @Autowired
    private UserUtils userUtils;

    public List<PromoResponse> getValidPromotions() {
        List<Promotion> promotions = promotionRepository.findAllByNumOfPromoIsGreaterThanAndIsOutDateFalseAndIsDeletedFalse(0);
        List<PromoResponse> responses = new ArrayList<>();
        for(Promotion promo : promotions) {
            responses.add(mappingPromoResponse(promo));
        }
        return responses;
    }

    public PromoResponse mappingPromoResponse(Promotion promotion) {
        PromoResponse response = new PromoResponse();
        response.setDescription(promotion.getDescription());
        response.setEndDate(promotion.getEndDate());
        response.setId(promotion.getId());
        response.setName(promotion.getName());
        response.setNumOfPromo(promotion.getNumOfPromo());
        response.setPromoAmount(promotion.getPromoAmount());
        response.setStartDate(promotion.getStartDate());
        response.setUserRank(mappingUserRankResponse(promotion.getUserRank()));
        response.setOrderPrice(promotion.getOrderPrice());
        return response;
    }

    public UserRankResponse mappingUserRankResponse(UserRank userRank) {
        UserRankResponse response = new UserRankResponse();
        response.setAmountLevel(userRank.getAmountLevel());
        response.setId(userRank.getId());
        response.setRankName(userRank.getRankName());
        return response;
    }

    public Promotion createPromotion(PromoRequest request) {
        Promotion promotion = new Promotion();
        promotion.setDeleted(false);
        promotion.setDescription(request.getDescription());
        promotion.setEndDate(request.getEndDate());
        promotion.setUserRank(userRankRepository.findById(request.getRank()));
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

    public Promotion updatePromotion(PromoRequest request, long id) {
        Promotion promotion = promotionRepository.findById(id);
        promotion.setDescription(request.getDescription());
        promotion.setEndDate(request.getEndDate());
        promotion.setUserRank(userRankRepository.findById(request.getRank()));
        promotion.setStartDate(request.getStartDate());
        promotion.setName(request.getName());
        promotion.setNumOfPromo(request.getNumOfPromo());
        promotion.setPromoAmount(request.getPromoAmount());
        promotion.setOrderPrice(request.getOrderPrice());
        promotion.setOutDate();
        return promotionRepository.save(promotion);
    }

    public Promotion deletePromotion(long id) {
        Promotion promotion = promotionRepository.findById(id);
        promotion.setDeleted(true);
        return promotionRepository.save(promotion);
    }


    public List<Promotion> getPromotionByRank(long rankId) {
        List<Promotion> promotions = promotionRepository.findByUserRank(userRankRepository.findById(rankId));
        return promotions;
    }
}
