<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
   String basePath = request.getContextPath() + "/";   
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="Shortcut Icon" href="<%=basePath%>pic/logo.svg" type="image/x-icon" />
    <title>书城主页</title>
    <!-- fontawesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- custom css -->
    <link rel="stylesheet" href="<%=basePath%>css/style1.css">
    <link rel="stylesheet" href="<%=basePath%>css/bookstyle.css">
</head>
<body>
   <!-- nav bar -->
    <jsp:include page="mhead.jsp"></jsp:include>
    <!-- end of nav bar -->

    <!-- product details -->
    <!-- 网格布局 -->
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
                <a href="<%=basePath%>user/ShopcarAddSvl?isbn=${book.isbn}" class="btn">加入购物车</a>
                <a href="<%=basePath%>MainSvl" style="color: #f09d51">返回</a>
            </div>
        </div>
    </div>
</body>
</html>


