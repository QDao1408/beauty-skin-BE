package online.beautyskin.beauty.entity.respone;

import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.enums.ProductEnums;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private int stock;
    private OffsetDateTime createDateTime;
    private OffsetDateTime lastUpdateDateTime;
    private OffsetDateTime expiredDateTime;
    private ProductEnums status;
    private String instruction;
    private boolean isDeleted = false;
    private double price;
    private String ingredient;
    private Category category;
    private List<SkinType> skinTypes = new ArrayList<>();
    private List<SkinConcern> skinConcerns = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    private List<Form> forms = new ArrayList<>();
    private List<RoutineStep> routineSteps = new ArrayList<>();
    private List<Image> images = new ArrayList<>();
    private List<Favorites> favoritedByUsers = new ArrayList<>();
    private Double averageRating;
    private Long productSold;
    private double promotion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCreateDateTime(OffsetDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public void setLastUpdateDateTime(OffsetDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public void setExpiredDateTime(OffsetDateTime expiredDateTime) {
        this.expiredDateTime = expiredDateTime;
    }

    public void setStatus(ProductEnums status) {
        this.status = status;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSkinTypes(List<SkinType> skinTypes) {
        this.skinTypes = skinTypes;
    }

    public void setSkinConcerns(List<SkinConcern> skinConcerns) {
        this.skinConcerns = skinConcerns;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public void setRoutineSteps(List<RoutineStep> routineSteps) {
        this.routineSteps = routineSteps;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setFavoritedByUsers(List<Favorites> favoritedByUsers) {
        this.favoritedByUsers = favoritedByUsers;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void setProductSold(Long productSold) {
        this.productSold = productSold;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public OffsetDateTime getCreateDateTime() {
        return createDateTime;
    }

    public OffsetDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public OffsetDateTime getExpiredDateTime() {
        return expiredDateTime;
    }

    public ProductEnums getStatus() {
        return status;
    }

    public String getInstruction() {
        return instruction;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public double getPrice() {
        return price;
    }

    public String getIngredient() {
        return ingredient;
    }

    public Category getCategory() {
        return category;
    }

    public List<SkinType> getSkinTypes() {
        return skinTypes;
    }

    public List<SkinConcern> getSkinConcerns() {
        return skinConcerns;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Form> getForms() {
        return forms;
    }

    public List<RoutineStep> getRoutineSteps() {
        return routineSteps;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Favorites> getFavoritedByUsers() {
        return favoritedByUsers;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public Long getProductSold() {
        return productSold;
    }

    public ProductResponse() {
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }
}
