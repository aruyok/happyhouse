package com.ssafy.dto;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class NoticeInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		System.out.println(session);
		UserDto memberVO = (UserDto)session.getAttribute("member");
		
		// 로그인 안됨
		if(memberVO == null) {
			response.sendRedirect("/member/login");
			return false;
		// 로그인 되었으나 관리자가 아님
		}else if(memberVO.getRole().equals("2")){
				request.setAttribute("message", "접근권한이 없습니다.");
				request.setAttribute("path", "/notice/list");
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/adminAccess.jsp");
				view.forward(request, response);
				return false;
		//관리자 확인됨
		}else {
			return true;
		}
	}
}