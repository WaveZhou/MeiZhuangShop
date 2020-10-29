package com.zx.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import com.sun.mail.smtp.SMTPMessage;
import com.zx.common.annotation.AutoWired;
import com.zx.common.util.ConnectionUtils;
import com.zx.mapper.UserMapper;
import com.zx.service.UserService;
import com.zx.vo.User;

public class UserServiceImpl implements UserService {
	
	@AutoWired(value=true)
	private UserMapper userDao;
	
	// 查询用户账号是否存在
	public boolean findUserByLoginName(String loginName) {
		
		User user = userDao.findUserByLoginName(loginName);
		return user == null ? true : false;
	}

	// 保存用户注册信息到数据库
	public void save(User user, HttpServletRequest request) {
		try {
			
			userDao.save(user);
			
			
				// 创建Properties对象，用来封装邮件服务器相关信息
				Properties props = new Properties();
				// 设置邮件服务器的地址
				props.setProperty("mail.smtp.host", "smtp.qq.com");
				// 邮件服务器需要权限，指定用户必须登录邮件服务器才能发送邮件
				props.setProperty("mail.smtp.auth", "true");

				// 创建Authenticator的实例，实现账户、密码的鉴权。
				Authenticator auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("1543150122@qq.com", "oxyapqziokyjjgeh");
					}
				};

				// 通过Session与服务器建立连接
				Session session = Session.getInstance(props, auth);

				// 创建发送邮件对象，该对象主要用于封装邮件相关信息，比如 主题 发件人 邮件内容等
				SMTPMessage message = new SMTPMessage(session);

				// 设置邮件的主题
				message.setSubject("用户注册激活邮件，请勿回复，按照指引激活");
				// 设置邮件的内容
				message.setContent(
						"<a href='http://127.0.0.1:8080" + request.getContextPath() + "/active?activeCode="
								+ user.getActive() + "' target='_blank'>恭喜您注册成功，请点击该链接进行激活，无需回复！</a>",
						"text/html;charset=utf-8");

				// 设置发件人
				message.setFrom(new InternetAddress("1543150122@qq.com"));

				// 设置收件人 接收者类型由：TO(收件人)、CC(抄送)、BCC(密送)
				message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));

				// 发送邮件
				Transport.send(message);
				
				
				
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据用户激活码修改disabled，1：可以登录 0：不允许登录
	public void active(String activeCode) {
		
		userDao.active(activeCode);
	}

	// 查询数据库是否有选定的账户名和密码
	public boolean findUser(String loginName, String password) {
		
		User user = userDao.findUser(loginName, password);
		
		return user == null ? true : false;
	}

	// 查询数据库选定的账户名和密码是否激活
	public User findUserActive(String loginName, String password) {
		
		User user = userDao.findUserByActive(loginName, password);
		return user;
	}

	
	public boolean findUserByEmail(String email) {
		
		User user = userDao.findUserByEmail(email);
		
		return user == null ?true :false;

	}

}
