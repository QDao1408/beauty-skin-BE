package online.beautyskin.beauty.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.Transaction;
import online.beautyskin.beauty.enums.TransactionEnums;
import online.beautyskin.beauty.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void createTransactionForCreateOrder(Order order, TransactionEnums enums) {
        Transaction transaction = new Transaction();
        String des = "User " + order.getUser().getId()
                + " pay for order " + order.getId();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setEnums(enums);
        transaction.setOrders(order);
        transaction.setAmount(order.getTotalPrice());
        transaction.setDescription(des);
        transaction.setIncome(true);
        transactionRepository.save(transaction);
    }

    public void createRefundTransaction(Order order) {
        Transaction transaction = new Transaction();
        String des = "Refund " + order.getTotalPrice()
                + " for user: " + order.getUser().getId();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setEnums(TransactionEnums.REFUND);
        transaction.setOrders(order);
        transaction.setAmount(order.getTotalPrice());
        transaction.setDescription(des);
        transaction.setIncome(false);
        transactionRepository.save(transaction);
    }

}
