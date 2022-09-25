package com.org.customerrewards.customerrewards.service;

import com.org.customerrewards.customerrewards.entity.Customer;
import com.org.customerrewards.customerrewards.model.CustomerRewards;

public interface CustomerRewardsService {

    public Customer findByCustomerId(Long customerId);

    public CustomerRewards getCustomerRewards(Long customerId);

}
