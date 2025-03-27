package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import online.beautyskin.beauty.enums.TransactionEnums;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(0)
    private double amount;
    @NotNull
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private TransactionEnums enums;

    private String description;

    private boolean isIncome;

    

    public Transaction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionEnums getEnums() {
        return enums;
    }

    public void setEnums(TransactionEnums enums) {
        this.enums = enums;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean isIncome) {
        this.isIncome = isIncome;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrders(Order order) {
        this.order = order;
    }

    
}
