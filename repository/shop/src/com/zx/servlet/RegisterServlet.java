package com.zx.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;
import com.zx.service.proxy.ServiceProxyUtils;
import com.zx.vo.User;

/**
 * 处理注册界面逻辑
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//跳转至用户信息注册页面
		request.getRequestDispatcher("/WEB-INF/view/front/register.jsp").forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = new User();
    	//获取界面传递过来的参数
    	String loginName = request.getParameter("user.loginName");
    	user.setLoginName(loginName);
		//获取密码
		String passWord = request.getParameter("user.password");
		user.setPassword(passWord);
		
	    //获取真实姓名
	    String name = request.getParameter("user.name");
	    user.setName(name);
	    //获取性别
	    String sex = request.getParameter("user.sex");
	    user.setSex(sex);
	    //获取地址
	    String address = request.getParameter("user.address");
	    user.setAddress(address);
	    //获取电话号码
	    String phone = request.getParameter("user.phone");
	    user.setPhone(phone);
	    //创建时间
	    user.setCreateDate(new Date());
	    //获取邮箱
	    String email = request.getParameter("user.email");
	    //邮箱
	    user.setEmail(email);
	    //设置激活码
	    String acticeCode = UUID.randomUUID().toString();
	    user.setActive(acticeCode);
	    
	    UserService userService =  new ServiceProxyUtils().bindService(new UserServiceImpl());
    	userService.save(user,request);
		request.setAttribute("message", "恭喜您，注册成功!请登录邮箱["+email+"]进行激活！");
		request.getRequestDispatcher("/WEB-INF/view/front/login.jsp").forward(request, response);
 	
    }
  
}
