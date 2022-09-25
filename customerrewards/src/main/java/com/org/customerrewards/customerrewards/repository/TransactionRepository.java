package com.org.customerrewards.customerrewards.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.customerrewards.customerrewards.entity.Transaction;

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    public List<Transaction> findAllTransactionsByCustomerId(Long customerId);

    public List<Transaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from,
            Timestamp to);
}
