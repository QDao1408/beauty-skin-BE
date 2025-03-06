package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SkinType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private Long id;
    @Column(name = "Type")
    private String typeName;
    @Column(name = "Description")
    private String description;
    private boolean isDeleted = false;



    @ManyToMany
    @JoinTable(
            name = "mapping_product_skinType",
            joinColumns = @JoinColumn(name = "type_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private List<Product> products = new ArrayList<Product>();

    @OneToOne(mappedBy = "skinType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Routine routine;

    public SkinType() {
    }

    public SkinType(final String typeName, final String description) {
        this.typeName = typeName;
        this.description = description;
    }

    public long getId() {
        return id;
    }



    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void addProducts(Product product) {
        this.products.add(product);
        product.addSkinType2(this);
    }
    public void addProducts2(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.removeSkinType2(this);
    }
    public void removeProduct2(Product product) {
        this.products.remove(product);
    }
}
