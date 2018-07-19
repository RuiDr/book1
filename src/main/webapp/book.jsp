<%@page import="com.qf.book1.pojo.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	List<Book> books = (List<Book>) request.getAttribute("books");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>图书信息</title>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userlist.css" />
</head>
<body>
	<h1 class="title">图书信息管理</h1>
	<table class="list">
		<th colspan="9">
			<form action="${pageContext.request.contextPath}/books/findByLike"
				method="post">
				<input type="text" placeholder="按书名名搜索" name="bookName"
					id="search-input" class="search-input" /> <input type="submit"
					id="search-button" value="搜索"></input>
			</form>
		</th>
		<tr class="title-tr">
			<td>Id</td>
			<td>分类</td>
			<td>图书书名</td>
			<td>图书作者</td>
			<td>图书简介</td>
			<td>图书图片地址</td>
			<td>操作</td>
		</tr>
		<%
		if(books!=null){
			for (Book b : books) {
		%>
		<tr class="value-tr">
			<td class="value-td"><%=b.getId()%></td>
			<td class="value-td"><%=b.getKind().getType()%></td>
			<td class="value-td"><%=b.getBookName()%></td>
			<td class="value-td"><%=b.getAuthor()%></td>
			<td class="value-td"><%=b.getIntro()%></td>
			 <td class="value-td"><img class="yu" alt="图片异常"
				src="${pageContext.request.contextPath}/<%=b.getAddress()%>" /></td> 
			<td class="value-botton">
				<button type="button" class="update">修改</button>
				<button type="button" class="delete">删除</button>
			</td>
		<tr>
			<%
				}
		}
			%>
	</table>
	<button type="button" class="add" id="add">增加</button>
	<div class="box">
		<form action="${pageContext.request.contextPath}/books/addBook" id="form" method="post"  enctype="multipart/form-data" >
			<button type="button" class="close" id="close">关闭</button>
			<div>
				<p id="id-p">ID</p>
				<input type="text" name="id" id="id" class="id" />
			</div>
			<div class="box-th">
				<p>分类</p>
				<input type="text" name="type" id="type" class="type" />
			</div>
			<div class="box-th">
				<p>图书书名</p>
				<input type="text" name="bookName" id="bookName" class="bookName" />
			</div>
			<div class="box-th">
				<p>图书作者</p>
				<input type="text" name="author" id="author"
					class="author" />
			</div>
			<div class="box-th">
				<p>图书简介</p>
				<input type="text" name="intro" id="intro"
					class="intro" />
			</div>
			<div class="box-th">
				<p>图片地址</p>
				<input type="file" name="filedata" id="filedata"
					class="filedata" />
			</div>
			<input type="button" class="submit" id="submit" value="提交" />
		</form>
	</div>
	<div class="box-shadow"></div>
</body>
<script type="text/javascript">
if("${msg.msg}"!=""&&"${msg.msg}"!=null){
	alert("${msg.msg}");
}
	//关闭窗口
	$("#close").click(function() {
		$(".box").css({
			"display" : "none"
		});

		$(".box-shadow").css({
			"display" : "none"
		});
		/* 清空残留数据 */
		$("#bookFilePath").remove();
		$("#bookPhotoPath").remove();
		$("#id").val("");
		$("#bookName").val("");
		$("#bookAuthor").val("");
		$("#bookSynopsis").val("");
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
			/*$("#form").attr({
			"action":"${pageContext.request.contextPath}/books/addBook"
		   })*/
			//添加事件
		$(".submit").click(function(){
			var formData=new FormData($("#form")[0]);
			//处理表单数据
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/books/addBook",
				data:formData,
				// 讲ajax里面处理数据的请求关闭
				processData:false,
				encType:"multipart/form-data",
				contentType:false,
				async:false,		
				success:function(data){
					alert(data.msg);
					window.location.reload();
				}
			});
		})	
 }
})
	/*更新按钮*/
	$(".update").click(function() {
		//获取对应的值 填入 表格中
		var id = $(this).parents("tr").find("td").eq(0).html();
		var type = $(this).parents("tr").find("td").eq(1).html();
		var bookName = $(this).parents("tr").find("td").eq(2).html();
		var author = $(this).parents("tr").find("td").eq(3).html();
		var intro = $(this).parents("tr").find("td").eq(4).html();
		var address = $(this).parents("tr").find("td").eq(5).html();
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
			$("#type").val(type);
			$("#bookName").val(bookName);
			$("#author").val(author);
			$("#intro").val(intro);
		/*$("#form").attr({
				"action":"${pageContext.request.contextPath}/books/updateBook"
			})*/
		}
		$(".submit").click(function(){
			//处理表单数据
			var formData=new FormData($("#form")[0]);
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/books/updateBook",
				data:formData,
				// 讲ajax里面处理数据的请求关闭
				processData:false,
				encType:"multipart/form-data",
				contentType:false,
				async:false,		
				success:function(data){
					alert(data.msg);
					window.location.reload();
				}
			});
		})
	})
	/*删除按钮*/
	$(".delete").click(function(){
		var id=$(this).parents("tr").find("td").eq(0).html();
		$.ajax({
			type:"get",
			url:"${pageContext.request.contextPath}/books/deleteBook?id="+id,
			async:false,
			success:function(data){
				alert(data.msg);
				window.location.reload();
			}
		});
	})
</script>
</html>