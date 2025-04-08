package online.beautyskin.beauty.entity.respone;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import online.beautyskin.beauty.entity.UserRank;

public class PromoResponse {

    private long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime endDate;
    private String description;
    private double promoAmount;
    private double orderPrice;
    private int numOfPromo;
    private UserRankResponse userRank;
    
    public PromoResponse() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public OffsetDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }
    public OffsetDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPromoAmount() {
        return promoAmount;
    }
    public void setPromoAmount(double promoAmount) {
        this.promoAmount = promoAmount;
    }
    public int getNumOfPromo() {
        return numOfPromo;
    }
    public void setNumOfPromo(int numOfPromo) {
        this.numOfPromo = numOfPromo;
    }
    public double getOrderPrice() {
        return orderPrice;
    }
    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
    public UserRankResponse getUserRank() {
        return userRank;
    }
    public void setUserRank(UserRankResponse userRank) {
        this.userRank = userRank;
    }

    
    
}
