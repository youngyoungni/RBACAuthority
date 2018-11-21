package com.young.atcrowdfunding.service;

import java.util.List;

import com.young.atcrowdfunding.bean.Permission;
import com.young.atcrowdfunding.bean.User;

public interface PermissionService {

	Permission queryRootPermission();

	List<Permission> queryChildPermissions(Integer id);

	List<Permission> queryAll();

	void insert(Permission permission);

	Permission queryById(Integer id);

	void update(Permission permission);

	void delete(Integer id);

	List<Integer> queryPermissionidByRoleid(Integer roleid);

	List<Permission> queryPermissionidByUser(User dbUser);

}
