package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OrderDetailId")
    private long orderDetailId;
    @Column(name = "Quantity")
    @Min(1)
    private int quantity;
    @Column(name = "UnitPrice")
    @Min(0)
    private double unitPrice;
    @Column(name = "TotalAmount")
    @Min(0)
    private double totalPrice;

    public OrderDetail() {}

    public long getOrderDetailId() { return orderDetailId; }
    public void setOrderDetailId(long id) { this.orderDetailId = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
