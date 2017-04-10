package com.hpe.bboss.balance.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.bboss.balance.demo.dao.UserMapper;
import com.hpe.bboss.balance.demo.service.UserService;
import com.hpe.bboss.balance.demo.vo.User;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;
	
	@Override
	public User selectByPrimaryKey(Integer uId) {
		return userDao.selectByPrimaryKey(uId);
	}

}
