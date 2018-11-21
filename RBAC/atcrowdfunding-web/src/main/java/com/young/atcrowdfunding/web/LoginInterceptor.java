package com.young.atcrowdfunding.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 	��¼������
 * @author Youngni
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	/**
	 * 	������ִ��ǰ
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// �жϵ�ǰ�û��Ƿ��Ѿ���¼
		HttpSession session = request.getSession();
		if( session.getAttribute("loginUser") == null) {
			String path = session.getServletContext().getContextPath();
			response.sendRedirect(path+"/login");
			return false;
		}
		return true;	// ���������ҵ�񣬲�ȥ������
	}

	/**
	 * 	������ִ����
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 	�����ͼ��Ⱦ֮��
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
