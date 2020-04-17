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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
  function tijiao(){
	  var r=confirm("确认是否付款？")
	  if (r==true){
		  var myform = document.getElementById("myform");
		  myform.submit();		
	  }  
  }
</script>
<form action="<%=basePath%>user/PaySvl" method="post" id="myform">
	<table align="center" width=90%>
	<jsp:include page="mhead.jsp"></jsp:include>
      <tr>
      	<td>
      		<table border="1" width=100%> 
      			<tr><td>书名</td><td>作者</td><td>商品价格</td><td width="5%">数量</td></tr>		       
       			   <c:forEach items="${books}" var="bk"> 				
       				<tr><td>${bk.bname}</td><td>${bk.author}</td><td>${bk.price}<td>${bk.buynum}本</tr>    
				   </c:forEach>  
      			 <tr><td colspan=4 align=center>账户余额：￥${user.account} &nbsp;&nbsp;&nbsp;&nbsp; 商品总价：${allMoney} </td></tr>
      			 <tr><td colspan=4 >
      			            <input type="hidden" name="allMoney" value="${allMoney}">
      			 </td></tr>
    		</table>
      	</td>
      </tr>
      <tr>
      		<td align="center">
      		   <c:if test="${user.account>=allMoney}">
      		       <input type="button" value="付款确认" onclick="tijiao()">&nbsp;
      		       
      		   </c:if>
      		    <c:if test="${user.account<allMoney}">
      		        <p style="color:red;font-size:8px;">账户金额不足,请充值！</p>
      		   </c:if>
      		</td>
      </tr>
    
    </table>
	</form>
</body>
</html>