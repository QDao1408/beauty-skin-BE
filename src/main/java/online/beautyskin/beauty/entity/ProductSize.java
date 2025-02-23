package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class ProductSize {
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
    @Column(name = "Price", nullable = false)
    @Min(0)
    private double price;
    private boolean isDeleted = false;

    public ProductSize() {}
    public ProductSize(int volume, int weight, double price) { this.volume = volume; this.weight = weight; this.price = price; }
    public long getId() { return id; }
    public int getVolume() { return volume; }
    public void setVolume(int volume) { this.volume = volume; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
