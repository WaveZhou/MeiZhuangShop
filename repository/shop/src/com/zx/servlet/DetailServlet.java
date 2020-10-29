package com.zx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.service.ArticleService;
import com.zx.service.impl.ArticleServiceImpl;
import com.zx.service.proxy.ServiceProxyUtils;
import com.zx.vo.Article;

/**
 * 展示商品详细信息
 */
@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DetailServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 创建服务层对象
		ArticleService articleService =  new ServiceProxyUtils().bindService(new ArticleServiceImpl());
		
		//获取界面传过来的id信息
		String id = request.getParameter("id");
		
		Article article = articleService.findArticleById(id);
		request.setAttribute("article", article);
		
		request.getRequestDispatcher("/WEB-INF/view/front/detial.jsp").forward(request, response);
	}

}
