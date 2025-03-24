package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import online.beautyskin.beauty.enums.PromotionEnums;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Column(name = "PromotionType")
    private PromotionEnums type;
    @Column(name = "PromotionAmount")
    private double promoAmount;
    private boolean isDeleted = false;
    private boolean isOutDate;
    private int numOfPromo;

    @ManyToMany
    @JoinTable(
            name = "mapping_product_promo",
            joinColumns = @JoinColumn(name = "promo_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<Product>();

    @ManyToMany
    @JoinTable(
            name = "mapping_order_promo",
            joinColumns = @JoinColumn(name = "promo_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "rank_id")
    private LoyaltyPoint loyaltyPoint;


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

    public PromotionEnums getType() {
        return type;
    }

    public void setType(PromotionEnums type) {
        this.type = type;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
        isOutDate = endDate.isAfter(LocalDate.now());
    }
}
