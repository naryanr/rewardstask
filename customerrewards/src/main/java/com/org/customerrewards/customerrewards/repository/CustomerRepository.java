package com.org.customerrewards.customerrewards.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.customerrewards.customerrewards.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query(value = "select customer_id,customer_name from rewards.customer where customer_id=:customerId", nativeQuery = true)
    public Customer findByCustomerId(Long customerId);
}
