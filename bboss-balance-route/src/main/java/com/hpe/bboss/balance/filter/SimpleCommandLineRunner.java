package com.hpe.bboss.balance.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.filters.FilterRegistry;
import com.netflix.zuul.monitoring.MonitoringHelper;

@Order(value=2)
@Component
public class SimpleCommandLineRunner implements CommandLineRunner{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run(String... args) throws Exception {
		logger.info(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
		MonitoringHelper.initMocks(); //启动监控
        initJavaFilters();
	}

	/*
	 * 注册三种类型filter
	 */
	private void initJavaFilters(){
		final FilterRegistry freg = FilterRegistry.instance();
		freg.put("javaPreFilter", new ZuulFilter() {
			@Override
			public boolean shouldFilter() {
				return true;
			}
			@Override
			public Object run() {
				logger.info("running javaPreFilter");
                RequestContext.getCurrentContext().set("name", "liaokailin");
                return null;
			}
			@Override
			public String filterType() {
				return "pre";
			}
			@Override
			public int filterOrder() {
				return 50000;
			}
		});
		
		freg.put("javaRoutingFilter", new ZuulFilter() {
			@Override
			public boolean shouldFilter() {
				return true;
			}
			@Override
			public Object run() {
				logger.info("running javaRoutingFilter");
                try {
                    RequestContext.getCurrentContext().getResponse().sendRedirect("http://blog.csdn.net/liaokailin/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
			}
			@Override
			public String filterType() {
				return "route";
			}
			@Override
			public int filterOrder() {
				return 50000;
			}
		});
		
		freg.put("javaPostFilter", new ZuulFilter() {
            @Override
            public int filterOrder() {
                return 50000;
            }
            @Override
            public String filterType() {
                return "post";
            }
            @Override
            public boolean shouldFilter() {
                return true;
            }
            @Override
            public Object run() {
            	logger.info("running javaPostFilter");
            	logger.info(RequestContext.getCurrentContext().get("name").toString());
                return null;
            }
        });

	}
}
