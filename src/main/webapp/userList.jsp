<%@page import="com.qf.book1.pojo.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%
	List<User> users = (List<User>) request.getAttribute("users");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>用户信息管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/userlist.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<h1 class="title">用户信息</h1>
	<table class="list">
		<th colspan="6">
			<form action="${pageContext.request.contextPath}/users/findByLike"
				method="post">
				<input type="text" placeholder="按用户名搜索" name="userName"
					id="search-input" class="search-input" /> <input type="submit"
					id="search-button" value="搜索"></input>
			</form>
		</th>
		<tr class="title-tr">
			<td>Id</td>
			<td>userName</td>
			<td class="pswd">passWord</td>
			<td>Phone</td>
			<td>Email</td>
			<td>修改</td>
		</tr>
		<%
			for (User u : users) {
		%>
		<tr class="value-tr">
			<td class="value-td"><%=u.getId()%></td>
			<td class="value-td"><%=u.getUserName()%></td>
			<td class="value-td pswd"><%=u.getUserPassword()%></td>
			<td class="value-td"><%=u.getPhone()%></td>
			<td class="value-td"><%=u.getEmail()%></td>
			<td class="value-botton">
				<button type="button" class="update">修改</button>
				<button type="button" class="delete">删除</button>
			</td>
		<tr>
			<%
				}
			%>
	</table>
	<button type="button" class="add" id="add">增加</button>
	<div class="box">
		<form action="" id="form" method="post">
			<button type="button" class="close" id="close">关闭</button>
			<div>
				<p id="id-p">ID</p>
				<input type="text" name="id" id="id" class="id" />
			</div>
			<div class="box-th" readonly="readonly">
				<p>用户</p>
				<input type="text" name="userName" id="userName" class="userName" />
			</div>
			<div class="box-th pswd" style="display:none">
				<p>密码</p>
				<input type="Password" name="userPassword" id="userPassword" class="userPassword" />
			</div>
			<div class="box-th" readonly="readonly">
				<p>手机</p>
				<input type="text" name="phone" id="phone" class="phone" />
			</div>
			<div class="box-th" readonly="readonly">
				<p>邮箱</p>
				<input type="text" name="email" id="email" class="email" />
			</div>
			<input type="submit" class="submit" value="提交" />
		</form>
	</div>
	<div class="box-shadow"></div>
</body>
<script type="text/javascript">
	$("#close").click(function() {
		$(".box").css({
			"display" : "none"
		});

		$(".box-shadow").css({
			"display" : "none"
		});

		/* 清空残留数据 */
		$("#id").val("");
		$("#userName").val("");
		$("#userPassWord").val("");
		$("#phone").val("");
		$("#email").val("");

		$(".submit").removeClass("add_submit").removeClass("update_submit");
	});

	/*添加按钮*/
	$("#add").click(function() {
		if ($(".box").css("display") == "none") {
			/*先让添加框显示*/
			$(".box").css({
				"display" : "block"
			});

			$(".submit").attr({
				"value" : "添加"
			});

			$(".submit").addClass("add_submit");

			$(".box-shadow").css({
				"display" : "block"
			});
			//添加
			$(".submit").click(function(){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/users/addUser",				
					data:$("#form").serialize(),
					async:false,
					success:function(data){
						alert(data.msg);
					}
				});
			})
			
		}

	})
	//点击修改按钮
	$(".update").click(function() {
		var id = $(this).parents("tr").find("td").eq(0).html();
		var userName = $(this).parents("tr").find("td").eq(1).html();
		var psw = $(this).parents("tr").find("td").eq(2).html();
		var phone = $(this).parents("tr").find("td").eq(3).html();
		var email = $(this).parents("tr").find("td").eq(4).html();
		if ($(".box").css("display") == 'none') {

			$(".box").css({
				"display" : "block"
			});

			$(".submit").attr({
				"value" : "修改"
			});

			$(".submit").addClass("update_submit");

			/* 阴影 */
			$(".box-shadow").css({
				"display" : "block"
			});

			//先给前端修改页面显示：修改数据
			$("#id").val(id);
			$("#userName").val(userName);
			$("#userPassWord").val(psw);
			$("#phone").val(phone);
			$("#email").val(email);
			
			//修改
			$(".submit").click(function(){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/users/updateUser",
				data:$("#form").serialize(),
				async:false,
				success:function(data){
					alert(data.msg);
				}
			});
		})
		}
	})

	//删除
	
		$(".delete").click(function(){
			var uId=$(this).parents("tr").find("td").eq(0).html();	
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/users/deleteUser?uId="+uId,
				async:false,
				success:function(data){
					alert(data.msg);
					window.location.reload();
				}
			});
		})
	
</script>
</html>