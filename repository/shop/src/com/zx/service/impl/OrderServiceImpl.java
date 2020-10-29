package com.zx.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zx.common.annotation.AutoWired;
import com.zx.common.util.ConnectionUtils;
import com.zx.mapper.ArticleMapper;
import com.zx.mapper.OrderItemMapper;
import com.zx.mapper.OrderMapper;
import com.zx.mapper.ShopcarMapper;
import com.zx.service.OrderService;
import com.zx.vo.Order;
import com.zx.vo.OrderItem;

public class OrderServiceImpl implements OrderService {

	@AutoWired(value=true)
   private OrderMapper orderDao;
	@AutoWired(value=true)
   private OrderItemMapper orderItemDao;
	@AutoWired(value=true)
   private ArticleMapper articleDao;
	@AutoWired(value=true)
   private ShopcarMapper shopCarDao;
   
	//根据用户id查询订单详细信息
	public List<Order> findOrderArticle(int userId) {

		
		List<Order> orders = orderDao.findOrderByUserId(userId);

		
		return orders;
	}


	//保存订单
	public void saveOrder(Order order) {
		
			
			//先保存订单再保存订单明细，订单以及订单明细要么同时保存成功，要么都失败
			orderDao.saveOrder(order);
			
			//获取订单主键id值
			int orderId = order.getId();
			
			for(int i=0;i<order.getOrderItem().size();i++){
				//订单明细里面的order_id全部都是订单表的自动增长的主键
				order.getOrderItem().get(i).setOrderId(orderId);
				//保存订单明细表
				orderItemDao.saveOrderItem(order.getOrderItem().get(i));
				
				//清空购物车
				shopCarDao.deleteShopCar(order.getOrderItem().get(i).getArticleId(), order.getUserId());
				//减少库存量
				articleDao.minusStorage(order.getOrderItem().get(i).getOrderNum(),order.getOrderItem().get(i).getArticleId());
				
				
			}
			
		
		
	}
	

}
