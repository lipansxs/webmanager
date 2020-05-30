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
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="./js/jquery-3.4.1.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".alert").hide().slideUp();
            $(".login-painer").slideDown("slow");

            $("#login-btn").click(function(){

                // 如果没有错误就可以继续往下执行，如果有错误就不往下执行
                var errorFlg = true;

                let warn_info = "请输入：";

                // 获取用户名输入框
                if (!$(".username").val().length) {

                    warn_info = warn_info + "用户名 ";
                    errorFlg = false;
                }
                // 获取密码输入框
                if (!$(".password").val().length) {
                    // alert("请输入密码");
                    warn_info += "密码"
                    errorFlg = false;
                }

                // 如果有一个没有输入
                if (!errorFlg){
                    // 滑动显示警告信息
                    $(".alert").removeClass("hidden").slideDown();

                    // 设置三秒之后自动向上滑动消失
                    setTimeout(function(){
                        $(".alert").removeClass("hidden").slideUp();
                    }, 1000)

                    $(".alert #msg-danger").text(warn_info);
                }

                // 如果返回false就不会往下执行
                return errorFlg;
            });

        });
    </script>

    <title>登入界面</title>
</head>
<body>

<div class="container ">
    <div class="col-md-10 col-md-offset-1" style="position: relative">
        <div class="alert alert-warning" role="alert">
            <strong>提示</strong> &nbsp; <span id="msg-danger"></span>
        </div>
    </div>
</div>

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
