<%--
  Created by IntelliJ IDEA.
  User: sxs
  Date: 2020/5/18
  Time: 8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.sxs.constant.Constant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登入失败</title>

    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }

        html,body{
            height: 100%;
        }

        body{
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .info-content{
            height: 400px;
            width: 700px;
            background-color: rgba(23, 229, 34, .6);
            border-radius: 15px;
            border: 1px solid rgba(45, 234, 3, .5)
        }

        .info-content .error-info{
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            color: rgba(175, 125, 32, 0.8);
            font-size: 20px;
        }

    </style>

</head>
<body>
<div class="info-content">
    <div class="error-info">
        <c:if test="${param.loginresult == Constant.PASSWORD_NOT_RIGHT}">
            <span>密码错误</span>
        </c:if>

        <c:if test="${param.loginresult == Constant.USER_NOT_EXISTENCE}">
            <span>该用户不存在</span>
        </c:if>
    </div>
</div>
</body>
</html>
