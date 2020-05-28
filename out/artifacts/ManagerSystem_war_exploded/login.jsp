<%--
  Created by IntelliJ IDEA.
  User: sxs
  Date: 2020/5/17
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css">
    <script src="./js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".login-painer").slideDown("slow");

            $("#login-btn").click(function(){

                // 如果没有错误就可以继续往下执行，如果有错误就不往下执行
                var errorFlg = true;

                // 获取用户名输入框
                if (!$(".username").val().length) {
                    alert("请输入用户名");
                    errorFlg = false;
                }
                // 获取密码输入框
                if (!$(".password").val().length) {
                    alert("请输入密码");
                    errorFlg = false;
                }
                // 如果返回false就不会往下执行
                return errorFlg;
            });

        });
    </script>
    <title>登入界面</title>
</head>
<body>
<div class="login-painer">
    <div class="container">
        <span class="title"></span>
        <form id="login-form" action="login" method="post">
            <input type="text" name="username" class="username" placeholder="用户名">
            <input type="password" name="password" class="password" placeholder="密码">
            <input type="submit" value="登入" class = "login" id="login-btn"><span class="loss-pwd"><a href="#">忘记密码?</a></span>
        </form>
    </div>
</div>
</body>
</html>
