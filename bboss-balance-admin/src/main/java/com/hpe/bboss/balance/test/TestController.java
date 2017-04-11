package com.hpe.bboss.balance.test;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpe.bboss.core.entity.CommonResult;
import com.hpe.bboss.core.rest.RemoteServiceClient;

@Controller
public class TestController {

	@Autowired
	private DiscoveryClient client;
	
	@Resource
	private RemoteServiceClient remote;

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
		ServiceInstance instance = client.getLocalServiceInstance();
		Integer r = a + b;
		System.out.println(
				"/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
		return r;
	}
	
	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(){
		CommonResult<String> s = remote.post("user-service", "/user", "", String.class);
		
		return s.getBody();
	}
}
