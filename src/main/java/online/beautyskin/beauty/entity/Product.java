package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import online.beautyskin.beauty.enums.ProductEnums;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Product_Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Stock")
    private int stock;

    @Column(name = "CreateDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // Handles offset like +07:00

    private OffsetDateTime createDateTime;

    @Column(name = "LastUpdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // Handles offset like +07:00

    private OffsetDateTime lastUpdateDateTime;

    @Column(name = "ExpiredDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // Handles offset like +07:00

    private OffsetDateTime expiredDateTime;

    @Column(name = "Status")
    private ProductEnums status;
    @Column(name = "Instruction")
    private String instruction;

    private boolean isDeleted;

    private double price;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private CartDetails cartDetails;

//    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
//    private OrderDetail orderDetail;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<MappingProductImage> mappingProductImages = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Promotion> promotions = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<SkinType> skinTypes = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<SkinConcern> skinConcerns = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Form> forms = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<RoutineStep> routineSteps = new ArrayList<>();



    public List<MappingProductImage> getMappingProductImages() { return mappingProductImages; }
    public void setMappingProductImages(List<MappingProductImage> mappingProductImages) { this.mappingProductImages = mappingProductImages; }

    public List<Feedback> getFeedbacks() { return feedbacks; }
    public void setFeedbacks(List<Feedback> feedbacks) { this.feedbacks = feedbacks; }
    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        feedback.setProduct(this);
    }
    public void removeFeedback(Feedback feedback) {
        feedbacks.remove(feedback);
        feedback.setProduct(null);
    }

    public void setCategory(Category category) { this.category = category; }
    public Category getCategory() { return category; }


    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }


    public Product() {
    }

    public Product(String name, String description, int stock, OffsetDateTime createDateTime, OffsetDateTime lastUpdateDateTime, OffsetDateTime expiredDateTime, ProductEnums status, String instruction, boolean isDeleted) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.createDateTime = createDateTime;
        this.lastUpdateDateTime = lastUpdateDateTime;
        this.expiredDateTime = expiredDateTime;
        this.status = status;
        this.instruction = instruction;
        this.isDeleted = isDeleted;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public OffsetDateTime getCreateDateTime() { return createDateTime; }
    public void setCreateDateTime(OffsetDateTime createDateTime) { this.createDateTime = createDateTime; }
    public OffsetDateTime getLastUpdateDateTime() { return lastUpdateDateTime; }
    public void setLastUpdateDateTime(OffsetDateTime lastUpdateDateTime) { this.lastUpdateDateTime = lastUpdateDateTime; }
    public OffsetDateTime getExpiredDateTime() { return expiredDateTime; }
    public void setExpiredDateTime(OffsetDateTime expiredDateTime) { this.expiredDateTime = expiredDateTime; }
    public ProductEnums getStatus() { return status; }
    public void setStatus(ProductEnums status) { this.status = status; }
    public String getInstruction() { return instruction; }
    public void setInstruction(String instruction) { this.instruction = instruction; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public CartDetails getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(CartDetails cartDetails) {
        this.cartDetails = cartDetails;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<SkinType> getSkinTypes() {
        return skinTypes;
    }

    public void setSkinTypes(List<SkinType> skinTypes) {
        this.skinTypes = skinTypes;
    }

    public List<SkinConcern> getSkinConcerns() {
        return skinConcerns;
    }

    public void setSkinConcerns(List<SkinConcern> skinConcerns) {
        this.skinConcerns = skinConcerns;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public List<RoutineStep> getRoutineSteps() {
        return routineSteps;
    }

    public void setRoutineSteps(List<RoutineStep> routineSteps) {
        this.routineSteps = routineSteps;
    }

    public void addSkinTypes(SkinType type) {
        skinTypes.add(type);
    }

}
