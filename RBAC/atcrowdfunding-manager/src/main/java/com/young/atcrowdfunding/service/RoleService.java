package com.young.atcrowdfunding.service;


import java.util.List;
import java.util.Map;

import com.young.atcrowdfunding.bean.AJAXResult;
import com.young.atcrowdfunding.bean.Role;

public interface RoleService {

	AJAXResult pageQuery(Integer pageNo, Integer pageSize, String isDelete, String queryText);

	void insert(Role role);

	Role queryById(Integer id);


	void updateRole(Role role);

	void updateRoleList(Integer[] roleId);

	List<Role> queryAll();

	void insertRolePermission(Map<String, Object> map);



}
