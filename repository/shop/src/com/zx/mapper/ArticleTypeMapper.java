package com.zx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zx.vo.ArticleType;

/**
 * ArticleTypeMapper 数据访问类
 * @author XXX
 * @email xxxx@qq.com
 * @date 2019-09-26 10:22:52
 * @version 1.0
 */
public interface ArticleTypeMapper {

	//获取一级商品类型
	@Select("SELECT * FROM ec_article_type WHERE LENGTH(CODE) = 4")
	List<ArticleType> findAllFirstArticleTypes();

	//根据一级商品类型的code获取二级商品类型
	@Select("SELECT * FROM ec_article_type WHERE LENGTH(CODE) = 8 and CODE like #{firstTypeCode}")
	List<ArticleType> findAllSecondArticleTypes(String firstTypeCode);



}