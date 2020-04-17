<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript">
	function tijiao(){
		debugger;
		var uname = document.getElementById("uname").value;
		var pwd = document.getElementById("pwd").value;
		if(uname == ""){
		   alert("用户名不能为空!");
		   return false;
		}
		if(pwd == ""){
			alert("密码不能为空!");
			return false;
		}			
		var span = document.getElementById("myMsg");
		$.ajax({
			   type: "POST",
			   url: "<%=basePath%>LoginSvl2",
			   data: "uname=" + uname + "&pwd=" + pwd,
			   success: function(msg){
			      if(msg == "1"){
			    	  window.location.href="<%=basePath%>MainSvl";
			      }else if(msg == "2"){
			    	  span.innerHTML = "用户名或密码错误";
			      }else if (msg == "0"){
			    	  span.innerHTML = "用户名或密码不能为空";
			      }else if (msg == "-1"){
			    	  span.innerHTML = "网络异常，请和管理员联系...";
			      }
			   }
		});
	}	

</script>
</head>
<body>
   <form action="<%=basePath%>LoginSvl" method="post" id="myform">
        <table align="center">            
            <tr><td height=200></td></tr>
            <tr><td>用户名：</td><td><input type="text" name="uname" id="uname"></td></tr>
            <tr><td>密码：</td><td><input type="password" name="pwd" id="pwd"></td></tr>
            <tr><td colspan="2" align="center">
            	<input type="button" value="提交" onclick="tijiao()"/>            	
            	&nbsp;&nbsp;<a href="<%=basePath%>RegistSvl">注册</a></td>
            </tr>
            <tr><td colspan="2" align="center"><span id="myMsg" style="color:red;font-size:8px">${msg}</span></td></tr>
        </table>
    </form>

</body>
</html>