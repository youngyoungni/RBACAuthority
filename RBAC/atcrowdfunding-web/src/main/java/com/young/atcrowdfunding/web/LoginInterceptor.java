package com.young.atcrowdfunding.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 	登录拦截器
 * @author Youngni
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * 	控制器执行前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 判断当前用户是否已经登录
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null) {
			String path = session.getServletContext().getContextPath();
			response.sendRedirect(path+"/login");
			return false;
		}
		return true;	// 决定下面的业务，不去控制器
	}

	/**
	 * 	控制器执行完
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 	完成视图渲染之后
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
