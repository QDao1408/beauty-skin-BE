package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "Volume", nullable = false)
    @Min(0)
    private int volume;
    @Column(name = "Weight", nullable = false)
    @Min(0)
    private int weight;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSizeMapping> productSizeMappings = new ArrayList<>();

    public Size() {}
    public Size(int volume, int weight) { this.volume = volume; this.weight = weight; }
    public long getId() { return id; }
    public int getVolume() { return volume; }
    public void setVolume(int volume) { this.volume = volume; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
    public List<ProductSizeMapping> getProductSizeMappings() { return productSizeMappings; }
    public void setProductSizeMappings(List<ProductSizeMapping> productSizeMappings) {
        this.productSizeMappings = productSizeMappings;
    }
    public void addProductSizeMapping(ProductSizeMapping productSizeMapping) {
        productSizeMappings.add(productSizeMapping);
        productSizeMapping.setSize(this);
    }
    public void removeProductSizeMapping(ProductSizeMapping productSizeMapping) {
        productSizeMappings.remove(productSizeMapping);
        productSizeMapping.setSize(null);
    }

}
