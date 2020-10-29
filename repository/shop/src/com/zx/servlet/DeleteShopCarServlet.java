package com.zx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.service.ShopCarService;
import com.zx.service.impl.ShopCarServiceImpl;
import com.zx.service.proxy.ServiceProxyUtils;
import com.zx.vo.User;


/**
 * 删除当前用户购物车中商品信息
 *
 **/
@WebServlet("/deleteShopCar.do")
public class DeleteShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteShopCarServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//创建服务层对象
		ShopCarService service =  new ServiceProxyUtils().bindService(new ShopCarServiceImpl());
		
	   //获取需要删除的商品id
		String articleId = request.getParameter("articleId");
		//从session中获取用户信息
		User user = (User)request.getSession().getAttribute("session_user");

		//根据用户id以及需要删除的商品id进行删除操作
		service.deleteShopCar(articleId,Integer.parseInt(user.getId()));

		//展示购物车中商品信息
		response.sendRedirect(request.getContextPath()+"/showShopCar.do");

	}
}
