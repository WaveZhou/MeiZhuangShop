package com.zx.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 字符编码集过滤
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {

    public CharacterFilter() {
        //无参构造器
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//预处理
		//设置请求编码
		request.setCharacterEncoding("utf-8");
		
		//设置响应编码
		response.setContentType("text/html;charset=utf-8");

		chain.doFilter(request, response);
		
	}
}
