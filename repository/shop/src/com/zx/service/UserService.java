package com.zx.service;

import javax.servlet.http.HttpServletRequest;

import com.zx.vo.User;

public interface UserService {
	// 查询用户账号是否存在
	boolean findUserByLoginName(String loginName);
	boolean findUserByEmail(String email);
	
	//插入用户注册信息
	void save(User user,HttpServletRequest request);
	
	//根据用户激活码修改disabled，1：可以登录    0：不允许登录
	void active(String activeCode);
	
	//查询数据库是否有选定的账户名和密码
	boolean findUser(String text,String password);
	
	//查询数据库选定的账户名和密码是否激活
	User findUserActive(String loginName,String password);
}
