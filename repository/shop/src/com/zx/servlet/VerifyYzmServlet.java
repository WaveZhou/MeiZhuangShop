package com.zx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *处理注册界面验证信息的逻辑
 */
@WebServlet("/verifyYzm")
public class VerifyYzmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VerifyYzmServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取界面传递过来的参数
    	String yzm1 = request.getParameter("user.yzm");
     	
     	//验证验证码是否正确
     	String yzm2 = (String) request.getSession().getAttribute("yzm");

     	if(!yzm1.equals(yzm2)) {
     		response.getWriter().print("验证码不正确！");
     	}	
	}

}
