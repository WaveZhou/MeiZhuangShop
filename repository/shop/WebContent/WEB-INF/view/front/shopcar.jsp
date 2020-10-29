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
<title>购物车</title>
<!-- Bootstrap core CSS -->
<link href="${ctx}/resources/bootstrap/css/bootstrap.css"
	rel="stylesheet" />

<link href="${ctx}/resources/css/taobao.css" rel="stylesheet" />
<script src="${ctx}/resources/jquery/jquery-1.11.0.min.js"></script>
<script src="${ctx}/resources/jquery/jquery-migrate-1.2.1.min.js"></script>
<script src="${ctx}/resources/bootstrap/js/bootstrap.js"></script>
<script src="${ctx}/resources/js/taobao.js"></script>

<script type="text/javascript">
	function minuFun(articleId) {

		var obj = document.getElementById(articleId);

		//获取输入款中的值
		var num = obj.value;

		if (num > 1) {
			//重新给输入框中的value赋值
			obj.value = num - 1;

			//发送请求更新购物车中购买的数量 
			window.location = "${ctx}/updateShopCar.do?articleId=" + articleId
					+ "&buyNum=" + obj.value;
			
			/* var xhr = new XMLHttpRequest();

	    	     xhr.open("post", "${ctx}/updateShopCar.do?articleId=" + articleId + "&buyNum=" + obj.value, true);
	    	     
	    	     xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	    	     xhr.send(); */

		}
	}

	
	function addFun(articleId,storage) {
		//获取输入框对应的dom对象
		var obj = document.getElementById(articleId);

		//获取输入款中的值
		var num = parseInt(obj.value);//parseInt:将字符窜转成整数

		//获取库存数
		var kucun = storage;
		//重新给输入框中的value赋值
		obj.value = (num + 1) > kucun ? kucun : num + 1;

		//发送请求更新购物车中购买的数量 
		window.location = "${ctx}/updateShopCar.do?articleId=" + articleId
				+ "&buyNum=" + obj.value;
		
		/* var xhr = new XMLHttpRequest();

    	     xhr.open("post", "${ctx}/updateShopCar.do?articleId=" + articleId + "&buyNum=" + obj.value, true);
    	     
    	     xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

    	     xhr.send(); */
		

	}

	
	//为购买的数量对应的输入框  绑定失去焦点事件
	//必须带一个库存上来，因为直接用EL表达式来取的话，不知道取到的是哪一个商品的库存
	function validNum(obj, articleId,storage) {
		//用户输入的数字 必须为 大于等于1的正整数，并且不能大于库存
		//1、判断用户输入的是否是数字   isNaN: is Not a Number
		if (isNaN(obj.value) || obj.value < 1) {
			//用户输入的不是数字或者小于1的数字 都不合法,则 使用默认购买数量
			obj.value = 1;
		} else if (obj.value > storage) {
			obj.value = storage;
			alert("购买数量已超过库存数，请核实");
		} else {
			//防止用户用户输入小数    假设用户输入3 == 》3  用户输入  3.5 == 》4   对用户输入的值 进行向上取整
			obj.value = Math.ceil(obj.value);
			//发送请求更新购物车中购买的数量 
			window.location = "${ctx}/updateShopCar.do?articleId=" + articleId
					+ "&buyNum=" + obj.value;
			
			/* var xhr = new XMLHttpRequest();

	    	     xhr.open("post", "${ctx}/updateShopCar.do?articleId=" + articleId+ "&buyNum=" + obj.value , true);
	    	     
	    	     xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	    	     xhr.send(); */		
		}
	}
	
	
	 //实现全选效果
    function checkAll(checkObj){

           //获取所有的子checkbox
		   var boxs = document.getElementsByName("box");
		   //将订单相关信息存放在 隐藏输入框中
		   var orderInfo = document.getElementById("orderInfo");
		   orderInfo.value = "";
		   
           //遍历所有的子 checkbox
		   for(var i=0;i< boxs.length;i++){
		       
		       //获取页面中元素对应的dom对象
			   var obj = boxs[i];
			   obj.checked = checkObj.checked;
			   
		       if(checkObj.checked){
					//将隐藏输入框中之前的信息 和  现在用户选中的新的商品信息 进行拼接
					orderInfo.value = orderInfo.value +"#"+obj.value;
			   }	       
		   }
           
		   var priceCount = 0.00;
		   var orders = orderInfo.value.split("#");
		   for(var i=1;i<orders.length;i++){
			   var items = orders[i].split("_");
			   //价格在数组的第三位,把得到的价格进行累加
			   priceCount = parseFloat(items[2])+priceCount
		   }
   		   //设置提交订单的按钮的文本值     .toFixed(2):四舍五入保留两位小数
   		   document.getElementById("subSpan").innerText=priceCount.toFixed(2)+"元";
           
	   }
	 
	 
  	//当所有的子checkbox都选中的时候，全选应该选中
    function clickFn(info,box){
	        
		   	//获取所有的子checkbox
		  	var boxs = document.getElementsByName("box");
         	//定义变量用于记录选中的个数
			var count = 0;
			
			var orderInfo = document.getElementById("orderInfo");
			for(var i=0;i< boxs.length;i++){
			   
			   if(boxs[i].checked){
			      count++;
			   }
			}

           //获取全选对应的dom对象
		   var checkAllObj = document.getElementById("checkAll");
		   //如果选中的个数等于总的checkbox的个数则全选选中，否则不选中
		   checkAllObj.checked = (count == boxs.length);
		   
		   
		   //将订单相关信息存放在 隐藏输入框中
		   //将orderInfo拼接好，用作传递到后台，进行订单的提交
		   if(orderInfo.value.indexOf(info) == -1 && box.checked == true){
			 //将隐藏输入框中之前的信息 和  现在用户选中的新的商品信息 进行拼接
				orderInfo.value = orderInfo.value +"#"+info;
		   }else if(orderInfo.value.indexOf(info) != -1 && box.checked == false){
			   orderInfo.value = orderInfo.value.replace("#"+info,"")
		   }
		   
		   
		   //计算提交订单的价格总计
		   var priceCount = 0.00;
		   var orders = orderInfo.value.split("#");
		   for(var i=1;i<orders.length;i++){
			   var items = orders[i].split("_");
			   //价格在数组的第三位,把得到的价格进行累加
			   priceCount = parseFloat(items[2])+priceCount
		   }
		   
   		   //设置提交订单的按钮的文本值
   		   document.getElementById("subSpan").innerText=priceCount.toFixed(2)+"元";
	   
  	}
  	
  	
  	
    //提交订单
    function submitOrder(){
   	 //获取用户选中的checkbox，如果一个都没有选中，则给用户提示
   	 //获取所有的子 checkbox 
   	 var boxes = document.getElementsByName("box");

   	 var num = 0;
   	 for(var i=0;i<boxes.length;i++){
   		 if(boxes[i].checked){
   			 num++;
   		 }
   	 }
   	 
   	 if(num == 0){
   		 alert("请选择需要购买的商品信息！");
   	 }else{
   		
   		 //提交表单
   		 document.getElementById("form").submit();
   		 
   	 	  }	 
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
					<li><a href="#"> <span style='color: red;'></span>
					</a></li>
					<c:choose>
						<c:when test="${not empty session_user}">
							<li><a href="#">欢迎【<font color='red'>${session_user.name}</font>】登录
							</a></li>
							<li><a href="${ctx}/logout">退出</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${ctx}/login">登录</a></li>
						</c:otherwise>
					</c:choose>
					<li><a href="${ctx}/register">免费注册</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- /.navbar -->
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">

			<!-- 内容主体开始 -->
			<div class="col-xs-12 col-sm-12">
			<c:choose>
             <c:when test="${empty shopCars}">
                 <center><h2><font color="red">您的购物车中暂无商品信息</font></h2></center>
             </c:when>
             
             <c:otherwise>
				<div>当前位置：我的购物车&nbsp;<a href="${ctx}/deleteAllShopCar.do">清空购物车</a></div>
				<div class="table-responsive">
					<table class="table table-hover table-bordered table-striped">
						<thead>
							<tr>
								<th><input type="checkbox" id="checkAll" onclick="checkAll(this)"></th>
								<th style="width: 15%">图片</th>
								<th style="width: 40%">名称</th>
								<th style="width: 20%">价格</th>
								<th style="width: 10%">数量</th>
								<th style="width: 8%">小计</th>
								<th style="width: 15%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${shopCars}" var="shopCar">
								<tr>
									<td style="vertical-align: middle;"><input type="checkbox" name="box"
										value="${shopCar.article.id}_${shopCar.buyNum}_${shopCar.article.discountPrice* shopCar.buyNum}" 
										onclick="clickFn(this.value,this)" /> 
										<span id="total_1" style="display: none;">${shopCar.article.price}</span></td>
										
									<td><img alt="商品图片"
										src="${ctx}/image/article/${shopCar.article.image}"></td>
									<td>${shopCar.article.title}</td>
									<td>价格:<span
										class="${shopCar.article.discount!=0.0 ? 'price':''}">${shopCar.article.price}</span>

										<c:if test="${shopCar.article.discount != 0.0}">
								        折后价:<span class="discountPrice"><fmt:formatNumber
													value="${shopCar.article.discount * shopCar.article.price}"
													pattern="0.00"></fmt:formatNumber></span>
										</c:if>
									</td>
									<td><span style="cursor: pointer"
										class="glyphicon glyphicon-minus"
										onclick="minuFun('${shopCar.article.id}')"></span> <input
										id="${shopCar.article.id}" value="${shopCar.buyNum}"
										style="width: 40px; text-align: center;"
										onblur="validNum(this,${shopCar.article.id},${shopCar.article.storage})" /> 
										<span style="cursor: pointer" class="glyphicon glyphicon-plus"
										onclick="addFun(${shopCar.article.id},${shopCar.article.storage})"></span></td>
									<td><fmt:formatNumber
											value="${shopCar.article.discountPrice* shopCar.buyNum}"
											pattern="0.00"></fmt:formatNumber></td>
									<td><a
										href="${ctx}/deleteShopCar.do?articleId=${shopCar.article.id}">删除</a>&nbsp;<a>收藏</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</c:otherwise>
				</c:choose>
			</div>
			<!-- 内容主体结束 -->
		</div>
		<!--/row-->

		<c:if test="${not empty shopCars}">
		<form action="${pageContext.request.contextPath}/saveOrder.do" method="post" id="form">
		<!-- 通过隐藏输入框 将订单相关信息传递至后台 -->
        <input type="text" name="orderInfo" id="orderInfo"/>
		<div align="right">
			总金额:<span id="totalMoney" style="color: red;"><fmt:formatNumber
					value="${totalPrice}" pattern="0.00"></fmt:formatNumber>元</span>&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="commitOrder" class="btn btn-danger" type="button" onclick="submitOrder()">
				提交订单 <span class="badge" id="subSpan"></span>
			</button>
		</div>
		</form>
		</c:if>
		
		<hr>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>
	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->
</body>
</html>