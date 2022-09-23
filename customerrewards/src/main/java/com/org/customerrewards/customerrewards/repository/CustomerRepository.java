package com.org.customerrewards.customerrewards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.customerrewards.customerrewards.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByCustomerId(Long customerId);
}
