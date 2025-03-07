package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "TagName", unique = true, nullable = false)
    private String name;
    private boolean isDeleted = false;


    @ManyToMany(mappedBy = "tags", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private List<Product> products = new ArrayList<Product>();

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
}
