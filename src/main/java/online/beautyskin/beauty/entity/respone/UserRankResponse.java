package online.beautyskin.beauty.entity.respone;

public class UserRankResponse {
    private long id;
    private String rankName;
    private double amountLevel;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getRankName() {
        return rankName;
    }
    public void setRankName(String rankName) {
        this.rankName = rankName;
    }
    public double getAmountLevel() {
        return amountLevel;
    }
    public void setAmountLevel(double amountLevel) {
        this.amountLevel = amountLevel;
    }
    public UserRankResponse() {
    }

    
    
}
