package com.young.atcrowdfunding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.young.atcrowdfunding.bean.Permission;
import com.young.atcrowdfunding.bean.User;

public interface PermissionDao {

	@Select("select * from t_permission where pid is null")
	Permission queryRootPermission();

	@Select("select * from t_permission where pid = #{id}")
	List<Permission> queryChildPermissions(Integer id);

	@Select("select * from t_permission")
	List<Permission> queryAll();

	void insert(Permission permission);

	@Select("select * from t_permission where id = #{id}")
	Permission queryById(Integer id);

	void update(Permission permission);

	@Delete("delete from t_permission where id = #{id}")
	void delete(Integer id);

	@Select("select permissionid from t_role_permission where roleid = #{roleid}")
	List<Integer> queryPermissionidByRoleid(Integer roleid);

	
	List<Permission> queryPermissionidByUser(User dbUser);

}
