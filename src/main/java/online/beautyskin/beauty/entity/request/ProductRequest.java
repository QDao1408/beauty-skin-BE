package online.beautyskin.beauty.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import online.beautyskin.beauty.entity.SkinType;
import online.beautyskin.beauty.enums.ProductEnums;
import org.apache.catalina.LifecycleState;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public class ProductRequest {
    private String name;

    private String description;

    private int stock;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // Handles offset like +07:00

    private OffsetDateTime createDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // Handles offset like +07:00
    private OffsetDateTime lastUpdateDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") // Handles offset like +07:00
    private OffsetDateTime expiredDateTime;

    private ProductEnums status;
    private String instruction;

    private boolean isDeleted;

    private long brandId;

    private long categoryId;

    private List<Long> skinTypeId;

    private List<Long> skinConcernId;

    private List<Long> tagId;

    private List<Long> routineSteps;

    private List<Long> forms;

    public ProductRequest() {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public OffsetDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(OffsetDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public OffsetDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(OffsetDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public OffsetDateTime getExpiredDateTime() {
        return expiredDateTime;
    }

    public void setExpiredDateTime(OffsetDateTime expiredDateTime) {
        this.expiredDateTime = expiredDateTime;
    }

    public ProductEnums getStatus() {
        return status;
    }

    public void setStatus(ProductEnums status) {
        this.status = status;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getSkinTypeId() {
        return skinTypeId;
    }

    public void setSkinTypeId(List<Long> skinTypeId) {
        this.skinTypeId = skinTypeId;
    }

    public List<Long> getSkinConcernId() {
        return skinConcernId;
    }

    public void setSkinConcernId(List<Long> skinConcernId) {
        this.skinConcernId = skinConcernId;
    }

    public List<Long> getTagId() {
        return tagId;
    }

    public void setTagId(List<Long> tagId) {
        this.tagId = tagId;
    }

    public List<Long> getRoutineSteps() {
        return routineSteps;
    }

    public void setRoutineSteps(List<Long> routineSteps) {
        this.routineSteps = routineSteps;
    }

    public List<Long> getForms() {
        return forms;
    }

    public void setForms(List<Long> forms) {
        this.forms = forms;
    }
}
