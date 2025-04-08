package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import online.beautyskin.beauty.enums.ProductEnums;

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

    @Lob  // Marks this as a large object (TEXT/BLOB)
    @Column(columnDefinition = "TEXT") // Optional, ensures it maps to MySQL TEXT
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
    @Enumerated(EnumType.STRING)
    private ProductEnums status;

    @Lob  // Marks this as a large object (TEXT/BLOB)
    @Column(columnDefinition = "TEXT") // Optional, ensures it maps to MySQL TEXT
    private String instruction;

    private boolean isDeleted;

    private double price;

    @Lob  // Marks this as a large object (TEXT/BLOB)
    @Column(columnDefinition = "TEXT") // Optional, ensures it maps to MySQL TEXT
    private String ingredient;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();


    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Min(0)
    @Max(1)
    private double promotion;

//    @ManyToMany(mappedBy = "products", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    private List<SkinType> skinTypes = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "mapping_product_skinType",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private List<SkinType> skinTypes = new ArrayList<>();


//    @ManyToMany(mappedBy = "products")
    @ManyToMany
    @JoinTable(
            name = "mapping_product_skinConcern",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "concern_id")
    )
    private List<SkinConcern> skinConcerns = new ArrayList<>();

//    @ManyToMany(mappedBy = "products")
    @ManyToMany
    @JoinTable(
            name = "mapping_product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

    //    @ManyToMany(mappedBy = "products")
    @ManyToMany
    @JoinTable(
            name = "mapping_product_form",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "form_id")
    )
    private List<Form> forms = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<RoutineStep> routineSteps = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "mapping_product_image",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
//    @ManyToMany(mappedBy = "products")
    private List<Image> images = new ArrayList<>();

    @ManyToMany(mappedBy = "favoriteProducts")
    private List<Favorites> favoritedByUsers = new ArrayList<>();

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

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<SkinType> getSkinTypes() {
        return skinTypes;
    }

    public void addSkinType(SkinType type) {
        this.skinTypes.add(type);
        type.addProducts2(this);
    }

    public void removeSkinType(SkinType type) {
        this.skinTypes.remove(type);
        type.removeProduct2(this);
    }
    public void addSkinType2(SkinType type) {
        this.skinTypes.add(type);
    }

    public void removeSkinType2(SkinType type) {
        this.skinTypes.remove(type);
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


    public void setSkinTypes(List<SkinType> skinTypes) {
        this.skinTypes = skinTypes;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public List<Favorites> getFavoritedByUsers() {
        return favoritedByUsers;
    }

    public void setFavoritedByUsers(List<Favorites> favoritedByUsers) {
        this.favoritedByUsers = favoritedByUsers;
    }

    public double getPromotion() {
        return promotion;
    }
    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }
}