package online.beautyskin.beauty.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
@Entity
public class LoyaltyPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String rankName; // dong, bac, vang, kim cuong
    private long amountLevel; // 5tr, 10tr, 20tr, 30tr

    @OneToOne(mappedBy = "loyaltyPoint")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "loyaltyPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Promotion> promotions = new ArrayList<>();

    public LoyaltyPoint() {}

    public long getId() {
        return id;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public long getAmountLevel() {
        return amountLevel;
    }

    public void setAmountLevel(long amountLevel) {
        this.amountLevel = amountLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
    
    public void addPromotion(Promotion promotion) {
        this.promotions.add(promotion);
        promotion.setLoyaltyPoint(this);
    }

    public void removePromotion(Promotion promotion) {
        this.promotions.remove(promotion);
        promotion.setLoyaltyPoint(null);
    }
    
}
