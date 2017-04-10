package com.hpe.bboss.balance.demo.service;

import com.hpe.bboss.balance.demo.vo.User;

public interface UserService {
	public User selectByPrimaryKey(Integer uId);
}
