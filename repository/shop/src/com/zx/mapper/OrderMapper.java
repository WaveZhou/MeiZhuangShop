package com.zx.mapper;

import java.util.List;

import com.zx.vo.Order;

/**
 * OrderMapper 数据访问类
 * @author XXX
 * @email xxxx@qq.com
 * @date 2019-09-26 10:22:52
 * @version 1.0
 */
public interface OrderMapper {

	//保存订单
	void saveOrder(Order order);

	//根据用户id获取订单信息
	List<Order> findOrderByUserId(int userId);



}