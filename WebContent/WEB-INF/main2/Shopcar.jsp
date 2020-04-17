<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
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
<form action="<%=basePath%>user/CheckoutSvl" method="post">
<table align="center" width=90%>
      <jsp:include page="mhead.jsp"></jsp:include>
      <tr>
       <c:if test="${bkSize!=-1}">
       <td>
      		<table border="1" width=100%> 
      			<tr><td>书名</td><td>作者</td><td>商品价格</td><td width="5%">数量</td><td>操作</td></tr>		       
       			<c:forEach items="${books}" var="bk">
       				<tr><td>${bk.bname}</td><td>${bk.author}</td><td>${bk.price}</td>
       				<td><input type="text"  name="bk${bk.isbn}" value="${bk.buynum}" /></td>
       				<td><a href="<%=basePath%>/user/ShopcarRemoveSvl?isbn=${bk.isbn}">移除</a></td></tr> 
       			</c:forEach>	   	
    		</table>
      	</td>
       </c:if>
      </tr>
      
      <tr>
      		<td align="center">
      		<c:if test="${bkSize>0}"> 
      		<input type="submit" value="结算"> &nbsp;
      		</c:if>
      		<a href="<%=basePath%>MainSvl">返回</a>
      		</td>	
      </tr>
    </table>
</form>
</body>
</html>