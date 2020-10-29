package com.zx.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zx.common.annotation.AutoWired;
import com.zx.common.util.ConnectionUtils;
import com.zx.mapper.ArticleMapper;
import com.zx.mapper.ShopcarMapper;
import com.zx.service.ShopCarService;
import com.zx.vo.Article;
import com.zx.vo.ShopCar;
import com.zx.vo.User;



/**
 * 购物车服务层
 */
public class ShopCarServiceImpl implements ShopCarService {

	@AutoWired(value=true)
	private ShopcarMapper shopCarDao;
	
	@AutoWired(value=true)
    private ArticleMapper articleDao;
	

	/* 
	 * 加入商品至购物车
	 */
	public void addShop(String shopId, int userId, String buyNum) {
		
			shopCarDao.addShop(Integer.valueOf(shopId),userId,Integer.valueOf(buyNum));
			
		
	}

	/* 
	 * 根据用户id以及商品id获取购物车中的当前用户购物车的商品信息
	 *（用于查看购物车中是否有该商品的信息，无则直接加入购物车，有则增加购买数量）
	 */
	public ShopCar getShopCarByArticleIdAndUserId(String shopId, int userId) {
	
		ShopCar shopCar = shopCarDao.getShopCarByArticleIdAndUserId(Integer.valueOf(shopId),userId);
		
		return shopCar;
	}

	/* 
	 * 修改购物车中商品信息
	 */
	public void updateShop(ShopCar shopCar) {
		
		shopCarDao.updateShop(shopCar);
		
	}

	/* 
	 *  根据用户id获取购物车商品信息，用于展示购物车中商品信息
	 */
	public List<ShopCar> getShopCarByUserId(User user) {
		
		List<ShopCar> shopCars = shopCarDao.getShopCarByUserId(user);

		
		return shopCars;
	}

	/* 
	 *  删除购物车中商品信息
	 */
	public void deleteShopCar(String articleId, int userId) {
		
		shopCarDao.deleteShopCar(Integer.valueOf(articleId),userId);
		
	}

	
	/*
	 *清空购物车信息 
	 */
	public void deleteAllShopCar(int userId) {
		
		shopCarDao.removeShopCarByUserId(userId);
		
	}
	
}



