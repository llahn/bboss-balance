package com.hpe.bboss.balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.hpe.bboss.core.config.DemoDataSource;

@SpringBootApplication
@EnableDiscoveryClient // 激活eureka中的DiscoveryClient实现
@EnableConfigurationProperties(DemoDataSource.class)
@ComponentScan(value="com.hpe.bboss") //默认扫描的是当前文件所在包层级
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
