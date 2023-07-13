package com.gdu.vinery.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gdu.vinery.domain.SleepUserDTO;
import com.gdu.vinery.mapper.UserMapper;

@Component
public class SleepUserCheckInterceptor implements HandlerInterceptor {
		
		@Autowired
		private UserMapper userMapper;
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			
			String userId = request.getParameter("userId");
			
			SleepUserDTO sleepUserDTO = userMapper.selectSleepUserById(userId);
			
			if(sleepUserDTO != null) {
				
				HttpSession session = request.getSession();
				session.setAttribute("sleepUserId", userId);
				
				response.sendRedirect(request.getContextPath() + "/user/wakeup.form");
				return false;	
			}
			
			return true;	
		}

}
