package online.beautyskin.beauty.entity.respone;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private long id;
    private LocalDateTime orderDate;
    private String orderStatus;
    private double totalPrice;
    private String paymentMethod;
    private String paymentStatus;
    private List<OrderDetailResponse> orderDetails = new ArrayList<>();
    private OrderUserResponse userResponse;
    private String promotion;
    private List<OrderTransactionResponse> orderTransactions = new ArrayList<>();

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

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<OrderTransactionResponse> getOrderTransactions() {
        return orderTransactions;
    }

    public void setOrderTransactions(List<OrderTransactionResponse> orderTransactions) {
        this.orderTransactions = orderTransactions;
    }
}
