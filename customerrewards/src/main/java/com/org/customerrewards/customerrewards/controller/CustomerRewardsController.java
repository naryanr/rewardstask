package com.org.customerrewards.customerrewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.customerrewards.customerrewards.entity.Customer;
import com.org.customerrewards.customerrewards.model.CustomerRewards;
import com.org.customerrewards.customerrewards.service.CustomerRewardsService;

@RestController
public class CustomerRewardsController {

    @Autowired
    CustomerRewardsService customerRewardsService;

    @RequestMapping(value = "/calRewards", method = RequestMethod.GET)
    public String calRewards() {
        // return customerRewardsService.calRewards(customerrewards);
        return "Rewards updated controller";
    }

    @RequestMapping(value = "/rewards", method = RequestMethod.GET)
    public ResponseEntity<CustomerRewards> getCustomerRewards(@RequestParam long customerId) {
        Customer customer = customerRewardsService.findByCustomerId(customerId);
        if (customer == null) {
            return new ResponseEntity<CustomerRewards>(new CustomerRewards(), HttpStatus.BAD_REQUEST);
        }
        CustomerRewards customerRewards = customerRewardsService.getCustomerRewards(customerId);

        return new ResponseEntity<CustomerRewards>(customerRewards, HttpStatus.OK);
    }

    @RequestMapping("/error")
    @ResponseBody
    public String getErrorPath() {
        return "<center><h1>Something went wrong</h1></center>";
    }
}
