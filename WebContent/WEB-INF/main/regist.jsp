<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<%
   String basePath = request.getContextPath() + "/";   
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!--响应式布局 1.在 meta 中声明 viewport 元标签-->
    <!--视口宽度设置为设备宽度，视口不能缩放-->
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>欢迎注册</title>
    <!--引入Google font，配合CSS的font-family-->
    <link href="https://fonts.font.im/css?family=Poppins:600" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/style.css">
    <!--引入fontawesome的css，引入kit的js图标不能正常显示-->
	<link rel="Shortcut Icon" href="<%=basePath%>pic/logo.svg" type="image/x-icon" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script>
		function tijiao(){
			var uname = document.getElementById("uname").value;
			var pwd = document.getElementById("pwd").value;
			var pwd2 = document.getElementById("pwd2").value;
			if(uname == ""){
			   alert("用户名不能为空!");
			   return false;
			}
			if(pwd == ""){
				alert("密码不能为空!");
				return false;
			}
			if(pwd2 == ""){
				alert("密码确认不能为空!");
				return false;
			}
			if(pwd != pwd2){
				alert("密码与密码确认不一致!");
				return false;
			}
			var myform = document.getElementById("myform");
			myform.submit();
		}

    var xmlhttp;         //创建全局变量
    
	function checkUname()
	{
    	debugger;
		xmlhttp=null	
		// 针对 Mozilla等浏览器的代码：
		if (window.XMLHttpRequest) {
		   xmlhttp=new XMLHttpRequest()
		}	
		// 针对 IE 的代码：
		else if (window.ActiveXObject){
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP")
		}
		var uname = document.getElementById("uname").value;
		var url = "<%=basePath%>UnameValidSvl?uname=" + uname;
		if (xmlhttp!=null) {
		  	xmlhttp.onreadystatechange=state_Change;  //回调方法，接收返回信息
		  	xmlhttp.open("GET",url,true);   //向目标地址，发送异步请求
		  	xmlhttp.send(null);
		 }
		else
		  {
		  alert("您的浏览器不支持XMLHTTP")
		  }
	}
    
	function state_Change(){
		// 如果 xmlhttp 显示为 "loaded"
		if (xmlhttp.readyState==4){
			  // 如果为 "OK"
			  if (xmlhttp.status==200){
			       //显示接收的信息
			       var msg = xmlhttp.responseText;	 			       
			       var sp = document.getElementById("unameAlert");
			       if(msg == "1"){
			    	   sp.innerHTML = "用户名已存在，请修改...";   
			       }else if(msg == "2"){
			    	   sp.innerHTML = "用户名可用...";
			       }else if(msg == "0"){
			    	   sp.innerHTML = "用户名不能为空...";
			       }else if(msg == "-1"){
			    	   sp.innerHTML = "网络繁忙，稍后再试...";
			       }else{
			    	   
			       }	       
		 		}else{
		   		  alert("Problem retrieving XML data")
		  	    }
		 }
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
           <form action="<%=basePath%>RegistSvl" method="post" id="myform">
	  <!--用户头像示例-->
		<img class="avatar" src="<%=basePath%>pic/img/avatar.svg" alt="avatar">
		<h2>welcome</h2>
		<!--用户名-->
		<div class="input-div one">
			<div class="i">
				<!--使用官网建议的fas仅能显示方框，改成老版本的fa可以正常显示图标-->
				<i class="fa fa-user"></i>
			</div>
			<div>
				<h5>Username</h5>
				<input type="text" class="input" name="uname" id="uname" maxlength="16" onkeyup="checkUname()">
				<span style="color:red;font-size:8px" id="unameAlert"></span>
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
		<!--确认密码-->
		<div class="input-div two">
			<div class="i">
				<i class="fa fa-lock"></i>
			</div>
			<div>
				<h5>Password</h5>
				<input type="password" class="input" name="pwd2" id="pwd2">
			</div>
		</div>
		<a href="<%=basePath%>MainSvl">Return</a>
		<!--提交按钮-->
		<input type="submit" class="btn" value="Sign up" onclick="tijiao()">

		<span style="color:red;font-size:8px">${msg}</span>
	<%--	 <table  align="center">--%>
	<%--		<tr><td height=100></td></tr>--%>
	<%--		<tr>--%>
	<%--		  <td width="107" height="36">用户名：</td>--%>
	<%--		  <td width="524"><INPUT name="uname" id="uname" type="text" maxlength="16" onkeyup="checkUname()">--%>
	<%--			   <span style="color:red;font-size:8px" id="unameAlert"></span>--%>
	<%--		  </td>--%>
	<%--		</tr>--%>
	<%--		<tr>--%>
	<%--		  <td width="107" height="36">密码：</td>--%>
	<%--		  <td width="524"><INPUT name="pwd" id="pwd" type="password"></td>--%>
	<%--		</tr>--%>
	<%--		<tr>--%>
	<%--		  <td width="107" height="36">确认密码：</td>--%>
	<%--		  <td width="524"><INPUT name="pwd2" id="pwd2" type="password"></td>--%>
	<%--		</tr>--%>
	<%--		<tr>--%>
	<%--		<td width="107" height="36">性别：</td>--%>
	<%--		<td width="524">--%>
	<%--			<INPUT name="gen" type="radio"   value="男" checked>男&nbsp; --%>
	<%--			<INPUT name="gen" type="radio" value="女" >女--%>
	<%--		</td>--%>
	<%--	   </tr>--%>
	<%--		<tr>--%>
	<%--		<td width="107" height="36">电子邮件：</td>--%>
	<%--		<td width="524"><INPUT name="email" type="text"></td>--%>
	<%--	  </tr>   --%>
	<%--		<tr>--%>
	<%--			<td colspan=2 >--%>
	<%--				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="提交" onclick="tijiao()"> &nbsp; <a href="<%=basePath%>MainSvl">返回</a>--%>
	<%--			</td>					--%>
	<%--		</tr>--%>
	<%--		<tr>--%>
	<%--			<td colspan="2" align="center">--%>
	<%--				<span style="color:red;font-size:8px">${msg}</span>--%>
	<%--			</td>--%>
	<%--		</tr>--%>
	<%--	</table>--%>
	</form>
        </div>
	</div>
	<script type="text/javascript" src="<%=basePath%>js/main.js"></script>
</body>
</html>