package com.org.customerrewards.customerrewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = { "com.org.customerrewards.customerrewards.entity" })
@EnableTransactionManagement
@EntityScan(basePackages = "com.org.customerrewards.customerrewards.entity")
@EnableJpaRepositories(basePackages = "com.org.customerrewards.customerrewards.repository")
public class CustomerRewardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRewardsApplication.class, args);
	}

}
