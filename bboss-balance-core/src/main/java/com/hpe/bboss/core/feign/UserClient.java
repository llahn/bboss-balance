package com.hpe.bboss.core.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("bboss-balance-user")
public interface UserClient {
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String getUser();
}
