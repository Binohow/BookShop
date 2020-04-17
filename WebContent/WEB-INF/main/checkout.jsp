<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>结算</title>
	<!-- custom css -->
    <link rel="stylesheet" href="<%=basePath%>css/style1.css">
	<link rel="Shortcut Icon" href="<%=basePath%>pic/logo.svg" type="image/x-icon" />
	<!-- fontawesome -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript">
  function tijiao(){
	  var r=confirm("确认是否付款？");
	  if (r==true){
		  var myform = document.getElementById("myform");
		  myform.submit();		
	  }  
  }
</script>
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
	</style>
</head>
<body>
<!-- nav bar -->
   <jsp:include page="mhead.jsp"></jsp:include>
    <!-- end of nav bar -->
<form action="<%=basePath%>user/PaySvl" method="post" id="myform">
	<table align="center" border="2px solid">
        <thead>
            <tr style="color: #f09d51;">
                <th>书名</th>
                <th>作者</th>
                <th>商品价格</th>
                <th>数量</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="bk" items="${books}">
                <tr>
                    <td>${bk.bname}</td>
                    <td>${bk.author}</td>
                    <td>￥${bk.price}</td>
                    <td>${bk.buynum}本</td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <tr>
               <td align="center" colspan="4">
				   账户余额：￥<fmt:formatNumber value="${user.account}" pattern="#.00" type="number"/>&nbsp;
				   图书总价：￥<fmt:formatNumber value="${allMoney}" pattern="#.00" type="number"/>&nbsp;
               </td>
            </tr>
			<tr>
				<td align="center" colspan="4">
					<c:if test="${user.account>=allMoney}">
					   <input type="button" value="付款确认" onclick="tijiao()" class="checkbtn">&nbsp;&nbsp;&nbsp;
				   </c:if>
				   <c:if test="${user.account<allMoney}">
					   <p style="color:red;font-size:8px;">账户金额不足,请充值！</p>
				   </c:if>
				   <a href="<%=basePath%>MainSvl" style="color: #f09d51;text-decoration: none">返回</a>
				</td>
			</tr>
			 <tr>
				 <td colspan=4 >
					<input type="hidden" name="allMoney" value="${allMoney}">
			 	</td>
			 </tr>
        </tfoot>
    </table>
<%--	<table align="center" width=90%>--%>
<%--      <tr>--%>
<%--      	<td>--%>
<%--      		<table border="1" width=100%> --%>
<%--      			<tr><td>书名</td><td>作者</td><td>商品价格</td><td width="5%">数量</td></tr>	--%>
<%--      			<c:forEach items="${books}" var="bk">  		--%>
<%--       				<tr><td>${bk.bname}</td><td>${bk.author}</td><td>${bk.price}</td><td >${bk.buyNum}本</tr>    --%>
<%--				</c:forEach>--%>
<%--      			    <tr><td colspan=4 align=center>账户余额：￥${user.account}  &nbsp;&nbsp;&nbsp;&nbsp; --%>
<%--      			         图书总价：￥   <fmt:formatNumber value="${allMoney}" pattern="#.00" type="number"/> </td></tr>--%>
<%--      			         <tr><td colspan=4 >--%>
<%--      			            <input type="hidden" name="allMoney" value="${allMoney}">--%>
<%--      			         </td></tr>--%>
<%--    		</table>--%>
<%--      	</td>--%>
<%--      </tr>--%>
<%--      <tr>--%>
<%--     		 <td align="center">--%>
<%--      	    	<c:if test="${user.account>=allMoney}">--%>
<%--      		    	<input type="button" value="付款确认" onclick="tijiao()">&nbsp;--%>
<%--      			</c:if> --%>
<%--      			<c:if test="${user.account<allMoney}">--%>
<%--      			    <p style="color:red;font-size:8px;">账户金额不足,请充值！</p>--%>
<%--      		    </c:if>       			--%>
<%--      		 	<a href="<%=basePath%>MainSvl">返回</a>--%>
<%--      		 </td>        	--%>
<%--      </tr>--%>
<%--    --%>
<%--    </table>--%>
	</form>

</body>
</html>