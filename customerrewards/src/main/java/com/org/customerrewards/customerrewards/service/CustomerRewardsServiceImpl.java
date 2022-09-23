package com.org.customerrewards.customerrewards.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.customerrewards.customerrewards.entity.Customer;
import com.org.customerrewards.customerrewards.entity.Transaction;
import com.org.customerrewards.customerrewards.model.CustomerRewards;
import com.org.customerrewards.customerrewards.repository.CustomerRepository;
import com.org.customerrewards.customerrewards.repository.TransactionRepository;
import com.org.customerrewards.customerrewards.Constants.CustomerRewardsConstants;

@Service
public class CustomerRewardsServiceImpl implements CustomerRewardsService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer findByCustomerId(Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    @Override
    public CustomerRewards getCustomerRewards(long customerId) {
        Timestamp lastMonthTimestamp = getMonthTimeStamp(CustomerRewardsConstants.daysInMonth);
        Timestamp lastSecondMonthTimestamp = getMonthTimeStamp(CustomerRewardsConstants.daysInMonth * 2);
        Timestamp lastThirdMonthTimestamp = getMonthTimeStamp(CustomerRewardsConstants.daysInMonth * 3);

        List<Transaction> lastMonthTransactionList = transactionRepository
                .findAllTransactionsByCustomerIdTimeStamp(customerId, lastMonthTimestamp,
                        Timestamp.from(Instant.now()));

        List<Transaction> lastSecondMonthTransactionList = transactionRepository
                .findAllTransactionsByCustomerIdTimeStamp(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);

        List<Transaction> lastThirdMonthTransactionList = transactionRepository
                .findAllTransactionsByCustomerIdTimeStamp(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewards = getRewardsPerMonth(lastMonthTransactionList);
        Long lastSecondMonthRewards = getRewardsPerMonth(lastSecondMonthTransactionList);
        Long lastThirdMonthRewards = getRewardsPerMonth(lastThirdMonthTransactionList);

        CustomerRewards customerRewards = new CustomerRewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setLastMonthRewards(lastMonthRewards);
        customerRewards.setLastSecondMonthRewards(lastSecondMonthRewards);
        customerRewards.setLastThirdMonthRewards(lastThirdMonthRewards);
        customerRewards.setTotalRewards(lastMonthRewards + lastSecondMonthRewards + lastThirdMonthRewards);

        return customerRewards;
    }

    private Timestamp getMonthTimeStamp(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

    private Long getRewardsPerMonth(List<Transaction> transactionslList) {
        return transactionslList.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(rewards -> rewards.longValue()));
    }

    private Long calculateRewards(Transaction transaction) {
        if (transaction.getAmount() > CustomerRewardsConstants.fiftyRewardsLimit
                && transaction.getAmount() <= CustomerRewardsConstants.hundreddRewardsLimit) {
            return (long) (transaction.getAmount() - CustomerRewardsConstants.fiftyRewardsLimit);
        } else if (transaction.getAmount() > CustomerRewardsConstants.hundreddRewardsLimit) {
            return (long) (((transaction.getAmount() - CustomerRewardsConstants.hundreddRewardsLimit) * 2) +
                    (transaction.getAmount() - CustomerRewardsConstants.fiftyRewardsLimit));
        } else {
            return 0l;
        }

    }

}
