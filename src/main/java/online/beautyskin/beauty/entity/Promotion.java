package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "StartDate")
    private LocalDate startDate;
    @Column(name = "EndDate")
    private LocalDate endDate;
    @Column(name = "Description")
    private String description;
    @Column(name = "PromotionAmount")
    private double promoAmount;
    private boolean isDeleted = false;
    private boolean isOutDate;
    private double orderPrice;
    private int numOfPromo;

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private LoyaltyPoint loyaltyPoint;

    @OneToMany(mappedBy = "promotion")
    private List<OrderPromo> orderPromotions;

    @AssertTrue(message = "Promotion's end date must be after the start date.")
    public boolean isEndDateAfterStartDate() {
        if (startDate == null || endDate == null) {
            return true; // Skip validation if either date is null
        }
        return endDate.isAfter(startDate);
    }

    public Promotion() {}


    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LoyaltyPoint getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(LoyaltyPoint loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }

    public int getNumOfPromo() {
        return numOfPromo;
    }

    public void setNumOfPromo(int numOfPromo) {
        this.numOfPromo = numOfPromo;
    }

    public boolean isOutDate() {
        return isOutDate;
    }

    public void setOutDate() {
        this.isOutDate = endDate.isAfter(LocalDate.now());
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public OrderPromo getOrderPromo() {
        return orderPromo;
    }

    
    
}
