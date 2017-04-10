package com.hpe.bboss.core.rest;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.hpe.bboss.core.entity.CommonResult;
import com.hpe.bboss.core.entity.resultenum.ResultStatus;

@Component
public class RemoteServiceClient {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AppUtil appUtil;

	/**
	 * post请求
	 * @param serviceId Eureka（注册服务器）中对应的服务地址
	 * @param request 请求参数对象
	 * @param resultBodyClass 返回对象
	 * @return
	 */
	public <Q, P> CommonResult<P> post(String serviceId, Q request, Class<P> resultBodyClass) {
		return post(serviceId, "", request, resultBodyClass);

	}
	
	/**
	 * @param serviceId  Eureka（注册服务器）中对应的服务地址
	 * @param fallbackUri  极限容错情况下，会使用的服务地址
	 * @param request  请求参数对象
	 * @param resultBodyClass  返回对象
	 * @return
	 */
	public <Q, P> CommonResult<P> post(String serviceId, String fallbackUri, Q request, Class<P> resultBodyClass) {
		try {
			String reqJson = appUtil.object2Json(request);
			URI uri = appUtil.getRestUrl(serviceId, fallbackUri);
			String respJson = postCore(uri.toString(), reqJson);
			
			return (CommonResult<P>)appUtil.json2Object(respJson, CommonResult.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new CommonResult<P>(ResultStatus.RESPONSE_FAILUE, e.getMessage());
		}

	}

	private String postCore(String fullUrl, String json) throws RestClientException {
		long startTime = System.currentTimeMillis();
		String respJson = "";
		try {
			// 复杂构造函数的使用
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setConnectTimeout(100000);// 设置超时
			requestFactory.setReadTimeout(100000);

			// 利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient
			RestTemplate restTemplate = new RestTemplate(requestFactory);

			// 设置HTTP请求头信息，实现编码等
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Accept-Charset", "utf-8");
			requestHeaders.set("Content-type", "text/xml; charset=utf-8");// 设置编码

			// 利用容器实现数据封装，发送
			HttpEntity<String> entity = new HttpEntity<>(json, requestHeaders);
			respJson = restTemplate.postForObject(fullUrl, entity, String.class);
			return respJson;
		} finally {
			logger.warn("cast time :" + TimeUtil.format(System.currentTimeMillis() - startTime));
			logger.warn("restful response[ url:" + fullUrl + ", json:" + respJson + "]");
		}

	}
	
	public String getCore(String url, Object req) {
        long startTime = System.currentTimeMillis();
        String respJson = "", paraUrl = "";
        try {
            logger.warn("restful request[ url:" + url + ", json:" + appUtil.object2Json(req) + "]");
            // 复杂构造函数的使用
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(100000);// 设置超时
            requestFactory.setReadTimeout(100000);

            // 利用复杂构造器可以实现超时设置，内部实际实现为 HttpClient
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            Map<String, Object> map = new LinkedHashMap<>();
          //  EbtceBeanUtils.copyBean2Map(map, req);//对象转Map
            paraUrl = createUrl(url, map);

            respJson = restTemplate.getForObject(paraUrl, String.class);
            return respJson;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return appUtil.object2Json(new CommonResult<Object>(ResultStatus.RESPONSE_FAILUE, e.getMessage()));
        } finally {
            logger.warn("cast time :" + TimeUtil.format(System.currentTimeMillis() - startTime));
            logger.warn("restful response[ url:" + paraUrl + ", json:" + respJson + "]");
        }
    }
	
	public String createUrl(String url, Map<String, Object> map) {
        StringBuilder para = new StringBuilder();
        for (Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey().equals("class"))
                continue;
            if (StringUtils.isNotBlank(para))
                para.append("&");
            para.append(entry.getKey()).append("=");
            if (entry.getValue() != null)
                para.append(entry.getValue());
            para.append("");
        }
        return url + "?" + para.toString();
    }
}
