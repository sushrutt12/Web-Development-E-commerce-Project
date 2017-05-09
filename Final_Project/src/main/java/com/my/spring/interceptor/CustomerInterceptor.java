package com.my.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.spring.pojo.Person;

public class CustomerInterceptor extends HandlerInterceptorAdapter{

//	String errorPage;
//
//	public String getErrorPage() {
//		return errorPage;
//	}
//
//	public void setErrorPage(String errorPage) {
//		this.errorPage = errorPage;
//	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		System.out.println("in customer interceptor");
		
		HttpSession session = (HttpSession) request.getSession();
//		String contextPath=session.getServletContext();
		String cp=request.getContextPath();
		
//		System.out.println("Context Path>>>>>>>>>>"+cp);
		Person p=(Person)session.getAttribute("user");
		if(!(p.getRole().equals("Customer"))){
//			System.out.println("no user");
			response.sendRedirect(cp+"/public/401.htm");
			return false;
		}
		else{
		return true;
		}
		
		
		
	}
	
}
