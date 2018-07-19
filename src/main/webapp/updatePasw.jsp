<%@page import="com.qf.book1.pojo.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%
	User u = (User) request.getSession().getAttribute("u");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>用户信息管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/userlist.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/updatePasw.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<h1 class="title">修改密码</h1>
	
		<form action="" class ="form_Class" id="form" method="post">
			<div>
				<p id="id-p">ID</p>
				<input type="text" name="id" id="id" class="id" />
			</div>
			<div class="box-th">
				<p >旧密码</p>
				<input type="Password" name="oldPassWord" id="oldPassWord" class="oldPassWord" value=""/><div  class ="p_Class" id="p1" style="color: red"></div>
			</div>
			<div class="box-th" >
				<p >输入新密码</p>
				<input type="Password" name="oldPassWord1" id="oldPassWord1" class="oldPassWord1" value="" /><div class ="p_Class" id="p2" style="color: red"></div>
			</div>
			<div class="box-th">
				<p >再次确认新密码</p>
				<input type="Password" name="newPassWord" id="newPassWord" class="newPassWord" value=""/><div class ="p_Class" id="p3" style="color: red"></div>
			</div>
			<br>
			<br>
			<input type="submit" class="submit submit_Class" value="提交" />
		</form>
</body>
<script >
// 修改密码
	var op=document.getElementById("oldPassWord").value;
	var op1=document.getElementById("oldPassWord1").value;;
	var np=document.getElementById("newPassWord").value;;
	
		$("#oldPassWord").blur(function(){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/users/updateOrgPassWord",				
				data:$("#form").serialize(),
				async:false,
				success:function(data){
					//alert(data.msg);
					if(data.msg=="原密码正确"){
						document.getElementById("p1").innerHTML="√";  
					}
				}
			});	
      })
     $("#newPassWord").blur(function(){
    	 if(op1==np){
    		 document.getElementById("p2").innerHTML="√";  
    		 document.getElementById("p3").innerHTML="√";  

    	 }else{
    		 document.getElementById("p2").innerHTML="×";  
    		 document.getElementById("p3").innerHTML="×";   
    		 
    	 }
     })
     
	$(".submit").click(function(){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/users/updatePassWord",				
					data:$("#form").serialize(),
					async:false,
					success:function(data){
						alert(data.msg);
						if(data.msg=="修改密码成功"){
							  top.location.href="${pageContext.request.contextPath}/login.jsp";  
						}
					}
				});
			})
</script>
</html>