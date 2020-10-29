package com.zx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;
import com.zx.service.proxy.ServiceProxyUtils;

/**
 * 邮箱的异步验证
 */
@WebServlet("/verifyEmail")
public class VerifyEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VerifyEmailServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取界面传递过来的参数
    	String email = request.getParameter("user.email");
    	// 创建服务层对象
     	UserService userService =  new ServiceProxyUtils().bindService(new UserServiceImpl());
     	if(userService.findUserByEmail(email)) {
     		response.getWriter().print("邮箱已存在！");
     	}     	  	
	}
    	
}

