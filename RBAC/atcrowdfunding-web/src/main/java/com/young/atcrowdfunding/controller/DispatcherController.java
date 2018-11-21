package com.young.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.atcrowdfunding.bean.AJAXResult;
import com.young.atcrowdfunding.bean.Permission;
import com.young.atcrowdfunding.bean.User;
import com.young.atcrowdfunding.service.PermissionService;
import com.young.atcrowdfunding.service.UserService;

@Controller
public class DispatcherController {

	@Autowired
	private PermissionService permissionService;
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/error")
	public String error() {
		return "error";
	}
	
	/**
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin( User user , Model model) {
		User dbUser = userService.queryLogin(user);
		if( dbUser != null) {
			return "main";
		}else {
			String errorMsg = "登录失败";
			model.addAttribute("errorMsg", errorMsg);
			return "redirect:/login";
		}
	}
	
	@ResponseBody
	@RequestMapping("/doAJAXLogin")
	public Object doAJAXLogin( User user, HttpSession session) {
		AJAXResult result = new AJAXResult();
		User dbUser = userService.queryLogin(user);
		if( dbUser != null) {
			session.setAttribute("loginUser", dbUser);
			
			//分配权限，获取用户权限信息
			List<Permission>  permissions= permissionService.queryPermissionidByUser(dbUser);
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			Permission root = null;
			
			//保存用户 权限
			Set<String> uriSet = new HashSet<String>();
			
			for ( Permission permission : permissions ) {
				permissionMap.put(permission.getId(), permission);
				if ( permission.getUrl() != null && !"".equals(permission.getUrl()) ) {
					uriSet.add(session.getServletContext().getContextPath() + permission.getUrl());
				}
			}
			
			session.setAttribute("authUriSet", uriSet);
			for ( Permission permission : permissions ) {
				Permission child = permission;
				if ( child.getPid() == 0 ) {
					root = permission;
				} else {
					Permission parent = permissionMap.get(child.getPid());
					parent.getChildren().add(child);
				}
			}
			session.setAttribute("rootPermission", root);
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//session 澶辨晥
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
}
