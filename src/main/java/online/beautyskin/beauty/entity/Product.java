package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import online.beautyskin.beauty.entity.Category;
import online.beautyskin.beauty.enums.ProductEnums;
import online.beautyskin.beauty.enums.PromotionEnums;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.service.annotation.GetExchange;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Product_Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Stock")
    private BigDecimal stock;

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


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProductSizeMapping> productSizeMappings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Feedback> feedbacks = new ArrayList<>();

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

    public List<ProductSizeMapping> getProductSizeMappings() {
        return productSizeMappings;
    }
    public void setProductSizeMappings(List<ProductSizeMapping> productSizeMappings) {
        this.productSizeMappings = productSizeMappings;
    }

    public void addProductSizeMapping(ProductSizeMapping productSizeMapping) {
        productSizeMappings.add(productSizeMapping);
        productSizeMapping.setProduct(this);
    }

    public void removeProductSizeMapping(ProductSizeMapping productSizeMapping) {
        productSizeMappings.remove(productSizeMapping);
        productSizeMapping.setProduct(null);
    }

    public Product() {
    }

    public Product(String name, String description, BigDecimal stock, OffsetDateTime createDateTime, OffsetDateTime lastUpdateDateTime, OffsetDateTime expiredDateTime, ProductEnums status, String instruction, boolean isDeleted) {
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
    public BigDecimal getStock() { return stock; }
    public void setStock(BigDecimal stock) { this.stock = stock; }
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


}
