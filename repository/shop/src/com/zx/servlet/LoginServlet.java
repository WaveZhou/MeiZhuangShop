package com.zx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理用户登录请求
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//获取界面传输过来的用户名和密码
//		String loginName = request.getParameter("loginName");
//		String passWord = request.getParameter("passWord");
//	
//		//创建服务层对象
//		UserService userService = new UserServiceImpl();
//		boolean flag1 = userService.findUser(loginName, passWord);
//		boolean flag2 = userService.findUserActive(loginName, passWord);
//		if(flag1) {
//			if(flag2) {
//				request.getRequestDispatcher("index").forward(request, response);
//			}else {
//				String message = "您还没有激活账户，请先登录邮箱进行激活！";
//				request.setAttribute("message", message);
//				request.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(request, response);
//			}		
//		}else {
//			String message = "您的账号或者密码不存在，请核实后再登录";
//			request.setAttribute("message", message);
//			request.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(request, response);
//		}
//	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(request, response);
	}

}
