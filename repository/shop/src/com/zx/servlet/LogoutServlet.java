package com.zx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.common.util.CookieUtil;

/**
 * 处理用户退出的逻辑
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//用户退出要清除用户的session_user和cookie
		request.getSession().removeAttribute("session_user");
		Cookie cookie = CookieUtil.getCookie(request, "rem");
		cookie.setMaxAge(0);
		//要把cookie响应回浏览器，这样cookie才会生效
		response.addCookie(cookie);
		//重定向至首页
		response.sendRedirect(request.getContextPath()+"/index");
	}

}
