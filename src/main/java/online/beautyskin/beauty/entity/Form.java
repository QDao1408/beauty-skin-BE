package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "FormName", nullable = false, unique = true)
    private String name;
    @Column(name = "Description")
    private String description;
    private boolean isDeleted = false;

    @ManyToMany
    @JoinTable(
            name = "mapping_product_form",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public Form() {}
    public Form(String name, String description) { this.name = name; this.description = description; }
    public long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
}
