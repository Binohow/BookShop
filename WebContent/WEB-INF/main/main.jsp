<%@ page language="java" contentType="text/html"   pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
   String basePath = request.getContextPath() + "/";
   String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+basePath;
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
    <style>
        .searchbtn{
            color: #222;
            background: #f09d51;
            border-radius: 26px;
            width: 116px;
            box-shadow: 0 8px 21px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
   <!-- nav bar -->
   <jsp:include page="mhead.jsp"></jsp:include>
    <!-- end of nav bar -->

   <!-- 轮播图 -->
   <div class="imgBox">
       <img class="img-slide img1" src="<%=basePath%>pic/banners/banner1.png" alt="1">
       <img class="img-slide img2" src="<%=basePath%>pic/banners/banner2.png" alt="2">
       <img class="img-slide img3" src="<%=basePath%>pic/banners/banner3.png" alt="3">
       <img class="img-slide img4" src="<%=basePath%>pic/banners/banner4.png" alt="4">
   </div>
<script type="text/javascript">
    var index = 0;
    //改变图片
    function ChangeImg() {
        index++;
        var a = document.getElementsByClassName("img-slide");
        if (index >= a.length) index = 0;
        for (var i = 0; i < a.length; i++) {
            a[i].style.display = 'none';
        }
        a[index].style.display = 'block';
    }
    //设置定时器，每隔*秒切换一张图片
    setInterval(ChangeImg, 4000);
    function tijiao(){
		debugger;
		var bname = document.getElementById("bname").value;

		if(bname == ""){
		   alert("书名不能为空!");
		   return false;
		}
		var myform = document.getElementById("myform");
		myform.submit();
	}
</script>
   <!-- 轮播图结束 -->

    <!-- products -->
   <section class="products">
        <div class="section-title">
            <form action="<%=path%>BookSearchSvl" method="post" id="myform">
                <input type="text" name="bname" id="bname" placeholder="search our products..." style="margin-left: 310px;border-bottom: 2px solid #f09d51;margin-right: 40px;">
                <input type="button" value="搜索" onclick="tijiao()" class="searchbtn">
            </form>
<%--            <input type="text" placeholder="search our products...">--%>
        </div>
        <div class="products-center">
            <!-- single product -->
            <c:forEach items="${books}" var="bk">
                <arcticle class="product">
                    <div class="img-container">
                        <img src= "<%=basePath%>${bk.pic}" alt="product" class="product-img">
                        <button class="bag-btn" data-id=${bk.isbn}>
                            <i class="fa fa-shopping cart"></i>
                            add to cart
                        </button>
                    </div>
                    <a href="<%=basePath%>BookSvl?isbn=${bk.isbn}">${bk.bname}</a>
                    <h4>￥${bk.price}</h4>
                </arcticle>
            </c:forEach>
        </div>
    </section>
<script src="<%=basePath%>js/main.js"></script>
</body>
</html>