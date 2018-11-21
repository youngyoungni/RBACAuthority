package com.young.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.atcrowdfunding.bean.AJAXResult;
import com.young.atcrowdfunding.bean.Role;
import com.young.atcrowdfunding.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping( value="/updates", method = RequestMethod.POST)
	@ResponseBody
	public Object update(Integer[] roleId) {
		AJAXResult result = new AJAXResult();
		try {
			roleService.updateRoleList(roleId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			// TODO: handle exception
		}
		return result;
	}
	
	@RequestMapping( value="/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(Role role) {
		AJAXResult result = new AJAXResult();
		try {
			roleService.updateRole(role);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			// TODO: handle exception
		}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id , Model model) {
		try {
			Role role = roleService.queryById(id);
			model.addAttribute("role", role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "role/edit";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insert(Role role) {
		AJAXResult result = new AJAXResult();
		try {
			roleService.insert(role);
			result.setSuccess(true);
		}catch(Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/add" , method = RequestMethod.GET)
	public String add() {
		return "role/add";
	}
	
	@RequestMapping( value = "/pageQuery" , method = RequestMethod.POST)
	@ResponseBody
	public Object pageQuery(@RequestParam(required = false) String queryText
			,@RequestParam(required = false, defaultValue = "n") String isDelete
			,@RequestParam(required = false, defaultValue = "1") Integer pageNo
			,@RequestParam(required = false, defaultValue = "5") Integer pageSize) {
		AJAXResult result = null;
		try {
			result = roleService.pageQuery(pageNo,pageSize,isDelete,queryText);
			result.setSuccess(true);
		}catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping( value = "/index", method = RequestMethod.GET )
	public String index() {
		return "role/role_index";
	}
	
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign( Integer roleid, Integer[] permissionids) {
		AJAXResult result = new AJAXResult();
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleid", roleid);
			map.put("permissionids", permissionids);
			
			roleService.insertRolePermission(map);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
		
	}
	
	@RequestMapping( value = "/assign", method = RequestMethod.GET )
	public String assign( Integer id , Model model) {
		
		
		model.addAttribute("roleid", id);
		return "role/assign";
	}
	
}
