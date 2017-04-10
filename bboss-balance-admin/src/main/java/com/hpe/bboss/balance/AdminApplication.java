package com.hpe.bboss.balance;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient //激活eureka中的DiscoveryClient实现
@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AdminApplication.class).web(true).run(args);
	}
}
