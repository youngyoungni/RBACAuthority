package com.young.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.atcrowdfunding.bean.AJAXResult;
import com.young.atcrowdfunding.bean.Permission;
import com.young.atcrowdfunding.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id) {
		AJAXResult result = new AJAXResult();
		try {
			permissionService.delete(id);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Permission permission) {
		AJAXResult result = new AJAXResult();
		try {
			permissionService.update(permission);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Permission permission) {
		AJAXResult result = new AJAXResult();
		try {
			permissionService.insert(permission);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id , Model model) {
		Permission permission = permissionService.queryById(id);
		model.addAttribute("permission", permission);
		return "permission/edit";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "permission/add";
	}
	
	
	@RequestMapping("/index")
	public String index() {
		return "permission/permission_index";
	}
	
	
	@ResponseBody
	@RequestMapping("/loadAssignData")
	public Object loadAssignData( Integer roleid) {
		 List<Permission> permissions = new ArrayList<Permission>();
		 List<Permission> ps = permissionService.queryAll();
		 
		 //��ȡ��ǰ��ɫ�Ѿ�����������Ϣ��t_role_permission permissionid��
		 List<Integer> permissionids = permissionService.queryPermissionidByRoleid(roleid);
		 
		 Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			//��װ���ݵ� Map
			for (Permission p : ps) {
				//�ж� roleid ��ɫ�� �Ƿ�����������Ϣ
				if( permissionids.contains(p.getId())) {
					p.setChecked(true);
				}else {
					p.setChecked(false);
				}
				permissionMap.put(p.getId(), p);
			}
			//վ���ӽڵ㿼�ǣ�ͨ���ӽڵ�ȥƥ�丸�ڵ�
			for ( Permission p : ps ) {
				Permission child = p;	//��ÿ���ڵ㶼�������ӽڵ�
				if ( child.getPid() == 0 ) {
					//�����ڵ�
					permissions.add(p);
				} else {
					//���ڵ� = permissionMap.get(child.getPid())ͨ���ӽڵ�� Pid ��ƥ�丸�ڵ�� Id
					Permission parent = permissionMap.get(child.getPid());
					//��ϸ��ӽڵ�Ĺ�ϵ
					parent.getChildren().add(child);
				}
			}
			return permissions;
		}
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		 List<Permission> permissions = new ArrayList<Permission>();
		 /* 1��ģ������	
		  * 	 Permission root = new Permission();
		 		 root.setName("�����ڵ�");
		 		 Permission child = new Permission();
		 		 child.setName("�ӽڵ�");
		 		 root.getChildren().add(child);
		 		 permissions.add(root);	
		 */		 
		 		 // ��ȡ���νṹ������
		 /* 2������������ݿ�����
		  * 		 Permission root = permissionService.queryRootPermission();
		 		 
		 		 List<Permission> childPermissions = permissionService.queryChildPermissions(root.getId());
		 		 
		 		 for (Permission childPermission : childPermissions) {
		 			 List<Permission> hildchildPermissions = permissionService.queryChildPermissions(childPermission.getId());
		 			 childPermission.setChildren(hildchildPermissions);
		 		 }
		 		 
		 		 root.setChildren(childPermissions);
		 		 permissions.add(root);
		 */		
		 /* 3���ݹ��ѯ���ݣ�Ч�ʵ���
		 		 Permission parent = new Permission();
		 		 parent.setId(0);
		 		 queryChildPermissions(parent);
		 		 return parent.getChildren();
		 */
		 /* 4��Array��ѯ��û���õ�����������Ҫ����ƥ��
		  * 		��Ҫÿ�����ݸ��������е�����ƥ��
		  * 		����10 �����10*10����
		 		List<Permission> ps = permissionService.queryAll();
		 		
		 		//վ���ӽڵ㿼�ǣ�ͨ���ӽڵ�ȥƥ�丸�ڵ�
		 		for (Permission p : ps) {
		 			Permission child = p; //��ÿ���ڵ㶼�������ӽڵ�
		 			if( p.getPid() == 0) {
		 				//�����ڵ�
		 				permissions.add(p);
		 			}else {
		 				for (Permission innerPermission : ps) {
		 					if( child.getPid().equals(innerPermission.getId())) {
		 						//���ڵ�
		 						Permission parent = innerPermission;
		 						//��ϸ��ӽڵ�Ĺ�ϵ
		 						parent.getChildren().add(child);
		 						break;
		 					}
		 				}
		 			}
		 		}
		 */		
		//Map
		List<Permission> ps = permissionService.queryAll();
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		//��װ���ݵ� Map
		for (Permission p : ps) {
			permissionMap.put(p.getId(), p);
		}
		//վ���ӽڵ㿼�ǣ�ͨ���ӽڵ�ȥƥ�丸�ڵ�
		for ( Permission p : ps ) {
			Permission child = p;	//��ÿ���ڵ㶼�������ӽڵ�
			if ( child.getPid() == 0 ) {
				//�����ڵ�
				permissions.add(p);
			} else {
				//���ڵ� = permissionMap.get(child.getPid())ͨ���ӽڵ�� Pid ��ƥ�丸�ڵ�� Id
				Permission parent = permissionMap.get(child.getPid());
				//��ϸ��ӽڵ�Ĺ�ϵ
				parent.getChildren().add(child);
			}
		}
		return permissions;
	}
	
	
	/**
	 * 3�ݹ��ѯ�����Ϣ:
	 * 	1�����Լ������Լ�
	 *  2 ���������������߼�
	 *  3�������ò����й���
	 *  4�ݹ��㷨��Ч�ʽϵ�
	 * @param parent
	 */
//	private void queryChildPermissions( Permission parent) {
//		List<Permission> childPermissions = permissionService.queryChildPermissions(parent.getId());
//		
//		//��� childPermissions Ϊ�վ�������
//		for (Permission permission : childPermissions) {
//			queryChildPermissions(permission);
//		}
//		parent.setChildren(childPermissions);
//	}
}
