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
    private String name;
    @Column(name = "Description")
    private String description;
    private boolean isDeleted = false;

    @ManyToMany(mappedBy = "skinTypes", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "skinType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Routine> routines;

    public SkinType() {
    }

    public SkinType(final String name, final String description) {
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

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }
}
