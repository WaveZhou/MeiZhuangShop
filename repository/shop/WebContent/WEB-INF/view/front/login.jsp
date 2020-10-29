<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录页面</title>
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="resources/css/taobao.css" rel="stylesheet" />
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/jquery/jquery.js"></script>
<script type="text/javascript">
	function checkLoginName(obj) {
		if (!obj) {
			document.getElementById("tipsLoginName").innerHTML = "登录账户不能为空";
			return false;
		}
		return true;
	}
	
	function checkPassword(obj) {
		if (!obj) {
			document.getElementById("tipsPassword").innerHTML = "密码不能为空";
			return false;
		}
		return true;
	}
	
	
	 function formSubmit(){
     	//获取账号
     	var loginObj = document.getElementById("loginName");
     	//获取密码
     	var passObj = document.getElementById("passWord");
     	//获取验证码
     	var yzmObj = document.getElementById("yzm");
		//获取记住一周
		var remObj = document.getElementById("rem");
     	
     	var xhr = new XMLHttpRequest();

    	     xhr.open("post", "${pageContext.request.contextPath}/verifyLogin" , true);
    	     
    	     xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

    	     xhr.send("loginName="+loginObj.value+"&passWord="+passObj.value+"&yzm="+yzmObj.value+"&rem="+remObj.value);
 
    	     xhr.onreadystatechange = function(){
    	       if (xhr.readyState == 4){ 
    	         
    	          if (xhr.status == 200){
    				 if(xhr.responseText == "1"){
    					  document.getElementById("tipsLoginName").innerText = "账号不能为空！";
    				 }else if(xhr.responseText == "2"){
    					  document.getElementById("tipsPassword").innerText = "密码不能为空！";
    				 }else if(xhr.responseText == "3"){
   	            	  yzmObj.value = "";
	            	  document.getElementById("tipsYzm").innerText = "验证码不正确！";
	            	  var imgObj = document.getElementById("img");
	            	  cutImg(imgObj);
	           		 }else if(xhr.responseText == "4"){
    	            	  loginObj.value = "";
    	            	  document.getElementById("tipsLoginName").innerText = "账号不存在！";
    	             }else if(xhr.responseText == "5"){
    	            	  passObj.value = "";
    	            	  document.getElementById("tipsPassword").innerText = "密码不正确！";
    	             }else if(xhr.responseText == "6"){
    	            	  document.getElementById("tips").innerHTML = "账号没有激活，请先激活再登录";
    	             }else{
    	            	  //跳转至首页
    	            	  window.location = "${pageContext.request.contextPath}/index";
    	              }
    	          }
    	       }  	
    	 	}	
   		 }
	 
	 
	//聚焦清除提示信息
	function check(tipObj) {
		tipObj.innerHTML = "";
	}
	
	//验证码点击事件
	function cutImg(obj) {
		obj.src = "yzm?date=" + Math.random();
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

					<li><a href="${ctx}/login">登录</a></li>
					<li><a href="${ctx}/register">免费注册</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- /.navbar -->
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<!-- 入门-->
		<!-- 登录界面 -->
		<div class="page-header">
			<h3>
				<span style="color: red;" id="tips">${message}</span>
			</h3>
			<h1>用户登录</h1>
		</div>
			<form class="form-horizontal" method="post" >
			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" value="" placeholder="用户名/邮箱"
						type="text" id="loginName" name="loginName" 
						onfocus="check(tipsLoginName)"/>
				</div>
				<div class="col-sm-3">
					<span style="color: red;" id="tipsLoginName"></span>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-4">
					<input class="form-control" placeholder="密码" id="passWord"
						type="password" name="passWord" 
						onfocus="check(tipsPassword)"/>
				</div>
				<div class="col-sm-3">
					<span style="color: red;" id="tipsPassword"></span>
				</div>
			</div>					
			
			<div class="form-group">
					<div class="col-sm-3">
						<input type="text" id="yzm" value="" name="user.yzm"
							class="form-control" placeholder="请输入验证码" maxlength="4"
							onblur="checkYzm(this.value)" onfocus="check(tipsYzm)">
					</div>
					<div>
						<img src="yzm" style="width: 70px; height: 33px; cursor: pointer"
							id="img" onclick="cutImg(this)" /> <span style="color: red;" id="tipsYzm"></span>
					</div>	
						<div class="col-sm-4">
						<span style="color: red;"></span>
						</div>							
					</div>			
			
			<div class="form-group">
				<div class="col-sm-4">
					<input type="checkbox" value="1" id="rem" name="rem"/>记住一周
				</div>
			</div>				

			<div class="form-group">
				<div class="col-sm-4">
					<div class="btn-group btn-group-justified" role="group"
						aria-label="...">
						<div class="btn-group" role="group">
							<button type="button" id="loginBtn" class="btn btn-success" onclick="formSubmit()">
								<span class="glyphicon glyphicon-log-in"></span>&nbsp;登录
							</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-danger"
								onclick="window.location='${ctx}/register'">
								<span class="glyphicon glyphicon-edit"></span>注册
							</button>
						</div>
					</div>
				</div>
			</div>
			</form>
		<hr>

		<!-- 彈出框-->
		<div id="myModal" class="modal bs-example-modal-sm fade">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">登录提示</h4>
					</div>
					<div class="modal-body">
						<p id="tip"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button id="sure" type="button" class="btn btn-primary"
							data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>

		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

	</div>
	<!--/.container-->
	<!--  横幅下方的主体结束 -->
</body>
</html>