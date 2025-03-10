package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private long id;

    @OneToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "Quantity")
    @Min(1)
    private int quantity;
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "customerCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartDetails> cartDetails = new ArrayList<>();



    public CustomerCart() {}
    public long getId() { return id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
    public List<CartDetails> getCartDetails() { return cartDetails; }
    public void setCartDetails(List<CartDetails> cartDetails) { this.cartDetails = cartDetails; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
