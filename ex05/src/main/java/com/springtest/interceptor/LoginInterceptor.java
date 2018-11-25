package com.springtest.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		
		if(userVO != null) {
			logger.info("new login success");
			session.setAttribute(LOGIN, userVO);
			
			if(request.getParameter("useCookie") != null) { // 자동로그인 선택한경우
				
				logger.info("remember me ..................");
				Cookie loginCookie = new Cookie("loginCookie",  session.getId()); // 현재  세션의 아이디값을 보관, 세션 아이디는 세션 쿠키의 값을 의미
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60 * 60 * 24 * 7); // 일주일간 보관(브라우저에)
			}
			
			//response.sendRedirect("/");
			Object dest = session.getAttribute("dest");
			
			response.sendRedirect(dest != null ? (String)dest:"/");
		}
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) != null) { // 기존 HttpSession에 남아있는 정보가 있는 경우 정보를 삭제
			logger.info("clear login data before");
			session.removeAttribute(LOGIN);
		}
		return true;
	}
}
