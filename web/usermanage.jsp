<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link rel="stylesheet" href= "<%=request.getContextPath()%>/css/indexstyle.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/managestyle.css">
    <title>用户管理界面</title>

</head>
<body>

    <!-- 包含顶部按钮网页 -->
    <jsp:include page="/baseModule/top.jsp"></jsp:include>

    <div class="container">
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

                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-10 col-md-offset-1 users-tb">

                <table class="table table-hover table-bordered">
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
                                        <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${selUser.id}</span>编辑</a>
                                        <a href="<%= request.getContextPath()%>/usermanage/del?id=${user.id}" class="btn btn-danger btn-xs active" role="button">删除</a>
                                    </div>
                                </td>
                            </tr>

                        </c:if>

                        <c:if test="${users != null}">
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td class="row-id">${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>${user.pwd}
                                        <!-- 编辑删除按钮 -->
                                        <div class="option hidden">
                                            <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${user.id}</span>编辑</a>
                                            <a href="<%= request.getContextPath()%>/usermanage/del?id=${user.id}" class="btn btn-danger btn-xs active" role="button">删除</a>
                                        </div>
                                    </td>
                                </tr>

                            </c:forEach>
                        </c:if>

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
                                                $(".users-tb tbody tr .option a:first-child").click(function () {
                                                    let id = $(this).find("span").text();
                                                    $("#updmodal .modal-body .form-group:first input").val(id);
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

    </div>
</body>
</html>