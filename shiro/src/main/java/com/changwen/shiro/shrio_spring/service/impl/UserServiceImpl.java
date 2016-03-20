package com.changwen.shiro.shrio_spring.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import com.changwen.shiro.shrio_spring.dao.UserDao;
import com.changwen.shiro.shrio_spring.entity.User;
import com.changwen.shiro.shrio_spring.service.UserService;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	public User getByUserName(String userName) {
		return userDao.getByUserName(userName);
	}

	public Set<String> getRoles(String userName) {
		return userDao.getRoles(userName);
	}

	public Set<String> getPermissions(String userName) {
		return userDao.getPermissions(userName);
	}

}
