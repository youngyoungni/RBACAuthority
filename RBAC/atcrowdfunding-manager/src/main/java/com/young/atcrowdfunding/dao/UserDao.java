package com.young.atcrowdfunding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.young.atcrowdfunding.bean.User;

public interface UserDao {
	
	@Select("select * from t_user")
	List<User> queryAll();

}
