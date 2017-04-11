package com.hpe.bboss.balance;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient //激活eureka中的DiscoveryClient实现
@SpringBootApplication
@ComponentScan(value="com.hpe.bboss") //默认扫描的是当前文件所在包层级
public class AdminApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AdminApplication.class).web(true).run(args);
	}
}
