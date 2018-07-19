<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>借书</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/borrow.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
</head>
<body>
	<h1 class="title">借书管理</h1>
	<div class="box">
		<form action="" id="form" method="post">
			<div class="box-th">
				<p>书本名字</p>
				<input type="text" name="bookName" placeholder="清输入书本名字" />
			</div>
			<div class="box-th">
				<p>用户</p>
				<input type="text" name="userName" placeholder="请输入用户" />
			</div>
			<div class="box-th">
				<p>借书日期</p>
				<input type="text" name="borrowDate" id="borrowDate" value=""/>
			</div>
			<div class="box-th">
				<p>还书日期</p>
				<input type="text" name="returnDate" id="returnDate" value="" />
			</div>
			<input type="submit" class="submit" value="提交订单" />
		</form>
	</div>
</body>
<script type="text/javascript">
	/*日期修改*/
	var date;
	var rDate;
	var today = new Date(); 
    var reDate=new Date(new Date().setDate(new Date().getDate()+30)); 
    date = (today.getFullYear()) +"-" + (today.getMonth() + 1 ) + "-" + today.getDate() ;
    rDate = (reDate.getFullYear()) +"-" + (reDate.getMonth() + 1 ) + "-" + reDate.getDate() ;
    document.getElementById("borrowDate").value=date;

    document.getElementById("returnDate").value=rDate;
	/*借书*/
	$(".submit").click(function(){
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/borrows/borrow",
			data:$("#form").serialize(),
			async:false,
			success:function(data){
				alert(data.msg);
			}
		});
	})
</script>
</html>