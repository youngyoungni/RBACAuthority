package com.young.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.atcrowdfunding.bean.AJAXResult;
import com.young.atcrowdfunding.bean.Page;
import com.young.atcrowdfunding.bean.User;
import com.young.atcrowdfunding.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 *	 �첽���û���ҳ
	 * @param pageNo	��ҳ��
	 * @param pageNumber��ÿҳ��ʾ�����У�����
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String queryText, @RequestParam(required = false, defaultValue = "1")Integer pageNo 
			, @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		AJAXResult result = new AJAXResult();
		
		try {
			//׼������-��ҳ
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageNo - 1 ) * pageSize );
			map.put("size", pageSize);
			map.put("queryText", queryText);
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
