package com.hpe.bboss.core.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * LoadBalancerClient就是有Netflix Ribbon提供的Client,他会根据ServiceId（配置文件中的Service
 * Name）向Eureka（注册服务器）获取服务地址。在这里也可以提供一个容错机制，极限情况下，全宕机时使用fallbackUri。
 */
@Component
public class AppUtil {
	
	/*
	 * LoadBalancerClient 接口方法说明：
	 * 		ServiceInstance choose(String serviceId)：根据传入的服务名serviceId，从负载均衡器中挑选一个对应服务的实例。
	 * 		T execute(String serviceId, LoadBalancerRequest request) throws IOException：使用从负载均衡器中挑选出的服务实例来执行请求内容。
	 * 		URI reconstructURI(ServiceInstance instance, URI original)：为系统构建一个合适的“host:port”形式的URI。
	 * 			在分布式系统中，我们使用逻辑上的服务名称作为host来构建URI（替代服务实例的“host:port”形式）进行请求，比如：http://myservice/path/to/service。
	 * 			在该操作的定义中，前者ServiceInstance对象是带有host和port的具体服务实例，而后者URI对象则是使用逻辑服务名定义为host的URI，
	 * 			而返回的URI内容则是通过ServiceInstance的服务实例详情拼接出的具体“host:post”形式的请求地址。
	 */
	@Autowired
	private LoadBalancerClient loadBalancer; // 这个就是有Netflix Ribbon提供的Client

	public URI getRestUrl(String serviceId, String fallbackUri) {
		URI uri = null;
		try {
			ServiceInstance instance = loadBalancer.choose(serviceId);
			uri = instance.getUri();

		} catch (RuntimeException e) {
			uri = URI.create(fallbackUri);
		}

		return uri;
	}

	public <T> ResponseEntity<T> createOkResponse(T body) {
		return createResponse(body, HttpStatus.OK);
	}

	public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
		return new ResponseEntity<>(body, httpStatus);
	}

	public <T> T json2Object(ResponseEntity<String> response, Class<T> clazz) {
		try {

			return (T) JSON.parseObject(response.getBody(), clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public <T> List<T> json2Objects(ResponseEntity<String> response, Class<T> clazz) {
		try {

			return JSON.parseArray(response.getBody(), clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
