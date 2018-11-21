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
		 
		 //获取当前角色已经分配的许可信息（t_role_permission permissionid）
		 List<Integer> permissionids = permissionService.queryPermissionidByRoleid(roleid);
		 
		 Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			//组装数据到 Map
			for (Permission p : ps) {
				//判断 roleid 角色中 是否包含此许可信息
				if( permissionids.contains(p.getId())) {
					p.setChecked(true);
				}else {
					p.setChecked(false);
				}
				permissionMap.put(p.getId(), p);
			}
			//站在子节点考虑，通过子节点去匹配父节点
			for ( Permission p : ps ) {
				Permission child = p;	//把每个节点都看成是子节点
				if ( child.getPid() == 0 ) {
					//顶级节点
					permissions.add(p);
				} else {
					//父节点 = permissionMap.get(child.getPid())通过子节点的 Pid 来匹配父节点的 Id
					Permission parent = permissionMap.get(child.getPid());
					//组合父子节点的关系
					parent.getChildren().add(child);
				}
			}
			return permissions;
		}
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		 List<Permission> permissions = new ArrayList<Permission>();
		 /* 1：模拟数据	
		  * 	 Permission root = new Permission();
		 		 root.setName("顶级节点");
		 		 Permission child = new Permission();
		 		 child.setName("子节点");
		 		 root.getChildren().add(child);
		 		 permissions.add(root);	
		 */		 
		 		 // 读取树形结构的数据
		 /* 2：不合理的数据库数据
		  * 		 Permission root = permissionService.queryRootPermission();
		 		 
		 		 List<Permission> childPermissions = permissionService.queryChildPermissions(root.getId());
		 		 
		 		 for (Permission childPermission : childPermissions) {
		 			 List<Permission> hildchildPermissions = permissionService.queryChildPermissions(childPermission.getId());
		 			 childPermission.setChildren(hildchildPermissions);
		 		 }
		 		 
		 		 root.setChildren(childPermissions);
		 		 permissions.add(root);
		 */		
		 /* 3：递归查询数据，效率低下
		 		 Permission parent = new Permission();
		 		 parent.setId(0);
		 		 queryChildPermissions(parent);
		 		 return parent.getChildren();
		 */
		 /* 4：Array查询，没有用到索引，就需要线型匹配
		  * 		需要每条数据跟本身所有的数据匹配
		  * 		例（10 ：最大（10*10））
		 		List<Permission> ps = permissionService.queryAll();
		 		
		 		//站在子节点考虑，通过子节点去匹配父节点
		 		for (Permission p : ps) {
		 			Permission child = p; //把每个节点都看成是子节点
		 			if( p.getPid() == 0) {
		 				//顶级节点
		 				permissions.add(p);
		 			}else {
		 				for (Permission innerPermission : ps) {
		 					if( child.getPid().equals(innerPermission.getId())) {
		 						//父节点
		 						Permission parent = innerPermission;
		 						//组合父子节点的关系
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
		//组装数据到 Map
		for (Permission p : ps) {
			permissionMap.put(p.getId(), p);
		}
		//站在子节点考虑，通过子节点去匹配父节点
		for ( Permission p : ps ) {
			Permission child = p;	//把每个节点都看成是子节点
			if ( child.getPid() == 0 ) {
				//顶级节点
				permissions.add(p);
			} else {
				//父节点 = permissionMap.get(child.getPid())通过子节点的 Pid 来匹配父节点的 Id
				Permission parent = permissionMap.get(child.getPid());
				//组合父子节点的关系
				parent.getChildren().add(child);
			}
		}
		return permissions;
	}
	
	
	/**
	 * 3递归查询许可信息:
	 * 	1方法自己调用自己
	 *  2 方法存在跳出的逻辑
	 *  3方法调用参数有规律
	 *  4递归算法，效率较低
	 * @param parent
	 */
//	private void queryChildPermissions( Permission parent) {
//		List<Permission> childPermissions = permissionService.queryChildPermissions(parent.getId());
//		
//		//如果 childPermissions 为空就跳出了
//		for (Permission permission : childPermissions) {
//			queryChildPermissions(permission);
//		}
//		parent.setChildren(childPermissions);
//	}
}
