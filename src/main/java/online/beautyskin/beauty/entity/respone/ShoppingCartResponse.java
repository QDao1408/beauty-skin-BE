package online.beautyskin.beauty.entity.respone;

import online.beautyskin.beauty.entity.CartDetails;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartResponse {
    private List<CartDetails> items;
    private double totalPrice;

    public ShoppingCartResponse(List<CartDetails> items, double totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public List<CartDetails> getItems() {
        return items;
    }

    public void setItems(List<CartDetails> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
