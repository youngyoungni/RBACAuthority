package com.young.atcrowdfunding.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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


	@Override
	public void insert(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setCreatetime(sdf.format(new Date()));
		user.setUserpswd("123456");
		user.setIsdelete("n");
		userDao.insertUser(user);
	}


	@Override
	public User queryById(String id) {
		// TODO Auto-generated method stub
		return userDao.queryById(id);
	}


	@Override
	public void update(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setUpdatetime(sdf.format(new Date()));
		userDao.updateUser(user);
	}


	@Override
	public void updates(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.updateUsers(map);
	}


	@Override
	public List<Integer> queryRoleidsByUserid(String id) {
		return userDao.queryRoleidsByUserid(id);
	}


	@Override
	public void insertUserRoles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.insertUserRoles(map);
	}


	@Override
	public void deleteUserRoles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.deleteUserRoles(map);
		
	}

}
