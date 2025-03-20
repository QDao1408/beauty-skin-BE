package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
@Entity
public class LoyaltyPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String rankName; // dong, bac, vang, kim cuong
    private long amountLevel; // 5tr, 10tr, 20tr, 30tr

    @OneToOne(mappedBy = "loyaltyPoint")
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

    public long getAmountLevel() {
        return amountLevel;
    }

    public void setAmountLevel(long amountLevel) {
        this.amountLevel = amountLevel;
        // hello this is me
    }
}
