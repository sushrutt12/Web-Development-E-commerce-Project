package com.my.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.spring.pojo.Person;

public class UserInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
		HttpSession session = (HttpSession) request.getSession();
		String cp=request.getContextPath();
		Person p=(Person)session.getAttribute("user");
		if(p == null){
			System.out.println("no user");
			response.sendRedirect(cp);
			return false;
		}
		else{
		return true;
		}
		
		
		
	}
	
}
