package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "StartDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime startDate;
    @Column(name = "EndDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime endDate;
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
    private UserRank userRank;

    @OneToMany(mappedBy = "promotion", orphanRemoval = true)
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public UserRank getUserRank() {
        return userRank;
    }

    public void setUserRank(UserRank userRank) {
        this.userRank = userRank;
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
        this.isOutDate = endDate.isBefore(OffsetDateTime.now());
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Order> getOrders() {
        return orders;
    }

    

    
    
}
