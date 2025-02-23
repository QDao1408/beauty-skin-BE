package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "BrandName")
    private String name;
    @Column(name = "Description")
    private String description;

    public Brand() {}
    public Brand(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
