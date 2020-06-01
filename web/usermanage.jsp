<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="<%= request.getContextPath()%>/js/jquery-3.4.1.js"></script>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link rel="stylesheet" href= "<%=request.getContextPath()%>/css/indexstyle.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/managestyle.css">
    <title>用户管理界面</title>

</head>
<body>

    <%--警告信息--%>
    <div class="container">
        <div class="col-md-10 col-md-offset-1" style="position: relative">
            <div class="alert alert-warning  alert-dismissible fade in" role="alert">
                <%--                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                <strong>提示</strong> &nbsp; <span id="msg-danger"></span>
            </div>
        </div>
    </div>

    <script>

        $(function () {
            $(".alert").hide().slideUp("fast");
        });

        // 封装一个函数，用来显示提示信息，1秒后自动消失
        function showMsg(msg){
            $(".alert").slideToggle("normal", function () {
                setTimeout(function(){
                    $(".alert").slideToggle("normal");
                }, 1000)
            }).find("#msg-danger").text(msg);

        }

    </script>

    <!-- 包含顶部按钮网页 -->
    <jsp:include page="/baseModule/top.jsp"></jsp:include>

    <div class="container main">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="search-add bg-success">
                    <div class="search">
                        <form class="form-inline" method="get" action="<%= request.getContextPath()%>/usermanage/sel">
                            <div class="form-group">
                                <label class="sr-only" for="username-input">username</label>
                                <div class="input-group">
                                    <div class="input-group-addon">用户名</div>
                                    <input type="text" class="form-control" id="username-input" placeholder="username" name="name">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">搜索</button>
                        </form>
                    </div>
                    <span class="connect"></span>
                    <div class="search">
                        <form class="form-inline" action="<%= request.getContextPath()%>/usermanage/sel">
                            <div class="form-group">
                                <label class="sr-only" for="id-input">id</label>
                                <div class="input-group">
                                    <div class="input-group-addon">id</div>
                                    <input type="text" class="form-control" id="id-input" placeholder="id" name="id">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">搜索</button>
                        </form>
                    </div>

                    <script>
                        $(function () {
                            let form  = $(".search form");
                            form.find("button[type=submit]").click(function(){
                                let type = $(this).prev("div").find("input[type=text]");
                                if (type.val().length == 0 || "" == type.val()) {
                                    showMsg("请输入搜索内容！");
                                    return false;
                                }
                            });
                        });
                    </script>

                    <button type="button" class="btn btn-success add-btn" data-toggle="modal" data-target="#adduser" >增加用户</button>

                    <div class="modal fade" id="adduser" tabindex="-1" role="dialog" aria-labelledby="addusermodaltitle">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="addusermodaltitle" style="color: black">添加用户</h4>
                                </div>
                                <form method="get" action="<%=request.getContextPath()%>/usermanage/add">
                                    <div class="modal-body">

                                            <div class="form-group">
                                                <label for="user-name" class="control-label">用户名</label>
                                                <input type="text" class="form-control" id="user-name" name="name">
                                            </div>
                                            <div class="form-group">
                                                <label for="user-password" class="control-label">密码</label>
                                                <input type="password" class="form-control" id="user-password" name="pwd"></input>
                                            </div>

                                            <div class="form-group">
                                                <label for="user-password" class="control-label">确认密码</label>
                                                <input type="password" class="form-control" id="confirm-user-password" name="pwd"></input>
                                            </div>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <input type="submit" class="btn btn-primary" id="confirm-add-user-btn" value="确定"/>
                                        <%--                                    <a href="/usermanage/add?name=" class="btn btn-primary active" role="button">确定</a>--%>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>

                    <script>
                        // 添加模态框验证代码
                        $(function () {
                            $(".add-btn").click(function(){
                                let form = $("#adduser form");

                                form.find(".modal-footer input[type=submit]").unbind("click").click(function () {

                                    if (form.find("#user-name").val().length == 0) {
                                        showMsg("请输入用户名！");
                                        return false;
                                    }

                                    if (form.find("#user-password").val().length == 0) {
                                        showMsg("请输入密码！");
                                        return false;
                                    }

                                    if (form.find("#confirm-user-password").val().length == 0) {
                                        showMsg("请输入确认密码!")
                                        return false;
                                    }


                                    let pwd = form.find(".modal-body .form-group #user-password").val();
                                    let confirmPwd = form.find(".modal-body .form-group #confirm-user-password").val();

                                    if (pwd != confirmPwd) {
                                        showMsg("确认密码与密码不符！");
                                        return false;
                                    }
                                });
                            });
                        });
                    </script>

                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-10 col-md-offset-1 users-tb">

                <table class="table table-hover table-bordered table-striped">
                    <thead>
                    <tr>
                        <td class="col-md-3">用户编号</td>
                        <td class="col-md-3">用户名</td>
                        <td class="col-md-3">用户密码</td>
                    </tr>
                    </thead>
                    <tbody>

                        <c:if test="${selUser != null}">
                            <tr>
                                <td class="row-id">${selUser.id}</td>
                                <td>${selUser.name}</td>
                                <td>${selUser.pwd}
                                    <!-- 编辑删除按钮 -->
                                    <div class="option hidden">
                                        <a href="#" class="btn btn-primary btn-xs ${selUser.name == logging? "active" : "disabled"}" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${selUser.id}</span>编辑</a>
                                        <a href="/usermanage/del?id=${user.id}" class="btn btn-danger btn-xs active delete-btn" data-toggle="modal" data-target=".bs-example-modal-sm" role="button">删除</a>
                                    </div>
                                </td>
                            </tr>

                        </c:if>

                        <c:if test="${pageInfo != null}">
                            <c:forEach items="${pageInfo.pageList}" var="user">
                                <tr>
                                    <td class="row-id">${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.pwd}
                                        <!-- 编辑删除按钮 -->
                                        <div class="option hidden">
                                            <a href="#" class="btn btn-primary btn-xs ${user.name == logging? "active" : "disabled"}" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${user.id}</span>编辑</a>
                                            <a href="/usermanage/del?id=${user.id}" class="btn btn-danger btn-xs active delete-btn" data-toggle="modal" data-target=".bs-example-modal-sm" role="button">删除</a>
                                        </div>
                                    </td>
                                </tr>

                            </c:forEach>
                        </c:if>

                        <%--点击删除弹出的模态框--%>
                        <div class="modal fade bs-example-modal-sm" id="del-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
                            <div class="modal-dialog modal-sm" role="document">
                                <div class="modal-content" style="text-align: center">
                                    <h1 style="color: black">确定删除？</h1>

                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-success" data-dismiss="modal">取消</button>
                                        <a type="button" class="btn btn-danger confirm-del active" role="button">确定删除</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <script type="text/javascript">
                            $(function () {
                                $(".users-tb tbody tr .option a:nth-child(2)").click(function () {
                                    let url = $(this).attr("href");
                                    let modal = $("#del-modal .modal-content");
                                    modal.find(".confirm-del").click(function(){
                                        $(this).attr("href", "/ManageSystem" + url);
                                    });
                                });
                            });
                        </script>

                        <div class="modal fade" id="updmodal" tabindex="-1" role="dialog" aria-labelledby="updmodaltitle">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="updmodaltitle" style="color: black">编辑用户</h4>
                                    </div>
                                    <form method="post" action="<%=request.getContextPath()%>/usermanage/upd">
                                        <div class="modal-body">

                                            <div class="form-group hidden">
                                                <input type="text" class="form-control" id="upd-id" name="id">
                                            </div>

                                            <%--设置编辑的数据的id--%>
                                            <script type="text/javascript">
                                                $(function () {
                                                    $(".users-tb tbody tr .option a:first-child").click(function () {
                                                        let id = $(this).find("span").text();
                                                        $("#updmodal .modal-body .form-group:first input").val(id);

                                                        $("#updmodal .modal-footer input[type=submit]").unbind("click").click(function () {
                                                            let formGroup = $("#updmodal .modal-body .form-group");

                                                            if (formGroup.find("#upd-name").val().length == 0) {
                                                                showMsg("请输入用户名！")
                                                                return false;
                                                            }

                                                            let pwd = formGroup.find("#upd-pwd").val();
                                                            let confirmPwd = formGroup.find("#upd-confirm-pwd").val();

                                                            if (pwd.length == 0) {
                                                                showMsg("请输入密码!");
                                                                return false;
                                                            }

                                                            if (confirmPwd.length == 0) {
                                                                showMsg("请输入确认密码");
                                                                return false;
                                                            }

                                                            if (pwd != confirmPwd) {
                                                                showMsg("密码与确认密码不一致！");
                                                                return false;
                                                            }



                                                        });

                                                    });
                                                });

                                            </script>

                                            <div class="form-group">
                                                <label for="upd-name" class="control-label">新的用户名</label>
                                                <input type="text" class="form-control" id="upd-name" name="name">
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-pwd" class="control-label">新的密码</label>
                                                <input type="password" class="form-control" id="upd-pwd" name="pwd"></input>
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-confirm-pwd" class="control-label">确认密码</label>
                                                <input type="password" class="form-control" id="upd-confirm-pwd" name="confirm-pwd"></input>
                                            </div>

                                        </div>


                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                            <input type="submit" class="btn btn-primary" value="确定修改"></input>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </tbody>

                    <%--显示和隐藏编辑和删除按钮--%>
                    <script type="text/javascript">
                        $(function(){
                            $(".users-tb tbody tr").mouseover(function(){
                                $(this).find(".option").removeClass("hidden");
                            });

                            $(".users-tb tbody tr").mouseleave(function(){
                                $(this).find(".option").addClass("hidden");
                            });
                        });
                    </script>
                </table>
            </div>
        </div>

        <script>
            $(function(){
                $(".users-tb tbody tr").click(function () {

                    let userName = $(this).find("td:nth-child(2)").text();
                    $("#updmodal #upd-name").val(userName);
                });
            });
        </script>


        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="pageIndex">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${pageInfo.totalPageCount}" varStatus="status">
                                <c:if test="${status.first}">
                                    <li>
                                        <a href="/ManageSystem/usermanage?pageIndex=${pageInfo.pageIndex - 1 < 1? 1: pageInfo.pageIndex - 1}" aria-label="Previous">上一页</a>
                                    </li>
                                </c:if>

                                <li class="${status.index == pageInfo.pageIndex? "active": ""}"><a href="/ManageSystem/usermanage?pageIndex=${status.index}">${status.index}</a></li>

                                <c:if test="${status.last}">
                                    <li>
                                        <a href="/ManageSystem/usermanage?pageIndex=${pageInfo.pageIndex + 1 > pageInfo.totalPageCount? pageInfo.totalPageCount: pageInfo.pageIndex + 1}" aria-label="Next">下一页</a>
                                    </li>
                                </c:if>
                            </c:forEach>

                        </ul>
                    </nav>
                </div>
            </div>
        </div>

    </div>


</body>
</html>