package com.weiwu.dao;

import org.apache.ibatis.annotations.Param;

import com.weiwu.entry.User;

public interface  UserDao {

	public User selectUserById(@Param("userId")String id);
	
	public User getUserByWeiXin(@Param("openId")String openId);
}
