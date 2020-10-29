package com.zx.service;

import java.util.List;

import com.zx.vo.ArticleType;

public interface ArticleTypeService {

	//获取所有的一级商品类型
	List<ArticleType> findAllFirstArticleTypes();
	//根据一级商品类型获取所有的二级商品类型
	List<ArticleType> findAllSecondArticleTypes(String firstTypeCode);

}
