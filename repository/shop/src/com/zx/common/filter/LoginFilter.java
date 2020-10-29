package com.zx.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zx.vo.User;

/**
 *登录拦截器
 */
@WebFilter("*.do")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取session
		HttpSession session = ((HttpServletRequest)request).getSession();
		//获取用户信息
		User user = (User)session.getAttribute("session_user");
		//拦截
		if(user != null) {
			//放行
			chain.doFilter(request, response);
		}else {
			request.setAttribute("message", "您尚未登录，请先登录再进行相关操作");
			request.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(request, response);
		}

	}

}
