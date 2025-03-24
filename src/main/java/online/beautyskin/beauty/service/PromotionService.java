package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.LoyaltyPoint;
import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.OrderPromo;
import online.beautyskin.beauty.entity.Promotion;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.PromoRequest;
import online.beautyskin.beauty.exception.NotFoundException;
import online.beautyskin.beauty.repository.LoyaltyPointRepository;
import online.beautyskin.beauty.repository.OrderPromoRepository;
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
    private OrderRepository orderRepository;

    @Autowired
    private OrderPromoRepository orderPromoRepository;

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
/*
    public String applyPromoForOrder(long promoId, long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Promotion promo = promotionRepository
                .findAllByIdAndNumOfPromoIsGreaterThanAndIsOutDateFalseAndIsDeletedFalse(promoId, 0)
                .orElseThrow(() -> new NotFoundException("Cannot found promotion"));
        User curUser = userUtils.getCurrentUser();
        double discountPrice = 0;
        OrderPromo checkOrder = orderPromoRepository.findByOrder(order).orElse(null);
        if(checkOrder != null) {
            throw new NotFoundException("This order is already applied promotion");
        }
        // check user rank
        if(curUser.getLoyaltyPoint().getId() != promo.getLoyaltyPoint().getId()) {
            throw new NotFoundException("You can not use this promotion");
        }
        
        if(promo.getPromoAmount() < 1) { // discount by percentage
            discountPrice = order.getTotalPrice() * promo.getPromoAmount();
        } else { // discount by amount
            discountPrice = promo.getPromoAmount();
        }
        // check min price of order to apply promotion
        if(order.getTotalPrice() >= promo.getOrderPrice()) { // able to apply promotion
            // save
            order.setTotalPrice(order.getTotalPrice() - discountPrice);
            orderRepository.save(order);
            // save
            promo.setNumOfPromo(promo.getNumOfPromo() - 1);
            promotionRepository.save(promo);

            OrderPromo orderPromo = new OrderPromo(order, promo, discountPrice);
            orderPromoRepository.save(orderPromo);
        } else {
            throw new NotFoundException("Order does not meet the min price to apply promotion");
        }

        return "Promotion apply successfully";
    }

    public String removePromoForOrder(long promoId, long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        Promotion promo = promotionRepository
                .findAllByIdAndNumOfPromoIsGreaterThanAndIsOutDateFalseAndIsDeletedFalse(promoId, 0)
                .orElseThrow(() -> new NotFoundException("Wrong promotion id"));

        OrderPromo checkOrder = orderPromoRepository.findByOrder(order)
        .orElseThrow(() -> new NotFoundException("Order has not apply any promotion yet"));

        double discountPrice = checkOrder.getDiscountPrice();
        // check if the order apply this promotion
        if(checkOrder.getPromotion().getId() != promoId) {
            throw new NotFoundException("This order applied another promotion");
        }
        
        // restore the order price and promotion 
        order.setTotalPrice(order.getTotalPrice() + discountPrice);
        orderRepository.save(order);

        promo.setNumOfPromo(promo.getNumOfPromo() + 1);
        promotionRepository.save(promo);

        return "Promotion remove successfully";
    }
    */


}
