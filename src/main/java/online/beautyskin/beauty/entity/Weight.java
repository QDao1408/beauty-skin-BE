package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "Weight", nullable = false)
    @Min(0)
    private int weight;
    private boolean isDeleted = false;

    public Weight() {}
    public Weight(int weight) { this.weight = weight; }
    public long getId() { return id; }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
}
