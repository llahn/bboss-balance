package com.hpe.bboss.balance.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SimpleZuulFilter extends ZuulFilter{
	
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	/*
	 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
	 * filterOrder：通过int值来定义过滤器的执行顺序
	 * shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效。
	 * run：过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
	 */
	
	@Override
	public boolean shouldFilter() {
//		logger.debug("ZuulFilter接口的shouldFilter():filter是否需要执行 true执行 false 不执行 ");
		return true;
	}

	@Override
	public Object run() {
//		logger.debug("ZuulFilter接口的run():过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		Object accessToken = request.getParameter("accessToken"); // 定义规则：访问url中必须带有accessToken参数
		if (accessToken == null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}
		return null;
	}

	/*
	 * pre：可以在请求被路由之前调用
	 * route：在路由请求时候被调用 
	 * post：在routing和error过滤器之后被调用
	 * error：处理请求时发生错误时被调用
	 */
	public String filterType() {
//		String str = "ZuulFilter接口的filterType()方法，返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型"+
//				System.getProperty("line.separator")+
//				"pre:请求执行之前filter "+
//				System.getProperty("line.separator")+
//				"route:处理请求，进行路由  "+
//				System.getProperty("line.separator")+
//				"post:请求处理完成后执行的filter "+
//				System.getProperty("line.separator")+
//				"error:出现错误时执行的filter ";
//		logger.debug(str);
		return "pre";
	}

	public int filterOrder() {
//		logger.debug("ZuulFilter接口的filterOrder():filter执行顺序，通过数字指定");
		return 0;
	}

}