package com.hpe.bboss.balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.hpe.bboss.balance.config.DemoDataSource;

@SpringBootApplication
@EnableDiscoveryClient // 激活eureka中的DiscoveryClient实现
@EnableConfigurationProperties(DemoDataSource.class)
public class UserApplication {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}
