package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jdk.jfr.Category;
import online.beautyskin.beauty.enums.PromotionEnums;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.service.annotation.GetExchange;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDateTime;

    @Column(name = "LastUpdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdateDateTime;

    @Column(name = "ExpiredDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredDateTime;

    @Column(name = "Status")
    private PromotionEnums status;
    @Column(name = "Instruction")
    private String instruction;

    private boolean isDeleted;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ProductSizeMapping> productSizeMappings = new ArrayList<>();

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

    public Product(String name, String description, BigDecimal stock, LocalDateTime createDateTime, LocalDateTime lastUpdateDateTime, LocalDateTime expiredDateTime, PromotionEnums status, String instruction, boolean isDeleted) {
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
    public LocalDateTime getCreateDateTime() { return createDateTime; }
    public void setCreateDateTime(LocalDateTime createDateTime) { this.createDateTime = createDateTime; }
    public LocalDateTime getLastUpdateDateTime() { return lastUpdateDateTime; }
    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) { this.lastUpdateDateTime = lastUpdateDateTime; }
    public LocalDateTime getExpiredDateTime() { return expiredDateTime; }
    public void setExpiredDateTime(LocalDateTime expiredDateTime) { this.expiredDateTime = expiredDateTime; }
    public PromotionEnums getStatus() { return status; }
    public void setStatus(PromotionEnums status) { this.status = status; }
    public String getInstruction() { return instruction; }
    public void setInstruction(String instruction) { this.instruction = instruction; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
}
