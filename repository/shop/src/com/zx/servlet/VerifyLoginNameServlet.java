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
 *处理注册界面验证信息的逻辑
 */
@WebServlet("/verifyLoginName")
public class VerifyLoginNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VerifyLoginNameServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取界面传递过来的参数
    	String loginName = request.getParameter("user.loginName");
    	   	
     	// 创建服务层对象
     	UserService userService =  new ServiceProxyUtils().bindService(new UserServiceImpl());
     	if(userService.findUserByLoginName(loginName)) {
     		response.getWriter().print("账号已存在！");
     	}     	  	
	}

}
