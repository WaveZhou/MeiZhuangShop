<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib prefix="zx" uri="/pageTag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品首页</title>
<link href="${ctx}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="${ctx}/resources/css/taobao.css" rel="stylesheet" />
</head>
<script src="${ctx}/resources/jquery/jquery.js"></script>
<script src="${ctx}/resources/bootstrap/js/bootstrap.js"></script>
<body>
	<!-- 横幅导航条开始 -->
	<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">显示导航条</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${ctx}/index">疯狂购物</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${ctx}/index">首页</a></li>
					<li><a href="${ctx}/showShopCar.do">购物车</a></li>
					<li><a href="${ctx}/showOrder.do">我的订单</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"> <span style='color: red;'></span>
					</a></li>
					<c:choose>
					    <c:when test="${not empty session_user}">
					         <li><a href="#">欢迎【<font color ='red'>${session_user.name}</font>】登录</a></li> 
					         <li><a href="${ctx}/logout">退出</a></li>     
					    </c:when>
					    <c:otherwise>
					         <li><a href="${ctx}/login">登录</a></li>   
					    </c:otherwise>
					</c:choose>
					<li><a href="${ctx}/register">免费注册</a></li>
				</ul>
			</div>
			<!-- /.nav-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<!-- 横幅导航条结束 -->
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<!-- 侧边导航开始 -->
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<div class="list-group">		
				  <c:forEach items="${firstTypes}" var="type">
				  <c:choose>
				    <c:when test="${chooseTypeCode eq type.code}"> 
					<a href="${ctx}/index?typeCode=${type.code}"
						class="list-group-item active">${type.name}</a> 
					</c:when>
					<c:otherwise>
					<a href="${ctx}/index?typeCode=${type.code}"
						class="list-group-item">${type.name}</a>	
					</c:otherwise>	
					</c:choose>
					</c:forEach>
				</div>
			</div>
			<!--  侧边导航结束 -->
			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-9">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">显示导航条</button>
				</p>
				
				<div class="alert alert-info" role="alert">
					<c:forEach items="${secondTypes}" var="secondType">
					<c:choose>
					<c:when test="${chooseTypeCode2 eq secondType.code}">
						<a href="${ctx}/index?typeCode=${secondType.code}"
						class="btn btn-default active">${fn:replace(secondType.name,'-','')}</a>
					</c:when>
					<c:otherwise>
						<a href="${ctx}/index?typeCode=${secondType.code}"
						class="btn btn-default">${fn:replace(secondType.name,'-','')}</a>
					</c:otherwise>
					</c:choose>	
					</c:forEach> 
					<div>
					
						<form action="${ctx}/index" method="get">
							<!-- 如果当前选择了商品的类型，仅在该类型下面进行搜索 -->
							<input type="hidden" name="typeCode" value="${typeCode}" />
							<input name="keyword" value="${keyword}"/>
							<button type="submit">搜索</button>
						</form>
					</div>
				</div>

				<div class="row">
				<c:forEach items="${articles}" var="articles">
					<div class="col-xs-6 col-lg-4">
						<span class="thumbnail"> <a href="${ctx}/detail?id=${articles.id}">
								<img src="${ctx}/image/article/${articles.image}" alt="...">
								<p style="height: 20px; overflow: hidden;">${articles.title}</p>
						</a>
							<p>
								<span class="price">${articles.price}</span>&nbsp;
								<font color='red'>折后价:<fmt:formatNumber value="${articles.discountPrice}" pattern="0.00"></fmt:formatNumber></font>
							</p>
						</span>		
					</div>
					</c:forEach>
					<!--/.col-xs-6.col-lg-4-->

				</div>
				<!--/row-->


				<!-- p2是url前段部分pageNumber=之前 -->


				<!--  分页开始 -->

				<div class="row">
					<nav>
						<ul class="pagination">
							<div>
								<!--<a href="javascript:goPage(1)">首页</a> <a
									href='javascript:goPage(1)'>上一页</a> <a
									href='javascript:goPage(2)'>下一页</a> <a
									href='javascript:goPage(7)'>尾页</a> &nbsp; <span>第&nbsp;
									1&nbsp;页/共&nbsp; 7页 ，共 52 条数据 </span> -->
								<zx:pager submitUrl="index" pageModel="${pageModel}"></zx:pager>
							</div>
						</ul>
				</div>
				<!-- 分页结束 -->
			</div>
			<!--/.col-xs-12.col-sm-9-->
			<!-- 内容主体结束 -->
		</div>
		<!--/row-->
		<hr>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>
	</div>
</body>
</html>