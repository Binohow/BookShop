<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<tr>
      	<td align=right>
      		<c:if test="${user==null}">
      	  	    <a href="<%=basePath%>LoginSvl2">登录</a>
      	  	 </c:if>
      	    <c:if test="${user!=null}">
      	           欢迎： ${user.uname} &nbsp;<a href="<%=basePath %>user/ShopcarSvl">购物车</a>
      	  		&nbsp;<a href="<%=basePath %>user/LogoutSvl">退出</a>     
      	  		<c:if test="${user.role == 1 }">
      	  		   <a href="<%=basePath %>back/BookAddSvl">后台</a>
      	  		</c:if> 
      	    </c:if>  
      	</td>
      </tr>