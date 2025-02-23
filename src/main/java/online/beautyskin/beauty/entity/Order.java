package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.enums.PaymentStatusEnums;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "OrderDate", nullable = false)
    private LocalDateTime orderDate;
    @Column(name = "OrderStatus")
    private OrderStatusEnums orderStatus;
    @Column(name = "PaymentStatus")
    private PaymentStatusEnums paymentStatus;
    @Column(name = "Amount")
    @Min(0)
    private double amount;

    public Order() {}
    public long getId() { return id; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public OrderStatusEnums getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatusEnums orderStatus) { this.orderStatus = orderStatus; }
    public PaymentStatusEnums getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatusEnums paymentStatus) { this.paymentStatus = paymentStatus; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

}
