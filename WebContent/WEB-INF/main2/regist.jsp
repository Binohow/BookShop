<%@ page language="java" contentType="text/html"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
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
  <form action="<%=basePath%>RegistSvl" method="post" id="myform">
	 <table  align="center">
				<tr><td height=100></td></tr>
				<tr>
				  <td width="107" height="36">用户名：</td>
				  <td width="524"><INPUT name="uname" id="uname" type="text" maxlength="16" onkeyup="checkUname()">
					   <span style="color:red;font-size:8px" id="unameAlert"></span>
				  </td>
				</tr>
				<tr>
				  <td width="107" height="36">密码：</td>
				  <td width="524"><INPUT name="pwd" id="pwd" type="password"></td>
				</tr>
				<tr>
				  <td width="107" height="36">确认密码：</td>
				  <td width="524"><INPUT name="pwd2" id="pwd2" type="password"></td>
				</tr>
				<tr>
				<td width="107" height="36">性别：</td>
				<td width="524">
					<INPUT name="gen" type="radio"   value="男" checked>男&nbsp; 
					<INPUT name="gen" type="radio" value="女" >女
				</td>
			   </tr>
				<tr>
				<td width="107" height="36">电子邮件：</td>
				<td width="524"><INPUT name="email" type="text"></td>
			  </tr>   
				<tr>
					<td colspan=2 >
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="提交" onclick="tijiao()"> &nbsp; <a href="<%=basePath%>MainSvl">返回</a>
					</td>					
				</tr>
				<tr>
					<td colspan="2" align="center">
						<span style="color:red;font-size:8px">${msg}</span>
					</td>
				</tr>
			</table>
   </form>  
</body>
</html>