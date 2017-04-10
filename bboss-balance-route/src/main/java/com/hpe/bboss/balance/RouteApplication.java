package com.hpe.bboss.balance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.hpe.bboss.balance.filter.SimpleZuulFilter;
import com.netflix.zuul.context.ContextLifecycleFilter;
import com.netflix.zuul.http.ZuulServlet;

@EnableZuulProxy
@EnableEurekaServer //启动一个服务注册中心提供给其他应用进行对话
@SpringCloudApplication
public class RouteApplication {
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Bean
	public SimpleZuulFilter simpleZuulFilter() {
		logger.debug("实例化SimpleZuulFilter过滤器");
		return new SimpleZuulFilter();
	}
	
	@Bean
    public ServletRegistrationBean zuulServlet() {
		logger.debug("通过ServletRegistrationBean构造ZuulServlet，该Servlet用以进行filter执行调度以及监控等等操作,访问 http://localhost:8080/test 进入该servlet");
        ServletRegistrationBean servlet = new ServletRegistrationBean(new ZuulServlet());
        servlet.addUrlMappings("/test");
        return servlet;
    }

    @Bean
    public FilterRegistrationBean contextLifecycleFilter() {
    	logger.debug("通过FilterRegistrationBean进行filter注册，ContextLifecycleFilter的核心功能是为了清除RequestContext； 请求上下文通过ThreadLocal存储，因此需要在请求完成后删除该对象。");
        FilterRegistrationBean filter = new FilterRegistrationBean(new ContextLifecycleFilter());
        filter.addUrlPatterns("/*");
        return filter;
    }

	public static void main(String[] args) {
		new SpringApplicationBuilder(RouteApplication.class).web(true).run(args);
	}
}
