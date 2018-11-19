package com.young.atcrowdfunding.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener {

	/**
	 * 	Web 服务器启动，加载
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 将 APP_PATH 保存到 application 范围中
		ServletContext application = sce.getServletContext();
		String path = application.getContextPath();
		System.out.println("application 的路径"+ path);
		application.setAttribute("APP_PATH", path);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
