package com.young.atcrowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.atcrowdfunding.bean.User;
import com.young.atcrowdfunding.dao.UserDao;
import com.young.atcrowdfunding.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	
	@Override
	public List<User> queryAll() {
		return userDao.queryAll();
	}
}
