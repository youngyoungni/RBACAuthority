package com.young.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;

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


	@Override
	public User queryLogin(User user) {
		return userDao.queryLogin(user);
	}


	@Override
	public List<User> pageQueryData(Map<String, Object> map) {
		return userDao.pageQueryData(map);
	}


	@Override
	public int pageQueryCount(Map<String, Object> map) {
		return userDao.pageQueryCount(map);
	}
}
