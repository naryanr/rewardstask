package com.org.customerrewards.customerrewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = { "CustomerRewardsController.class" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableAsync
// @EnableJpaRepositories(basePackages = {
// "com.org.customerrewards.customerrewards.entity" })
public class CustomerRewardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRewardsApplication.class, args);
	}

}
