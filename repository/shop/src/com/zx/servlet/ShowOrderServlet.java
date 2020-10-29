package com.zx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zx.service.OrderService;
import com.zx.service.impl.OrderServiceImpl;
import com.zx.service.proxy.ServiceProxyUtils;
import com.zx.vo.Order;
import com.zx.vo.User;

/**
 * 展示订单信息
 */
@WebServlet("/showOrder.do")
public class ShowOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowOrderServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建服务层对象
		OrderService orderService =  new ServiceProxyUtils().bindService(new OrderServiceImpl());
		
		//从session中获取用户信息
		User user = (User)request.getSession().getAttribute("session_user");
		List<Order> orders = orderService.findOrderArticle(Integer.parseInt(user.getId()));
		request.setAttribute("orders", orders);
		
		request.getRequestDispatcher("/WEB-INF/view/front/order.jsp").forward(request, response);
	}

}
