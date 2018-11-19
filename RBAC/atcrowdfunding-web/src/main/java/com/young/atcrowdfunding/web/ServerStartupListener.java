package com.young.atcrowdfunding.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener {

	/**
	 * 	Web ����������������
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// �� APP_PATH ���浽 application ��Χ��
		ServletContext application = sce.getServletContext();
		String path = application.getContextPath();
		System.out.println("application ��·��"+ path);
		application.setAttribute("APP_PATH", path);

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
