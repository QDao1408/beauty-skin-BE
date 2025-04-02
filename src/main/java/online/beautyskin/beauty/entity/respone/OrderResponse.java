package online.beautyskin.beauty.entity.respone;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import online.beautyskin.beauty.entity.PaymentMethod;
import online.beautyskin.beauty.entity.Promotion;
import online.beautyskin.beauty.enums.OrderStatusEnums;

public class OrderResponse {
    private long id;
    private LocalDateTime orderDate;
    private OrderStatusEnums orderStatus;
    private double totalPrice;
    private PaymentMethod paymentMethod;
    private List<OrderDetailResponse> orderDetails = new ArrayList<>();
    private OrderUserResponse userResponse;
    private Promotion promotion;


    public OrderResponse() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public OrderStatusEnums getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatusEnums orderStatus) {
        this.orderStatus = orderStatus;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public Promotion getPromotion() {
        return promotion;
    }
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    public OrderUserResponse getUserResponse() {
        return userResponse;
    }
    public void setUserResponse(OrderUserResponse userResponse) {
        this.userResponse = userResponse;
    }
    public List<OrderDetailResponse> getOrderDetails() {
        return orderDetails;
    }
    public void setOrderDetails(List<OrderDetailResponse> orderDetails) {
        this.orderDetails = orderDetails;
    }




}
