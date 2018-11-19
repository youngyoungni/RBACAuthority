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
	 *	 异步：用户首页
	 * @param pageNo	：页码
	 * @param pageNumber：每页显示多少行（条）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String queryText, @RequestParam(required = false, defaultValue = "1")Integer pageNo 
			, @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		AJAXResult result = new AJAXResult();
		
		try {
			//准备数据-分页
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", (pageNo - 1 ) * pageSize );
			map.put("size", pageSize);
			map.put("queryText", queryText);
			List<User> users = userService.pageQueryData(map);
			
			// 数据库总数据条数
			int totalSize = userService.pageQueryCount(map);
			int totalNo = 0 ;
			if( totalSize % pageSize == 0) {
				totalNo = totalSize / pageSize;
			}else {
				totalNo = totalSize / pageSize + 1 ;
			}
			
			//分页对象
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
	 * 	去用户信息页
	 * @return
	 */
	@RequestMapping("/indexAjax")
	public String indexAjax() {
		return "user/indexAjax";
	}
	
	/**
	 *	 同步：用户首页
	 * @param pageNo	：页码
	 * @param pageNumber：每页显示多少行（条）
	 * @return
	 */
	@RequestMapping("/index")
	public String index(@RequestParam(required = false, defaultValue = "1")Integer pageNo 
			, @RequestParam(required = false, defaultValue = "2") Integer pageSize
			, Model model) {
		
		//准备数据-分页
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (pageNo - 1 ) * pageSize );
		map.put("size", pageSize);
		
		List<User> users = userService.pageQueryData(map);
		model.addAttribute("users", users);
		
		// 当前页
		model.addAttribute("pageNo",pageNo);

		
		// 数据库总数据条数
		int totalSize = userService.pageQueryCount(map);
		int totalNo = 0 ;
		if( totalSize % pageSize == 0) {
			totalNo = totalSize / pageSize;
		}else {
			totalNo = totalSize / pageSize + 1 ;
		}
		// 最大页码
		model.addAttribute("totalNo", totalNo);
		
		return "user/index";
	}
	
}
