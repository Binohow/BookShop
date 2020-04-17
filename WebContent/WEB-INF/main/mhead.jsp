<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
   String basePath = request.getContextPath() + "/";   
%>
<style>
    .dropdown {
      position: relative;
      display: inline-block;
    }
    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        padding: 12px 16px;
        font-size: 1.2rem;
    }
    .dropdown:hover .dropdown-content {
      display: block;
    }
</style>
<nav class="navbar">
	<div class="navbar-center">
		<span class="nav-icon">
			<c:if test="${user==null}">
				<a href="<%=basePath%>LoginSvl2" class="login-btn">登录</a>
			</c:if>
			<c:if test="${user!=null}">
                <div class="dropdown">
                  <span>欢迎： ${user.uname} &nbsp;&nbsp;</span>
                  <div class="dropdown-content">
                      <p>余额：<fmt:formatNumber value="${user.account}" pattern="#.00" type="number"/></p>
                      <p style="margin: 10px 0">联系方式：${user.phonenum}</p>
                      <p>收货地址：${user.address}</p>
                  </div>
                </div>

				<a href="<%=basePath%>user/LogoutSvl" class="login-btn">退出</a>
				<c:if test="${user.role == 1 }">
					<a href="<%=basePath %>back/BookAddSvl">后台</a>
				</c:if>
			</c:if>
		</span>
		<img src="<%=basePath%>pic/logo.svg" alt="logo">
		<div class="cart-btn">
            <span class="nav-icon">
                <a href="<%=basePath%>user/ShopcarSvl"><i class="fa fa-cart-plus"></i></a>
<%--                <i class="fa fa-cart-plus"></i>--%>
            </span>
			<div class="cart-items">${sessionScope.count}</div>
		</div>
	</div>
</nav>
<%--	<tr>--%>
<%--      	<td align=right>--%>
<%--      		<c:if test="${user==null}">--%>
<%--      	  	    <a href="<%=basePath%>LoginSvl">登录</a>--%>
<%--      	  	 </c:if>--%>
<%--      	    <c:if test="${user!=null}">--%>
<%--			   欢迎： ${user.uname} &nbsp;<a href="#">购物车</a>--%>
<%--			&nbsp;	<a href="LogoutSvl">退出</a>--%>
<%--      	  		<c:if test="${user.role == 1 }">--%>
<%--      	  		   <a href="#">后台</a>--%>
<%--      	  		</c:if>--%>
<%--      	    </c:if>--%>
<%--      	</td>--%>
<%--      </tr>--%>