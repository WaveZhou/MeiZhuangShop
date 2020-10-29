package com.zx.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zx.common.annotation.AutoWired;
import com.zx.common.pager.PageModel;
import com.zx.common.util.ConnectionUtils;
import com.zx.mapper.ArticleMapper;
import com.zx.service.ArticleService;
import com.zx.vo.Article;

public class ArticleServiceImpl implements ArticleService {

	@AutoWired(value=true)
	private ArticleMapper  articleDao;
	
	
	
	//根据商品id查询商品详细信息
	public Article findArticleById(String id) {
		
		
		Article article = articleDao.findArticleById(id);
		
		
		
		return article;
	}
	
	
 
	//根据物品类型获取物品信息
	public List<Article> findArticlesByCode(String typeCode,String keyword,PageModel pageModel) {
		
		
		//获取总记录数
		int totalNum = articleDao.findTotalNum(typeCode==null?"%%":typeCode+"%",keyword==null?"%%":"%"+keyword+"%");
		pageModel.setTotalNum(totalNum);
		
		//商品信息分页查询
		List<Article> articles = articleDao.findArticlesByCode(typeCode==null?"%%":typeCode+"%",keyword==null?"%%":"%"+keyword+"%",pageModel);
		
		
		return articles;
	}

}
