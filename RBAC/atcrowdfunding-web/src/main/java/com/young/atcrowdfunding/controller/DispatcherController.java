package com.young.atcrowdfunding.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.atcrowdfunding.bean.AJAXResult;
import com.young.atcrowdfunding.bean.User;
import com.young.atcrowdfunding.service.UserService;

@Controller
public class DispatcherController {

	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
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
			String errorMsg = "登录失败！";
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
			result.setSuccess(true);
		}else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//session 失效
		session.invalidate();
		return "redirect:/login";
	}
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
}
