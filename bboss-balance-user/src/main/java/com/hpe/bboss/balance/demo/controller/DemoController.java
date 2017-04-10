package com.hpe.bboss.balance.demo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.bboss.balance.demo.service.UserService;
import com.hpe.bboss.balance.demo.vo.User;


@RestController
public class DemoController {
	
	@Resource(name="userServiceImpl")
	private UserService user;

	//@RequestMapping(value = "/user", method = RequestMethod.POST)
	@RequestMapping(value = "/user")
	public User index() {
		User res=user.selectByPrimaryKey(1);
		return res;
	}
}
