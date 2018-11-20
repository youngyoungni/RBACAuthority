package com.young.atcrowdfunding.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.atcrowdfunding.bean.AJAXResult;
import com.young.atcrowdfunding.bean.Page;
import com.young.atcrowdfunding.bean.Role;
import com.young.atcrowdfunding.dao.RoleDao;
import com.young.atcrowdfunding.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public AJAXResult pageQuery(Integer pageNo, Integer pageSize, String isDelete, String queryText) {
		AJAXResult result = new AJAXResult();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("start", ( pageNo - 1 ) * pageSize);
		map.put("size", pageSize);
		map.put("isDelete", isDelete);
		map.put("queryText", queryText);
		
		List<Role> roleList = roleDao.pageQueryData(map);
		
		int totalSize = roleDao.pageQueryCount(map);
		int totalNo = 0 ;
		if( totalSize % pageSize == 0) {
			totalNo = totalSize / pageSize;
		}else {
			totalNo = totalSize / pageSize + 1;
		}
		
		Page<Role> rolePage = new Page<Role>(roleList,pageNo,totalNo,totalSize);
		result.setData(rolePage);
		System.out.println(rolePage.toString());
		return result;
	}

	@Override
	public void insert(Role role) {
		role.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		roleDao.insert(role);
	}

	@Override
	public Role queryById(Integer id) {
		return roleDao.queryById(id);
	}
	@Override
	public void updateRole(Role role) {
		role.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		roleDao.updateRole(role);
	}

	@Override
	public void updateRoleList(Integer[] roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isDelete", "y");
		map.put("roleIdList", roleId);
		map.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		roleDao.updateRoleList(map);
	}

	@Override
	public List<Role> queryAll() {
		return roleDao.queryAll();
	}



}
