package com.hpe.bboss.core.json;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	/*
	 * 对象转JSON字符串
	 */
	public static String object2Json(Object obj) {
		return JSON.toJSONString(obj);
	}
	
	/*
	 * json字符串转对象
	 */
	public static <T> T json2Object(String resJson, Class<T> clazz){
		return JSON.parseObject(resJson, clazz);
	}
}
