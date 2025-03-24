package online.beautyskin.beauty.entity.request;

import java.time.LocalDate;


public class PromoRequest {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private double promoAmount;
    private int numOfPromo;
    private long rank;

    public PromoRequest() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
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

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    
}
