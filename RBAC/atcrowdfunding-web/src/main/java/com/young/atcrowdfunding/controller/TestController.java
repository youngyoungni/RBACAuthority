package com.young.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.atcrowdfunding.bean.User;
import com.young.atcrowdfunding.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/queryAll")
	@ResponseBody
	public Object queryAll() {
		List<User> users= userService.queryAll();
		return users;
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/json")
	@ResponseBody
	public Object json() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "’≈…Ω");
		return map;
	}
}
