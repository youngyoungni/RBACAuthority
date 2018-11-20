package com.young.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.atcrowdfunding.bean.AJAXResult;
import com.young.atcrowdfunding.bean.Page;
import com.young.atcrowdfunding.bean.Role;
import com.young.atcrowdfunding.bean.User;
import com.young.atcrowdfunding.service.RoleService;
import com.young.atcrowdfunding.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/updates")
	@ResponseBody
	public Object updates(Integer[] userid) {
		AJAXResult result = new AJAXResult();
		try {
			Map< String, Object> map = new HashMap< String, Object>();
			map.put("userids", userid);
			userService.updates(map);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(User user) {
		AJAXResult result = new AJAXResult();
		try {
			userService.update(user);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/edit")
	public String edit(String id, Model model) {
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	
	@RequestMapping( value = "/assign", method = RequestMethod.GET)
	public String assign(String id, Model model) {
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		
		List<Role> roles = roleService.queryAll();
		
		List<Role> assignedRoles = new ArrayList<Role>();
		List<Role> unassignedRoles = new ArrayList<Role>();
		
		// ��ȡ��ϵ������ݣ�
		List<Integer> roleids = userService.queryRoleidsByUserid(id);
		
		for (Integer integer : roleids) {
			System.out.println("���е�Ȩ��(roleid)��"+integer);
		}

		
		for (Role role : roles) {
			System.out.println("���е�Ȩ��(roleid)��"+role.getRoleId());
			
			if( roleids.contains(Integer.parseInt(role.getRoleId()))) {
				assignedRoles.add(role);
			}else {
				unassignedRoles.add(role);
			}
		}
		
		model.addAttribute("assignedRoles", assignedRoles);
		model.addAttribute("unassignedRoles", unassignedRoles);
		
		return "user/assign";
	}

	
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign( Integer userid ,Integer[] unassignroleids) {
		AJAXResult result = new AJAXResult();
		try {
			//���ӹ�ϵ������
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);
			map.put("roleids", unassignroleids);
			
			userService.insertUserRoles(map);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/dounAssign")
	public Object dounAssign( Integer userid ,Integer[] assignroleids) {
		AJAXResult result = new AJAXResult();
		try {
			//ɾ����ϵ������
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);
			map.put("roleids", assignroleids);
			
			userService.deleteUserRoles(map);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 	�����û�
	 * @param user
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(User user) {
		AJAXResult result = new AJAXResult();
		try {
			userService.insert(user);
			result.setSuccess(true);
		}catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}
	
	/**
	 *	 �첽���û���ҳ
	 * @param pageNo	��ҳ��
	 * @param pageNumber��ÿҳ��ʾ�����У�����
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String isdelete,String queryText, @RequestParam(required = false, defaultValue = "1")Integer pageNo 
			, @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		AJAXResult result = new AJAXResult();
		
		try {
			//׼������-��ҳ
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageNo - 1 ) * pageSize );
			map.put("size", pageSize);
			map.put("queryText", queryText);
			map.put("isdelete", isdelete);
			List<User> users = userService.pageQueryData(map);
			
			// ���ݿ�����������
			int totalSize = userService.pageQueryCount(map);
			int totalNo = 0 ;
			if( totalSize % pageSize == 0) {
				totalNo = totalSize / pageSize;
			}else {
				totalNo = totalSize / pageSize + 1 ;
			}
			//��ҳ����
			Page<User> userPage = new Page<User>(users,pageNo,totalNo,totalSize);
			
			result.setData(userPage);
			result.setSuccess(true);
		}catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 	ȥ�û���Ϣҳ
	 * @return
	 */
	@RequestMapping("/indexAjax")
	public String indexAjax() {
		return "user/indexAjax";
	}
	
	/**
	 *	 ͬ�����û���ҳ
	 * @param pageNo	��ҳ��
	 * @param pageNumber��ÿҳ��ʾ�����У�����
	 * @return
	 */
	@RequestMapping("/index")
	public String index(@RequestParam(required = false, defaultValue = "1")Integer pageNo 
			, @RequestParam(required = false, defaultValue = "2") Integer pageSize
			, Model model) {
		
		//׼������-��ҳ
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (pageNo - 1 ) * pageSize );
		map.put("size", pageSize);
		
		List<User> users = userService.pageQueryData(map);
		model.addAttribute("users", users);
		
		// ��ǰҳ
		model.addAttribute("pageNo",pageNo);

		
		// ���ݿ�����������
		int totalSize = userService.pageQueryCount(map);
		int totalNo = 0 ;
		if( totalSize % pageSize == 0) {
			totalNo = totalSize / pageSize;
		}else {
			totalNo = totalSize / pageSize + 1 ;
		}
		// ���ҳ��
		model.addAttribute("totalNo", totalNo);
		
		return "user/index";
	}
	
}
