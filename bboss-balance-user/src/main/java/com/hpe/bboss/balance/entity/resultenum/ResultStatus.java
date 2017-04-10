package com.hpe.bboss.balance.entity.resultenum;

public enum ResultStatus {
	RESPONSE_FAILUE(0, "失败"), 
	RESPONSE_SUCESS(1, "成功"), 
	SKIP_TO_DEDUCT(10, "跳到扣减"), 
	NO_FLIGHTS(99, "没有查询到符合条件的航班数据"), 
	MEMBER_ARE_NOT_ELIGIBLE_FOR_REDEEM(1004, "会员没有兑换资格"), 
	INSUFFICIENT_MEMBER_POINTS(1009, "会员里程不足"), 
	INCORRECT_PASSWORD(1015, "密码不正确"), 
	PAY_TIMEOUT(993, "支付超时"), 
	INVENTORY_SHORTAGE(994, "库存不足"), 
	FAILED_TO_CREATE_PNR(995, "预占座位失败"), 
	ACCOUNT_IS_LOCKED(996, "账号被锁定"), 
	REPEATED_SUBMIT(997, "重复提交"), 
	NOT_LOGGED_IN(998, "用户未登录"), 
	REQUEST_FAILUE(999, "请求失败"), 
	CRM_TIMEOUT(-1005, "CRM连接超时");

	public final int status;
	public final String message;

	private ResultStatus(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public static ResultStatus getEnum(int status) {
		for (ResultStatus c : ResultStatus.values()) {
			if (c.status == status) {
				return c;
			}
		}
		return null;
	}

	public static String getDesc(int status) {
		if (getEnum(status) != null)
			return getEnum(status).message;
		else
			return null;
	}

}