package com.hpe.bboss.balance.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value=3)
@Component
public class SimpleApplicationRunner implements ApplicationRunner{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info(">>>>>>>>>>>>>>>服务启动执行ApplicationRunner，执行加载数据等操作<<<<<<<<<<<<<");
	}

}
