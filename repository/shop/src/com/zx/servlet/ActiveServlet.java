package com.zx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.java.browser.net.ProxyService;
import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;
import com.zx.service.proxy.ServiceProxyUtils;

/**
 *处理用户激活操作
 */
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ActiveServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取连接带过来的激活码
		String activeCode = request.getParameter("activeCode");
		
		//创建服务层对象
		UserService userService = new ServiceProxyUtils().bindService(new UserServiceImpl());
		 
		userService.active(activeCode);
		request.getRequestDispatcher("/WEB-INF/view/front/active.jsp").forward(request, response);
	}

}
