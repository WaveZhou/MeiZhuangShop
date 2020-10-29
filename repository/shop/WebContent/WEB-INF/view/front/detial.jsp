<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品详情</title>
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="resources/css/taobao.css" rel="stylesheet" />
<script src="resources/jquery/jquery.js"></script>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/js/taobao.js"></script>

<script type="text/javascript">
	function add(storage){
		var number = document.getElementById("number");
		var value = parseInt(number.value);
		number.value = value+1;
		if(number.value > storage){
			number.value = storage;
			alert("购买数量已超过库存数，请核实");
			return false;
		}
		return true;
	}
	function subtract(){
		var number = document.getElementById("number");
		var value = parseInt(number.value);
		if(value>1){
			number.value = value-1;
		}		
	}
	
	function check(storage){
		var number = document.getElementById("number");
		var value = number.value;
		if(!(/(^[1-9]\d*$)/.test(value))){
			number.value = 1;
			return false;
		}
		if(number.value > storage){
			number.value = storage;
			alert("购买数量已超过库存数，请核实");
			return false;
		}
		return true;
	}
	
	function addCar(storage){
		return add(storage)&&check(storage);
	}
</script>
</head>
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
					<li><a href="#"><span style='color: red;'></span></a></li>
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
	</nav>
	<!--  横幅下方的主体开始 -->
	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right">

			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-12">
				<div class="col-xs-12 col-sm-6">
					<img alt="商品的图片" src="${ctx}/image/article/${article.image}">
				</div>
				<div class="col-xs-12 col-sm-6">
					<p>${article.title}</p>
					<p>
						<span class="price">${article.price}</span>&nbsp;
						<font color='red'>折后价:<fmt:formatNumber value="${article.discountPrice}" pattern="0.00"></fmt:formatNumber></font>
					</p>
					<p>
						库存量：<span id="storage">${article.storage}</span>
					</p>
					
					<form method="get" action="${ctx}/addToShopCar.do">
						<input type="hidden" name="id" value="${article.id}" /> <span
							style="font-weight: bold; font-size: 18px; cursor: pointer; margin-left: 10px; margin-right: 10px;"
							onclick="subtract()">-</span>
						<input id="number" name="buyNum" value="1" style="width: 50px;" onblur="check(${article.storage})"/>
						<span
							style="font-weight: bold; font-size: 18px; cursor: pointer; margin-left: 10px; margin-right: 10px;"
							onclick="add(${article.storage})">+</span>
						<button>
							<span class="glyphicon glyphicon-shopping-cart"
								style="color: red;" onsubmit="return addCar()"></span>加入购物车
						</button>
					</form>
					
				</div>
				<div class="col-xs-12">
					<fieldset>
						<legend>介绍</legend>
						${article.description}
					</fieldset>
				</div>
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
	<!--/.container-->
	<!--  横幅下方的主体结束 -->

</body>
</html>