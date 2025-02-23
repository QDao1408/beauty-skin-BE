package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "TagName", unique = true, nullable = false)
    private String name;
    private boolean isDeleted = false;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }
    public long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
}
