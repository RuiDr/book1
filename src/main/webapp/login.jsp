<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>图书管理系统</title>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fun.base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
</head>

<body>
	<div class="login">
		<div class="login-box">
			<div class="logo png"></div>
			<div class="login-form">
				<form action="${pageContext.request.contextPath}/users/login"
					method="post" class="form">
					<div class="name">
						<label>用户名</label><input type="text" class="text"
							placeholder="用户名" name="userName" tabindex="1"
							required="required"></br>
					</div>

					<div class="pwd">
						<label>密&nbsp;&nbsp;码</label><input type="password" class="text"
							placeholder="密码" name="userPassword" tabindex="2" required="required"></br>
						<input class="submit" type="submit" value="登陆" /> <input
							class="reset" type="reset" value="重置" /> </br>
							<font color="red">${msg}</font></br>
					</div>
				</form>
			</div>
		</div>
		<div class="air-balloon ab-1 png"></div>
		<div class="air-balloon ab-2 png"></div>
		<div class="footer"></div>
	</div>
</body>
</html>