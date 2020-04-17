<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="Shortcut Icon" href="<%=basePath%>pic/logo.svg" type="image/x-icon" />
<link rel="stylesheet" href="<%=basePath%>css/style.css">
<!-- fontawesome -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

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
<!--左侧wave图，在-1层-->
    <img src="<%=basePath%>pic/img/wave.png" alt="" class="wave">
    <!--第0层的容器-->
    <div class="container">
        <div class="img">
            <img src="<%=basePath%>pic/img/img.svg" alt="img">
        </div>
        <!--右侧的登录区-->
        <div class="login-container">
            <form action="<%=basePath%>LoginSvl" method="post" id="myform">
                <!--用户头像示例-->
                <img class="avatar" src="<%=basePath%>pic/img/avatar.svg" alt="avatar">
                <h2>Welcome</h2>
                <!--用户名-->
                <div class="input-div one">
                    <div class="i">
                        <!--使用官网建议的fas仅能显示方框，改成老版本的fa可以正常显示图标-->
                        <i class="fa fa-user"></i>
                    </div>
                    <div>
                        <h5>Username</h5>
                        <input type="text" class="input" name="uname" id="uname">
                    </div>
                </div>
                <!--密码框-->
                <div class="input-div two">
                    <div class="i">
                        <i class="fa fa-lock"></i>
                    </div>
                    <div>
                        <h5>Password</h5>
                        <input type="password" class="input" name="pwd" id="pwd">
                    </div>
                </div>
                <!--注册-->
                <a href="<%=basePath%>RegistSvl">Sign up for shopping!</a></td>
                <!--提交按钮-->
                <input type="submit" class="btn" value="Login" onclick="tijiao()">
                <div style="color:red;font-size:8px">${msg}</div>
            </form>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath%>js/main.js"></script>
<%--   <form action="<%=basePath%>LoginSvl" method="post" id="myform">--%>
<%--        <table align="center">            --%>
<%--            <tr><td height=200></td></tr>--%>
<%--            <tr><td>用户名：</td><td><input type="text" name="uname" id="uname"></td></tr>--%>
<%--            <tr><td>密码：</td><td><input type="password" name="pwd" id="pwd"></td></tr>--%>
<%--            <tr><td colspan="2" align="center">--%>
<%--            	<input type="button" value="提交" onclick="tijiao()"/>            	--%>
<%--            	&nbsp;&nbsp;<a href="<%=basePath%>RegistSvl">注册</a></td>--%>
<%--            </tr>--%>
<%--            <tr><td colspan="2" align="center"><span id="myMsg" style="color:red;font-size:8px">${msg}</span></td></tr>--%>
<%--        </table>--%>
<%--    </form>--%>

</body>
</html>