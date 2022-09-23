package com.org.customerrewards.customerrewards.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CustomerRewards {

    private long customerId;
    private long lastMonthRewards;
    private long lastSecondMonthRewards;
    private long lastThirdMonthRewards;
    private long totalRewards;

}
