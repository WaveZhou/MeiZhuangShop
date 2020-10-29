package com.zx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zx.common.pager.PageModel;
import com.zx.vo.Article;

/**
 * ArticleMapper 数据访问类
 * @author XXX
 * @email xxxx@qq.com
 * @date 2019-09-26 10:22:52
 * @version 1.0
 */
public interface ArticleMapper {

	//根据商品id获取商品信息
	@Select("SELECT * FROM ec_article WHERE id = #{id}")
	Article findArticleById(String id);

	//获取商品总记录数
	int findTotalNum(@Param("typeCode")String typeCode, @Param("keyword")String keyword);

	//商品信息分页查询
	List<Article> findArticlesByCode(@Param("typeCode")String typeCode, @Param("keyword")String keyword, @Param("pageModel")PageModel pageModel);

	//减少库存量
	@Update("UPDATE ec_article SET STORAGE = (STORAGE-#{orderNum}) WHERE id = #{articleId}")
	void minusStorage(@Param("orderNum")int orderNum, @Param("articleId")int articleId);



}