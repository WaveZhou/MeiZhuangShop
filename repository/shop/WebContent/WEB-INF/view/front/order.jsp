<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh_CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品详情</title>
    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
     <link rel="stylesheet" href="resources/bootstrap/style.css" />
    <link href="resources/css/taobao.css" rel="stylesheet" />
   <script src="resources/jquery/jquery.js"></script>
     
  </head>
  
  <body>

	

  
 <!-- 横幅导航条开始 -->
    <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" 
          	data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">显示导航条</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
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
                 <li><a href="#">
                     	欢迎【<font color ='red'>${session_user.name}</font>】登录                         
                     </a>
                 </li>
		               <li><a href="${ctx}/logout">退出</a></li>
                 
		      
           </ul>
        </div><!-- /.nav-collapse -->
        
         
      </div><!-- /.container -->
    </nav><!-- /.navbar -->
	
	<!--  横幅下方的主体开始 -->
    <div class="container">
				<div class="table-responsive">
				<table class="table table-hover table-bordered table-striped">
				<c:forEach items="${orders}" var="order">
					<thead>
						<tr>
							<th style="width: 15%">订单号</th>
							<th style="width: 40%">下单时间</th>
							<th style="width: 10%">发货时间</th>
							<th style="width: 8%">订单状态</th>
							<th style="width: 15%">订单总金额</th>
						</tr>
					</thead>
					<tbody>
					
						
					 
							<tr>
								<td>
									${order.orderCode}
								</td>
								<td>
									<fmt:formatDate value="${order.createDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
								</td>
								<td>
									<fmt:formatDate value="${order.sendDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
								</td>
								<td>
								    <c:choose>
								      <c:when test="${order.status eq '1'}">已发货</c:when>
								      <c:otherwise>未发货</c:otherwise>
								   </c:choose>
								</td>
								<td>
									
								</td>
							</tr>
						
					</tbody>

					<thead>
						<tr>
							<th style="width: 15%">图片</th>
							<th style="width: 50%">名称</th>
							<th style="width: 10%">价格</th>
							<th style="width: 8%">数量</th>
							<th style="width: 7%">操作</th>
						</tr>
					</thead>
					<tbody>
					
						
					 		<c:forEach items="${order.orderItem}" var="items">
							<tr>
								<td>
									<img alt="商品图片" src="${ctx}/image/article/${items.article.image}">
								</td>
								<td>
									${items.article.title} 
								</td>
								<td>
									<span ><fmt:formatNumber value="${items.article.discountPrice * items.orderNum}" pattern="0.00"></fmt:formatNumber></span>
					              	
								</td>
								<td>${items.orderNum}</td>								
								<td>
									<a href="${ctx}/detail?id=${items.article.id}">查看商品</a>									
								</td>
							</tr>
							</c:forEach>
					</tbody>

				</c:forEach>
				</table>
			</div>
				 
			   
				
				 
			   
	     

      <footer>
        <p>&copy; 版权所有，欢迎借鉴</p>
      </footer>

    </div><!--/.container-->
    <!--  横幅下方的主体结束 -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   
    <script src="resources/bootstrap/js/bootstrap.js"></script>

    <script src="resources/js/taobao.js"></script>
  </body>
</html>