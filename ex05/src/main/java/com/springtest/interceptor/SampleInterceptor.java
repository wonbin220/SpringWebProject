package com.springtest.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		
		System.out.println("post handle............");
		
		Object result = modelAndView.getModel().get("result");
		
		if(result != null) {
			request.getSession().setAttribute("result", result);
			response.sendRedirect("/doA");
		}
	}
	
	//실행되는 로그를 보면 preHandle()이 가장 먼저 호출되어 메소드가 동작한후 postHandle()이 동작함
	//Object handler의 Handler는 현재 실행하려는 메소드 자체를 의미, 이를 활용하여 현재 실행되는 컨트롤러를 파악하건, 추가적인 메소드를 실행하는 등의 작업이 가능
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		System.out.println("pre handle..................");
		
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		System.out.println("Bean: " + method.getBean());
		System.out.println("Method: " + methodObj);
		
		return true;
	}
	
	
}
