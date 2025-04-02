package online.beautyskin.beauty.entity.respone;

public class OrderDetailResponse {
    private long id;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private OrderProductResponse orderProductResponse;

    public OrderDetailResponse() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public OrderProductResponse getOrderProductResponse() {
        return orderProductResponse;
    }
    public void setOrderProductResponse(OrderProductResponse orderProductResponse) {
        this.orderProductResponse = orderProductResponse;
    }


}
