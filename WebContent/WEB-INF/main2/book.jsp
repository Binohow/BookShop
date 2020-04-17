<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<table align="center" width=90%>
  		<jsp:include page="mhead.jsp"></jsp:include>  		
  		<tr>
  		<td>
  			<table border="1" width=100%>        			
       			<tr><td rowspan=3><img width=100 height=100 src="<%=basePath%>${book.pic}"/></td><td colspan=2 align=center style="color:red">${book.bname}</td></tr>
       			<tr><td>商品价格</td><td>${book.price}</td></tr>
       			<tr><td>出版社</td><td>${book.press}</td></tr>
       			<tr><td height=300 colspan=3>${book.info}</td></tr>
       			<tr><td colspan=3 align=center><a href="<%=basePath %>user/ShopcarAddSvl?isbn=${book.isbn}">加入购物车</a> &nbsp; <a href="<%=basePath%>MainSvl">返回</a></td></tr>		
    		</table>
  		
  		</td>
  		</tr>
  	
  	</table>

</body>
</html>