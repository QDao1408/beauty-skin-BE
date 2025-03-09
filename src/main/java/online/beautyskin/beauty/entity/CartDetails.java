package online.beautyskin.beauty.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;


@Entity
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "LastUpdate")
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CustomerCart customerCart;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private boolean isDeleted = false;
    public CartDetails() {}
    public CartDetails(int quantity, LocalDateTime lastUpdate) {
        this.quantity = quantity;
        this.lastUpdate = lastUpdate;
    }

    public long getId() { return id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
    public CustomerCart getCustomerCart() { return customerCart; }
    public void setCustomerCart(CustomerCart customerCart) { this.customerCart = customerCart; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
