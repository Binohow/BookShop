<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>订单确认</title>
	<!-- custom css -->
    <link rel="stylesheet" href="<%=basePath%>css/style1.css">
	<link rel="Shortcut Icon" href="<%=basePath%>pic/logo.svg" type="image/x-icon" />
	<!-- fontawesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<style>
		#container{
			display: grid;
			grid-template-columns: 50% 50%;
			margin: 120px;
			font-family: "Lato", sans-serif;
			font-weight: bold;
			color: #222222;
		}
		#img{
			width: 100%;
		}
		#item1{
			display: grid;
			grid-template-rows: 20% 20% 30% 30%;
			font-size: 1.5rem;
			margin-top: 20px;
		}
	</style>
</head>
<body>
<jsp:include page="mhead.jsp"></jsp:include>
<div id="container">
	<div>
		<img src="<%=basePath%>pic/deliver.png" alt="" id="img">
	</div>
	<div id="item1">
		<div>付款成功！付款人：${user.uname}</div>
		<div>付款金额：￥<fmt:formatNumber value="${allMoney}" pattern="#.00" type="number"/></div>
		<div style="font-size: 2rem">我们会尽快为您安排配送!</div>
		<div><a href="<%=basePath%>MainSvl" style="color: #f09d51;text-decoration: none">返回主页</a></div>
	</div>
</div>
<%--  <table align="center" width=60%>--%>

<%--      <tr>--%>
<%--      	<td height="180"></td>--%>
<%--      	<td style="color:black;font-size:18px">--%>
<%--		   付款成功！ 付款人:${user.uname} <br>--%>
<%--		   付款金额： ￥${allMoney}--%>
<%--      	   <p style="color:red;font-size:30px">--%>
<%--			  我们会尽快为您进行配送--%>
<%--      	   </p>--%>

<%--      	</td>--%>
<%--      </tr>--%>
<%--      <tr><td height="80"></td></tr>--%>
<%--      <tr>--%>
<%--      		<td colspan="2" align="center"> <a href="<%=basePath%>MainSvl">返回主页</a></td>--%>
<%--      </tr>--%>
<%--    </table>--%>

</body>
</html>