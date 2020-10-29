package com.zx.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zx.common.annotation.AutoWired;
import com.zx.common.util.ConnectionUtils;
import com.zx.mapper.ArticleTypeMapper;
import com.zx.service.ArticleTypeService;
import com.zx.vo.ArticleType;

public class ArticleTypeServiceImpl implements ArticleTypeService {

	@AutoWired(value=true)
   private ArticleTypeMapper articleTypeDao;
	
	
  
	// 获取所有的一级商品类型
	public List<ArticleType> findAllFirstArticleTypes() {
		
		
		List<ArticleType> types = articleTypeDao.findAllFirstArticleTypes();
		return types;
	}

	// 根据一级商品类型获取所有的二级商品类型
	public List<ArticleType> findAllSecondArticleTypes(String firstTypeCode) {
		
		List<ArticleType> types = articleTypeDao.findAllSecondArticleTypes(firstTypeCode+"%");
		return types;
	}

}
