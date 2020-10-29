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
<title>注册页面</title>
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/taobao.css" rel="stylesheet" />
<script src="resources/jquery/jquery.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/bootstrap/js/bootstrapValidator.min.js"></script>

<script type="text/javascript">

	function checkLoginName(obj) {
		if (!obj) {
			document.getElementById("tipsLoginName").innerHTML = "用户名不能为空";
			return false;
		} else if (obj.length < 5) {
			document.getElementById("tipsLoginName").innerHTML = "用户名长度不能小于5";
			return false;
		}else{
			
			//通过js来发送异步请求
			//第一步：创建XMLHttpRequest
			var xhr = new XMLHttpRequest();
			// 第二步: 调用xhr对象的open方法来建立服务器之间的连接
			// 第一个参数：请求方式(GET|POST)
			// 第二个参数：请求的URL
			// 第三个参数：是异步还是同步(true、false)
			xhr.open("get", "verifyLoginName?user.loginName=" + obj , true);

			// 第三步：发送请求
			xhr.send();

			// 第四步：为xhr绑定onreadystatechange这个事件监听函数
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) { // 代表读取服务器响应数据结束

					if (xhr.status == 200) { // 代表服务器响应正常
						//xhr.responseText用于获取服务器返回的数据信息
						if (xhr.responseText) {
							document.getElementById("tipsLoginName").innerHTML = xhr.responseText;
							document.getElementById("loginName").value="";
							document.getElementById("loginName").value.focus();
							return false;
						}
					}
				}		
			}
			return true;
		}
		
	}
	
	function checkName(obj) {
		if (!obj) {
			document.getElementById("tipsName").innerHTML = "真实姓名不能为空";
			return false;
		}
		return true;
	}

	function checkPassword(obj) {
		if (!obj) {
			document.getElementById("tipsPassword").innerHTML = "密码不能为空";
			return false;
		} else if (obj.length < 5) {
			document.getElementById("tipsPassword").innerHTML = "密码长度不能小于5";
			return false;
		}
		return true;
	}

	function checkOkPassword(obj) {
		var passwordValue = document.getElementById("password").value;
		if (!obj) {
			document.getElementById("tipsOkPassword").innerHTML = "确认密码不能为空";
			return false;
		} else if (passwordValue != obj) {
			document.getElementById("tipsOkPassword").innerHTML = "两次密码不同";
			return false;
		}
		return true;
	}

	function checkPhone(obj) {
		if (!obj) {
			document.getElementById("tipsPhone").innerHTML = "手机号码不能为空";
			return false;
		} else if (!(/^1[3456789]\d{9}$/.test(obj))) {
			document.getElementById("tipsPhone").innerHTML = "手机号码不正确";
			return false;
		}
		return true;
	}

	function checkAddress(obj) {
		if (!obj) {
			document.getElementById("tipsAddress").innerHTML = "地址不能为空";
			return false;
		}
		return true;
	}

	function checkEmail(obj) {
		if (!obj) {
			document.getElementById("tipsEmail").innerHTML = "电子邮箱不能为空";
			return false;
		} else if (!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
				.test(obj))) {
			document.getElementById("tipsEmail").innerHTML = "电子邮箱不正确";
			return false;
		}else{

			var xhr = new XMLHttpRequest();

			xhr.open("get", "verifyEmail?user.email=" + obj , true);


			xhr.send();

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) { 

					if (xhr.status == 200) { 
						
						if (xhr.responseText) {
							document.getElementById("tipsEmail").innerHTML = xhr.responseText;
							document.getElementById("email").value="";
							document.getElementById("email").value.focus();
							return false;
						}
					}
				}		
			}
			return true;
		}
	}

	function checkYzm(obj) {
		if (!obj) {
			document.getElementById("tipsYzm").innerHTML = "  验证码不能为空";
			return false;
		} else {
			
			var xhr = new XMLHttpRequest();
			var loginNameValue = document.getElementById("loginName").value;
			xhr.open("get", "verifyYzm?user.yzm=" + obj , true);

			xhr.send();

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) { 
					if (xhr.status == 200) { 
						if (xhr.responseText) {
							document.getElementById("tipsYzm").innerHTML = xhr.responseText;
							var imgObj = document.getElementById("img");
							cutImg(imgObj);
							document.getElementById("yzm").value = "";
							document.getElementById("yzm").value.focus();
							return false;
						}
					}
				}		
			}
			return true;
		}
	}

	//聚焦清除提示信息
	function check(tipObj) {
		tipObj.innerHTML = "";
	}

	//对表单进行校验
	function checkForm() {
		var loginNameValue = document.getElementById("loginName").value;
		var nameValue = document.getElementById("name").value;
		var passwordValue = document.getElementById("password").value;
		var okPasswordValue = document.getElementById("okPassword").value;
		var phoneValue = document.getElementById("phone").value;
		var addressValue = document.getElementById("address").value;
		var emailValue = document.getElementById("email").value;
		var yzmValue = document.getElementById("yzm").value;
		return checkLoginName(loginNameValue) && checkName(nameValue)
				&& checkPassword(passwordValue)
				&& checkOkPassword(okPasswordValue) && checkPhone(phoneValue)
				&& checkAddress(addressValue) && checkEmail(emailValue)
				&& checkYzm(yzmValue);
		
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
					<li><a href="#"><span style='color: red;'></span></a></li>
					<li><a href="${ctx}/login">登录</a></li>
					<li><a href="${ctx}/register">免费注册</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!--  横幅下方的主体开始 -->
	<div class="container">
		<div class="row info-content">
			<form id="registerForm" class="form-horizontal" method="post"
				action="${ctx}/register" style="margin-top: 20px;"
				onSubmit="return checkForm()">
				<div class="form-group">
					<label class="col-sm-2 control-label">登录名称</label>
					<div class="col-sm-3">
						<input type="text" value="" id="loginName" name="user.loginName"
							class="form-control" placeholder="请输入您的登陆名称"
							onblur="checkLoginName(this.value)"
							onfocus="check(tipsLoginName)">
					</div>
					<div class="col-sm-3">
						<span style="color: red;" id="tipsLoginName"></span>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名</label>
					<div class="col-sm-3">
						<input type="text" id="name" value="" name="user.name"
							class="form-control" placeholder="请输入您的真实姓名"
							onblur="checkName(this.value)" onfocus="check(tipsName)">
					</div>
					<div class="col-sm-3">
						<span style="color: red;" id="tipsName"></span>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">登陆密码</label>
					<div class="col-sm-3">
						<input type="password" id="password" name="user.password"
							class="form-control" placeholder="请输入您的密码"
							onblur="checkPassword(this.value)" onfocus="check(tipsPassword)">
					</div>
					<div class="col-sm-3">
						<span style="color: red;" id="tipsPassword"></span>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-3">
						<input type="password" id="okPassword" name="user.okpassword"
							class="form-control" placeholder="请输入您的确认密码"
							onblur="checkOkPassword(this.value)"
							onfocus="check(tipsOkPassword)">
					</div>
					<div class="col-sm-3">
						<span style="color: red;" id="tipsOkPassword"></span>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-3">
						<label>男:</label><input name="user.sex" value="1" type="radio"
							checked="checked"> <label>女:</label><input
							name="user.sex" value="2" type="radio">
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">联系电话</label>
					<div class="col-sm-3">
						<input type="text" id="phone" value="" name="user.phone"
							class="form-control" placeholder="请输入您的电话"
							onblur="checkPhone(this.value)" onfocus="check(tipsPhone)">
					</div>
					<div class="col-sm-3">
						<span style="color: red;" id="tipsPhone"></span>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">地址</label>
					<div class="col-sm-3">
						<textarea id="address" name="user.address" rows="4" cols="30"
							class="form-control" placeholder="请输入您的地址信息"
							onblur="checkAddress(this.value)" onfocus="check(tipsAddress)"></textarea>
					</div>
					<div class="col-sm-3">
						<span style="color: red;" id="tipsAddress"></span>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">电子邮箱</label>
					<div class="col-sm-3">
						<input type="text" id="email" value="" name="user.email"
							class="form-control" placeholder="请输入您的邮箱"
							onblur="checkEmail(this.value)" onfocus="check(tipsEmail)">
					</div>
					<div class="col-sm-3">
						<span style="color: red;" id="tipsEmail"></span>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label">验证码</label>
					<div class="col-sm-2">
						<input type="text" id="yzm" value="" name="user.yzm"
							class="form-control" placeholder="请输入验证码" maxlength="4"
							onblur="checkYzm(this.value)" onfocus="check(tipsYzm)">
					</div>
					<div>
						<img src="yzm" style="width: 70px; height: 33px; cursor: pointer"
							onclick="cutImg(this)" id="img"/> <span style="color: red;" id="tipsYzm"></span>
					</div>
				</div>


				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-3">
						<button type="submit" id="btn_submit" class="btn btn-success">
							<span class="glyphicon glyphicon-user">注册 </span>
						</button>
						<button type="reset" class="btn btn-info">
							<span class="glyphicon glyphicon-edit">重置</span>
						</button>
					</div>
				</div>
			</form>
		</div>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

	</div>
	<!--  横幅下方的主体结束 -->
</body>
</html>