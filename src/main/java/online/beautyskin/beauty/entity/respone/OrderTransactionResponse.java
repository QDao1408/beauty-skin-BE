package online.beautyskin.beauty.entity.respone;

import java.time.LocalDateTime;

public class OrderTransactionResponse {

    private double transactionAmount;
    private String transactionEnum;
    private LocalDateTime transactionDate;

    public OrderTransactionResponse() {
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionEnum() {
        return transactionEnum;
    }

    public void setTransactionEnum(String transactionEnum) {
        this.transactionEnum = transactionEnum;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
