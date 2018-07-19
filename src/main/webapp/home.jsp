<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
		<meta charset="UTF-8">
		<title>主页</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userlist.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/home.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css"/>		
		<script src="${pageContext.request.contextPath }/js/jquery-2.1.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath }/bootstrap/js/jQuery.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script> 
		 $(document).ready(function(){ 
		 $('.carousel').carousel({interval:2000});//每隔2秒自动轮播 
		 }); 
		 </script> 
		
	</head>
	<body>
		<div class="title">
			<div class="sign"><img src="${pageContext.request.contextPath}/img/home4.jpg"></div>
			<div class="welcomeDiv">重庆理工大学图书馆</div>
			<div class="search">
				<form action="${pageContext.request.contextPath}/books/findByLike"
				method="post">
				<input type="text" placeholder="按书名名搜索" name="bookName"
					id="search_input" class="search_input" /> <input type="submit"
					id="search_button" class="search_btn" value="搜索"></input>
			</form>
			</div>
			
		</div>		
		<div id="myCarousel" class="carousel slide">		   
		    <!-- 轮播（Carousel）项目 -->
		    <div class="carousel-inner">
		        <div class="item active">
		            <img src="${pageContext.request.contextPath }/img/home1.jpg" class="home" alt="First slide">
		        </div>
		        <div class="item">
		            <img src="${pageContext.request.contextPath }/img/home2.jpg"  class="home" alt="Second slide">
		        </div>
		       
		         <div class="item">
		            <img src="${pageContext.request.contextPath }/img/home3.jpg"  class="home" alt="Third slide">
		        </div>
		    </div>
		    <!-- 轮播（Carousel）导航 -->
		        <a class="left carousel-control" href="#myCarousel"  data-slide="prev">
		            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		            <span class="sr-only">Previous</span>
		        </a>
		        <a class="right carousel-control" href="#myCarousel"data-slide="next">
		            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		            <span class="sr-only">Next</span>
		        </a>		   		    
		</div> 
		
    <div></div>
    <div class="footDiv">
    <ul>
        <li>重庆市巴南区红光大道69号 邮编：400054</li>
        <li>联系电话：62563425&nbsp;公开信箱：<a href="mailto:lib@cqut.edu.cn">lib@cqut.edu.cn</a></li>
    </ul>
    </div>

	</body>
	<script >

</script>
</html>