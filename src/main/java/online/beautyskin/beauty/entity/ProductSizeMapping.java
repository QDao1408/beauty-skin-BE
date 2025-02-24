package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

@Entity(name = "ProductSizeMapping")
public class ProductSizeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MappingId")
    private long id;
    private double price;

    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "SizeId", nullable = false)
    private Size size;

    public ProductSizeMapping() {}
    public ProductSizeMapping(double price) {
        this.price = price;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public Size getSize() { return size; }
    public void setSize(Size size) { this.size = size; }
}
