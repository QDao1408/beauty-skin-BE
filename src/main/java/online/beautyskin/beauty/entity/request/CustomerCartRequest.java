package online.beautyskin.beauty.entity.request;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerCartRequest {
    private int quantity;
    private LocalDateTime lastUpdate;
    private List<CartDetailsRequest> cartDetails;

    // Getters and Setters

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<CartDetailsRequest> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetailsRequest> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
