package com.zx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.zx.vo.ShopCar;
import com.zx.vo.User;

/**
 * ShopcarMapper 数据访问类
 * @author XXX
 * @email xxxx@qq.com
 * @date 2019-09-26 10:22:52
 * @version 1.0
 */
public interface ShopcarMapper {

	//提交订单时，删除购物车中商品信息
	@Delete("delete from ec_shopcar where article_id = #{articleId} and user_id = #{userId}")
	void deleteShopCar(@Param("articleId")int articleId, @Param("userId")int userId);

	//添加商品至购物车
	@Insert("insert into ec_shopcar(article_id,user_id,buynum)  values(#{shopId},#{userId},#{buyNum})")
	void addShop(@Param("shopId")Integer shopId, @Param("userId")int userId, @Param("buyNum")Integer buyNum);

	
	ShopCar getShopCarByArticleIdAndUserId(@Param("shopId")Integer articleId, @Param("userId")int userId);

	@Update("update ec_shopcar set buynum = #{buyNum} where article_id = #{articleId} and user_id=#{userId}")
	void updateShop(ShopCar shopCar);

	@Delete("delete from ec_shopcar where  user_id = #{userId}")
	void removeShopCarByUserId(int userId);

	
	List<ShopCar> getShopCarByUserId(User user);



}