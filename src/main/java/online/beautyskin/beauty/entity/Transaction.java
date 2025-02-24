package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "Amount")
    @Min(0)
    private double amount;
    @NotNull
    private LocalDateTime transactionDate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Transaction() {}
    public Transaction(double amount, LocalDateTime transactionDate) {
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
    public long getId() { return id; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
}
