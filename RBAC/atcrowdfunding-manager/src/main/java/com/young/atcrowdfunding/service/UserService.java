package com.young.atcrowdfunding.service;

import java.util.List;
import java.util.Map;

import com.young.atcrowdfunding.bean.User;

public interface UserService {

	List<User> queryAll();

	User queryLogin(User user);

	List<User> pageQueryData(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insert(User user);

	User queryById(String id);

	void update(User user);

	void updates(Map<String, Object> map);

	List<Integer> queryRoleidsByUserid(String id);

	void insertUserRoles(Map<String, Object> map);

	void deleteUserRoles(Map<String, Object> map);


}
