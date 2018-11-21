package com.young.atcrowdfunding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.atcrowdfunding.bean.Permission;
import com.young.atcrowdfunding.bean.User;
import com.young.atcrowdfunding.dao.PermissionDao;
import com.young.atcrowdfunding.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public Permission queryRootPermission() {
		// TODO Auto-generated method stub
		return permissionDao.queryRootPermission();
	}

	@Override
	public List<Permission> queryChildPermissions(Integer id) {
		// TODO Auto-generated method stub
		return permissionDao.queryChildPermissions(id);
	}

	@Override
	public List<Permission> queryAll() {
		// TODO Auto-generated method stub
		return permissionDao.queryAll();
	}

	@Override
	public void insert(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.insert(permission);
	}

	@Override
	public Permission queryById(Integer id) {
		// TODO Auto-generated method stub
		return permissionDao.queryById(id);
	}

	@Override
	public void update(Permission permission) {
		// TODO Auto-generated method stub
		permissionDao.update(permission);
	}

	@Override
	public void delete(Integer id) {
		permissionDao.delete(id);
	}

	@Override
	public List<Integer> queryPermissionidByRoleid(Integer roleid) {
		// TODO Auto-generated method stub
		return permissionDao.queryPermissionidByRoleid(roleid);
	}

	@Override
	public List<Permission> queryPermissionidByUser(User dbUser) {
		// TODO Auto-generated method stub
		return permissionDao.queryPermissionidByUser(dbUser);
	}
	
	
}
