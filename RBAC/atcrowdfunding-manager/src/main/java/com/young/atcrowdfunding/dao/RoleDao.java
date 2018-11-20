package com.young.atcrowdfunding.dao;

import java.util.List;
import java.util.Map;

import com.young.atcrowdfunding.bean.Role;

public interface RoleDao {

	List<Role> pageQueryData(Map<String, Object> map);

	int pageQueryCount(Map<String, Object> map);

	void insert(Role role);

	Role queryById(Integer id);

	void updateRole(Role role);

	void updateRoleList(Map<String, Object> map);

	List<Role> queryAll();


}
