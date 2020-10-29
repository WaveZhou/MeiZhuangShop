package com.zx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.common.util.CookieUtil;
import com.zx.service.UserService;
import com.zx.service.impl.UserServiceImpl;
import com.zx.service.proxy.ServiceProxyUtils;
import com.zx.vo.User;

/**
 * 处理登录界面异步请求
 */
@WebServlet("/verifyLogin")
public class VerifyLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VerifyLogin() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取界面传输过来的用户名和密码
		String loginName = request.getParameter("loginName");
		String passWord = request.getParameter("passWord");
		// 获取界面传递过来的验证码
		String yzm1 = request.getParameter("yzm");
		// 获取界面传过来的是否记住一周
		String rem = request.getParameter("rem");

		// 得到session里面的验证码
		String yzm2 = (String) request.getSession().getAttribute("yzm");

		// 创建服务层对象
		UserService userService =  new ServiceProxyUtils().bindService(new UserServiceImpl());
		
		// 创建user对象保存用户信息
		User user = userService.findUserActive(loginName, passWord);
//		user.setLoginName(loginName);
//		user.setPassword(passWord);
//		String id = user.getId();

		PrintWriter pw = response.getWriter();

		
		if (loginName.equals("") || loginName == null) {
			pw.write("1");
		} else if (passWord.equals("") || passWord == null) {
			pw.write("2");
		} else if (!yzm1.equals(yzm2)) {
			// 验证码不正确
			pw.write("3");
		} else if (!userService.findUserByLoginName(loginName)) {
			// 账号不存在
			pw.write("4");
		} else if (!userService.findUser(loginName, passWord)) {
			// 密码不存在
			pw.write("5");
		} else if (user == null) {
			// 账号没有激活
			pw.write("6");
		} else {
			// 将用户的信息存放在session中
			request.getSession().setAttribute("session_user", user);
			// 如果选中记住密码
			if ("1".equals(rem)) {
				String id = user.getId();
				StringBuffer cookieValue = new StringBuffer();
				cookieValue = cookieValue.append(loginName).append("_").append(passWord).append("_").append(id);
				CookieUtil.addCookie(request, response, "rem", cookieValue.toString(), 7 * 24 * 60 * 60);
			}
		}

	}

}
