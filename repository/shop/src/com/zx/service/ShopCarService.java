package com.zx.service;

import java.util.List;

import com.zx.vo.ShopCar;
import com.zx.vo.User;


/**
 * 购物车服务层
 */
public interface ShopCarService {


	//根据用户id以及商品id获取购物车中的当前用户购物车的商品信息
	ShopCar getShopCarByArticleIdAndUserId(String shopId, int id);
	
	//加入商品至购物车
	void addShop(String shopId, int id, String buyNum);

	//更新购物车中商品信息
	void updateShop(ShopCar shopCar);

	//根据用户id获取购物车商品信息
	List<ShopCar> getShopCarByUserId(User user);

	//删除购物车中商品信息
	void deleteShopCar(String articleId, int id);
	
	//清空购物车商品信息
	void deleteAllShopCar(int userId);

}
