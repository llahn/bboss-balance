package com.hpe.bboss.balance.util;

public class TimeUtil {
	/*
	 * 将长整型数字转换为之间格式输出
	 */
	public static String format(long time) {
		long temp = time;
		long ms = temp % 1000;
		temp = temp / 1000;

		long ss = temp % 60;
		temp = temp / 60;

		long mi = temp % 60;
		temp = temp / 60;

		long hh = temp;

		return hh + ":" + mi + ":" + ss + "." + ms;
	}
}
