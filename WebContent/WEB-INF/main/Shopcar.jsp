<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>
<!DOCTYPE html>
<html>
<head>
<title>购物车</title>
	<!-- custom css -->
    <link rel="stylesheet" href="<%=basePath%>css/style1.css">
	<link rel="Shortcut Icon" href="<%=basePath%>pic/logo.svg" type="image/x-icon" />
	<!-- fontawesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        table{
            border-collapse:collapse;
            width: 70%;
            text-align: center;
            line-height: 250%;
            margin-top: 70px;
            margin-left: 13%;
        }
        .checkbtn{
            color: #f0b548;
            text-align: center;
            width: 72px;
            height: 32px;
            line-height:30px;
            border: rgba(240, 157, 81,0.85) solid 2px;
            border-radius: 6px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            cursor: pointer;
            transition: .5s;
            font-size: 1rem;
            background: none;
        }
        .checkbtn:hover{
            color: #fff;
            background: #f0b548;
        }
         #search4{ border-width: 0; text-align:center;}
            /*获取焦点时 外边框显示红色*/
         #search4:focus{ border:1px solid red; }
    </style>
</head>
<body>
<!-- nav bar -->
   <jsp:include page="mhead.jsp"></jsp:include>
    <!-- end of nav bar -->
<form action="<%=basePath%>user/CheckoutSvl" method="post">
    <table align="center" border="2px solid">
        <thead>
            <tr style="color: #f09d51;">
                <th>书名</th>
                <th>作者</th>
                <th>商品价格</th>
                <th>数量</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="bk" items="${books}">
                <tr>
                    <td>${bk.bname}</td>
                    <td>${bk.author}</td>
                    <td>￥${bk.price}</td>
                    <td><input type="text"   id="search4"  name="bk${bk.isbn}" value="${bk.buynum}" /></td>
                    <td><a href="<%=basePath%>user/ShopcarRemoveSvl?isbn=${bk.isbn}" style="color: #f09d51;text-decoration: none">移除</a></td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <tr>
               <td align="center" colspan="5">
                    <c:if test="${bkSize > 0 }">
                        <input type="submit" value="结算" class="checkbtn"> &nbsp;&nbsp;
                    </c:if>
                <a href="<%=basePath%>MainSvl" style="color: #f09d51;text-decoration: none">返回</a>
               </td>
            </tr>
        </tfoot>
    </table>
</form>
</body>
</html>