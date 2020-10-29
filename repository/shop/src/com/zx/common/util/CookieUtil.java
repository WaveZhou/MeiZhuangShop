package com.zx.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	//通过名字得到cookie
	public static Cookie getCookie(HttpServletRequest request,String cookieName) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieName)) {
					return cookie;
				}
			}
		}
		return null;		
	}
	
	
	//添加cookie输入法
	public static void addCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String cookieValue,int age) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(age);
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
	}
	
}
