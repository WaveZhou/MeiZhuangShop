package com.zx.service;

import java.util.List;

import com.zx.vo.Order;

public interface OrderService {
	//根据用户id查询订单详细信息
	List<Order> findOrderArticle(int userId);
	
	//保存订单
	void saveOrder(Order order);

}
