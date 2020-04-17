<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
<script type="text/javascript">
     function tijiao(){
    	 var isbn = $('#isbn').val();
    	 var bname = $('#bname').val();
    	 var spIsbn =  document.getElementById("isbnAlert");
    	 var spBname = document.getElementById("nameAlert");
    	 if(isbn == ""){    		 
    		 spIsbn.innerHTML = "isbn不能为空";
    		 return false;
    	 }else{
    		 spIsbn.innerHTML = "";
    	 }
    	 if(bname == ""){	
    		 spBname.innerHTML = "书名不能为空";
    		 return false;
    	 }else{
    		 spBname.innerHTML = "";
    	 }
    	 var myform = document.getElementById("myform");
    	 myform.submit();
     }
</script>
</head>
<body>
<table align="center" width=90%>
     <jsp:include page="bhead.jsp"></jsp:include>
     
      <tr><td align="left"><h2>新书上架</h2></td></tr>
      <tr>
      	<td>
      		<form id="myform" action="<%=basePath%>back/BookAddSvl" method="post" enctype="multipart/form-data">
      		<table border="0" width=60% align="center">  		
      		
      			<tr><td>书号ISBN</td><td><input type="text" id="isbn" name="isbn"/><span style="color:red;font-size:8px;" id="isbnAlert"></span></td></tr>
       			<tr><td>书名</td><td><input type="text" id="bname" name="bname"/><span style="color:red;font-size:8px;" id="nameAlert"></span></td></tr>
       			<tr><td>作者</td><td><input type="text" name="author"/></td></tr>
       			<tr><td>出版社</td><td><input type="text" name="press"/></td></tr>
       			<tr><td>出版日期</td><td><input class="easyui-datebox" name="pubdate"/></td></tr>
       			<tr><td>价格</td><td><input class="easyui-numberbox" precision="2" value="0" name="price"/></td></tr>
       			<tr><td>图片上传</td><td><input type="file" name="pic"/></td></tr>
       			<tr><td colspan=2 align=center><input type=button value=提交 onclick="tijiao()" /></td></tr>
       			<tr><td colspan=2 align=center> <span style="color:red;font-size:8px;">${msg}</span></td></tr>
    		</table>
    	</form>
      	</td>
      </tr>
    </table>
</body>
</html>