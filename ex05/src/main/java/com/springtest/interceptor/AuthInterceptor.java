package com.springtest.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.springtest.domain.UserVO;
import com.springtest.service.UserService;


//특정 경로에 접근하는 경우 현재 사용자가 로그인한 상태의 사용자인지를 체크하는 역활
public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Autowired
	private UserService service;
	
	@Override // preHandle()을 이용하여 현재 사용자가  로그인한 상태인지를 체
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") != null) { // 사용자가 로그인하지 않은 상태라면  로그인하는 '/user/login'으로 이동
			logger.info("current user is not logined");

			saveDest(request);
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			//현재 사용자 세션에 login이 존재하지 않지만, 쿠키 중에서 loginCookie가 존재 할때 처리가 진행됨.
			if(loginCookie != null) {
				
				UserVO userVO = service.checkLoginBefore(loginCookie.getValue());
				
				logger.info("USERVO: " + userVO);
				
				if(userVO != null) {
					session.setAttribute("login", userVO);
					return true; //과거에  사용자 정보가 존재하는지 확인하여 존재하면 HttpSession에 다시 사용자 정보를 넣어줌
				}
			}
			
			response.sendRedirect("user/login");
			return false;
		}
		return true;
	}
	
	private void saveDest(HttpServletRequest req) {
		
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		if (req.getMethod().equals("GET")) {
			logger.info("dest: " + (uri + query));
			req.getSession().setAttribute("dest", uri + query);
		}
	}

}
