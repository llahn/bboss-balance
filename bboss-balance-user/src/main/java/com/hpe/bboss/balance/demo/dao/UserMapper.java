package com.hpe.bboss.balance.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.hpe.bboss.balance.demo.vo.User;

public interface UserMapper {
	
    User selectByPrimaryKey(@Param("uId")Integer uId);
}