package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import online.beautyskin.beauty.enums.PromotionEnums;

import java.time.LocalDate;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "PromotionType")
    private PromotionEnums type;
    @Column(name = "ApplyFor")
    private PromotionEnums apply;
    @Column(name = "PromotionAmount")
    private double promoAmount;

    @AssertTrue(message = "Promotion's end date must be after the start date.")
    public boolean isEndDateAfterStartDate() {
        if (startDate == null || endDate == null) {
            return true; // Skip validation if either date is null
        }
        return endDate.isAfter(startDate);
    }

    public Promotion() {}

    public Promotion(String name, LocalDate startDate, LocalDate endDate, String description, PromotionEnums type, PromotionEnums apply, double promoAmount) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.type = type;
        this.apply = apply;
        this.promoAmount = promoAmount;
    }


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

    public PromotionEnums getType() {
        return type;
    }

    public void setType(PromotionEnums type) {
        this.type = type;
    }

    public PromotionEnums getApply() {
        return apply;
    }

    public void setApply(PromotionEnums apply) {
        this.apply = apply;
    }

    public double getPromoAmount() {
        return promoAmount;
    }

    public void setPromoAmount(double promoAmount) {
        this.promoAmount = promoAmount;
    }
}
