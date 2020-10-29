package com.zx.service;

import java.util.List;

import com.zx.common.pager.PageModel;
import com.zx.vo.Article;

public interface ArticleService {
	//根据商品id查询商品详细信息
	Article findArticleById(String id);
	//根据物品类型获取物品信息
	List<Article> findArticlesByCode(String typeCode, String keyword, PageModel pageModel);
}
