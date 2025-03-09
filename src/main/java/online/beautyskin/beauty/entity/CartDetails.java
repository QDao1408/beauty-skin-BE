package online.beautyskin.beauty.entity;

import jakarta.persistence.*;


import java.math.BigDecimal;
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

    private BigDecimal subtotal;

    private boolean isDeleted = false;
    public CartDetails() {}

    public CartDetails(long id, int quantity, LocalDateTime lastUpdate, CustomerCart customerCart, Product product, BigDecimal subtotal, boolean isDeleted) {
        this.id = id;
        this.quantity = quantity;
        this.lastUpdate = lastUpdate;
        this.customerCart = customerCart;
        this.product = product;
        this.subtotal = subtotal;
        this.isDeleted = isDeleted;
    }

    public CartDetails(CustomerCart cart, Product product, int quantity, BigDecimal subtotal) {
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
    public void updateSubtotal() {
        this.subtotal = BigDecimal.valueOf(product.getPrice() * quantity);
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
