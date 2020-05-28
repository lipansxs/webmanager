<%--
  Created by IntelliJ IDEA.
  User: sxs
  Date: 2020/5/13
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>首页</title>
	<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
	<link rel="stylesheet" href="css/indexstyle.css">
<%--	<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">--%>

	<style type="text/css">
		li{
			list-style: none;
		}
	</style>
	
</head>
<body>
	
		<!-- 包含顶部按钮网页 -->
		<jsp:include page="/baseModule/top.jsp"></jsp:include>
		
		 	<div class="container">
                <div class="row">
                    <div class="col-lg-10 col-lg-offset-1">
                        <h1 class="page-header text-center">欢迎来到啦啦啦管理系统</h1>  
                        <p class="lead h2" style="color: white">在这个系统你可以做什么?</p>
                        <ul style="color: white" style="decoration: none">
                        	<li ><sapn class="h3">1. 系统管理</sapn>
                        		<ul>
                        			<li class="h4">用户管理</li>
                        			<li class="h4">货号管理</li>
                        		</ul>
                        	</li>
                        	<li><sapn class="h3">2. 库存管理</sapn>
                        		<ul>
                        			<li class="h4">入库单基本信息管理</li>
                        			<li class="h4">入库单详细信息管理</li>
                        			<li class="h4">出库单基本信息管理</li>
                        			<li class="h4">出库单详细信息管理</li>
                        		</ul>
                        	</li>
                        	<li><sapn class="h3">3. 辅助管理</sapn>
                        		<ul>
                        			<li class="h4">修改密码</li>
                        		</ul>
                        	</li>
                        </ul>
                    </div>
                </div>
            </div>
</body>
</html>
