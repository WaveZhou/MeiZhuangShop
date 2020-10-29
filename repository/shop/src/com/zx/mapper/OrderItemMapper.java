package com.zx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.zx.vo.OrderItem;

/**
 * OrderItemMapper 数据访问类
 * @author XXX
 * @email xxxx@qq.com
 * @date 2019-09-26 10:22:52
 * @version 1.0
 */
public interface OrderItemMapper {

	//保存订单明细
	@Insert("insert into ec_order_item(order_id,article_id,order_num)  values(#{orderId},#{articleId},#{orderNum})")
	void saveOrderItem(OrderItem orderItem);

	//根据订单id获取订单明细
	List<OrderItem> findOrderItemByOrderId(int id);



}