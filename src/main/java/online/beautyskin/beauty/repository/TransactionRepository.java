package online.beautyskin.beauty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import online.beautyskin.beauty.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
