package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class LoyaltyPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String rankName; // dong, bac, vang
    private double discount; // 5%, 7%, 10%
    private long amountLevel; // 5tr, 15tr, 30tr

    @OneToOne(mappedBy = "loyaltyPoint", cascade = CascadeType.ALL)
    private User user;

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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getAmountLevel() {
        return amountLevel;
    }

    public void setAmountLevel(long amountLevel) {
        this.amountLevel = amountLevel;
    }
}
