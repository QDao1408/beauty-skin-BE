package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;


@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailId")
    private long orderDetailId = 0;
    @Column(name = "Quantity")
    @Min(1)
    private int quantity;
    @Column(name = "UnitPrice")
    @Min(0)
    private double unitPrice;
    @Column(name = "TotalPrice")
    @Min(0)
    private double totalPrice;
    
    @Column(name = "is_feedback")
    private boolean feedback = false;

    @ManyToOne
    @JoinColumn(name = "product_id")
    
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderDetail() {}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getOrderDetailId() { return orderDetailId; }
    public void setOrderDetailId(long id) { this.orderDetailId = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public boolean isFeedback() {
        return feedback;
    }

    public void setFeedback(boolean feedback) {
        this.feedback = feedback;
    }
}
