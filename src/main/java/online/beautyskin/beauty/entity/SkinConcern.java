package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SkinConcern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ConcernID", nullable = false)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    private boolean isDeleted;

//    @ManyToMany
//    @JoinTable(
//            name = "mapping_product_skinConcern",
//            joinColumns = @JoinColumn(name = "concern_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
    @ManyToMany(mappedBy = "skinConcerns", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Product> products = new ArrayList<Product>();

    public SkinConcern() {
    }

    public SkinConcern(String name, String description) {
        this.name = name;
        this.description = description;
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
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}
