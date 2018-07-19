<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
  
  </head>
  
  <body>
  <form action="" method="post" id ="form" enctype="multipart/form-data">
			<input type="file" name="filedata" id="filedata"  />
			<input type="button" class ="submit" value="上传"/>
		</form>
  </body>
  <script type="text/javascript">
  $(".submit").click(function(){
	  var formData=new FormData(document.getElementById("form"));
	    $.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/users/upUser",
		data:formData,
		contentType:false,
		processData:false,
		encType:"multipart/form-data",
		async:false,
		success:function(data){
			alert(data.msg);
			window.location.reload();
		}
	});
})		
  </script>
</html>