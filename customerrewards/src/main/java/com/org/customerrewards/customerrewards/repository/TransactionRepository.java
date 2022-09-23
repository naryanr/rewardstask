package com.org.customerrewards.customerrewards.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.customerrewards.customerrewards.entity.Transaction;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findAllTransactionsByCustomerId(Long customerId);

    public List<Transaction> findAllTransactionsByCustomerIdTimeStamp(Long customerId, Timestamp from, Timestamp to);
}
