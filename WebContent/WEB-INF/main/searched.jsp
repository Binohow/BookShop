<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath() + "/";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="Shortcut Icon" href="<%=basePath%>pic/logo.svg" type="image/x-icon" />
    <title>书城主页</title>
    <!-- fontawesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- custom css -->
    <link rel="stylesheet" href="<%=basePath%>css/style1.css">
    <link rel="stylesheet" href="<%=basePath%>css/bookstyle.css">
    <title>查询结果</title>
</head>
<body>
<jsp:include page="mhead.jsp"></jsp:include>
		<c:if test="${book == null}">
            <p align=center>没有查询到该图书   <a href="<%=path%>MainSvl">返回</a></p>
		</c:if>
		<c:if test="${book != null}">
            <c:forEach items="${book}" var="book">
                <div id="container">
                    <div><img id="img" src="<%=basePath%>${book.pic}" alt=""></div>
                    <div id="item1">
                        <div id="item2">
                            <div>${book.bname}</div>
                        </div>
                        <div id="item3" class="text">
                            <div>作者：${book.author}</div>
                            <div>出版社：${book.press}</div>
                            <div>出版时间：${book.pdate}</div>
                        </div>
                        <div class="text">${book.info}</div>
                        <div>服务：由“当当”发货，并提供售后服务。 23:15前完成下单，预计明天可送达</div>
                        <div id="item4">
                            <div class="num">￥${book.price}</div>
                            <a href="<%=path%>user/ShopcarAddSvl?isbn=${book.isbn}" class="btn">加入购物车</a>
                            <a href="<%=path%>MainSvl" style="color: #f09d51">返回</a>
                        </div>
                    </div>
                </div>
<%--                    <tr><td rowspan=3><img width=100 height=100 src="<%=basePath%>${book.pic}"/></td><td colspan=2 align=center style="color:red">${book.bname}</td></tr>--%>
<%--                    <tr><td>商品价格</td><td>${book.price}</td></tr>--%>
<%--                    <tr><td>出版社</td><td>${book.press}</td></tr>--%>
<%--                    <tr><td height=300 colspan=3>${book.info}</td></tr>--%>
<%--                    <tr><td colspan=3 align=center><a href="<%=path%>user/ShopcarAddSvl?isbn=${book.isbn}">加入购物车</a> &nbsp; <a href="<%=path%>MainSvl">返回</a></td></tr>--%>
            </c:forEach>
		</c:if>

</body>
</html>