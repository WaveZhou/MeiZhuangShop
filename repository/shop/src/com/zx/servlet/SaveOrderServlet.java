package com.zx.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.zx.vo.OrderItem;
import com.zx.vo.User;

/**
 * 处理提交订单的逻辑
 */

@WebServlet("/saveOrder.do")
public class SaveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveOrderServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 创建服务层对象
		OrderService orderService =  new ServiceProxyUtils().bindService(new OrderServiceImpl());
		// 从session中获取用户信息
		User user = (User) request.getSession().getAttribute("session_user");
	
		//获取订单中所有商品相关信息
		String orderInfo = request.getParameter("orderInfo");
		
		if(orderInfo != null && !orderInfo.equals("")) {
			//获取到每一个商品的订单信息
			String[] orderInfos = orderInfo.split("#");
			
			//创建订单对象
			Order order = new Order();
			//设置下单时间
			order.setCreateDate(new Date());
			//设置用户id
			order.setUserId(Integer.parseInt(user.getId()));
			
			//创建时间格式化工具
			SimpleDateFormat adf = new SimpleDateFormat("yyyyMMddHHmmss");
			//生成订单编号 （唯一）
			StringBuffer sbf = new StringBuffer();
			sbf.append("wyj-").append(adf.format(new Date())).append("-").append(user.getId());
			order.setOrderCode(sbf.toString());
			
			List<OrderItem> items = new ArrayList<>();
			double totalMoney = 0.0;
			for(int i=1;i<orderInfos.length;i++) {
				String[] infos = orderInfos[i].split("_");
				//获取商品id
		    	String articleId = infos[0];
		    	//获取购买数量
		    	String buyNum = infos[1];
		    	//获取商品金额(打折后的)
		    	String money = infos[2];
		    	//计算总金额
		    	totalMoney += Double.valueOf(money);
		    	OrderItem item = new OrderItem();
		    	//封装订单明细对象
		    	item.setArticleId(Integer.valueOf(articleId));
		    	item.setOrderNum(Integer.valueOf(buyNum));
		    	items.add(item);
			}
			//设置总金额
		    order.setAmount(totalMoney);
		    //将订单明细存放在订单中
		    order.setOrderItem(items);
		    //保存订单
		    orderService.saveOrder(order);
		    
		    //跳转至展示订单信息的Servlet  
		    response.sendRedirect(request.getContextPath()+"/showOrder.do");
		}else{
	    	response.sendRedirect(request.getContextPath()+"/index");
	    }
	
	}

}
