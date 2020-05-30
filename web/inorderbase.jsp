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
    <title>入库单基本信息管理界面</title>

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
                        <form class="form-inline" action="<%= request.getContextPath()%>/baseinorder/sel">
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
                            $(".search button").click(function () {
                                if ($(this).prev("div").find("#id-input").val().length == 0) {

                                    showMsg("请输入搜索内容");
                                    return false;
                                }
                            });
                        });
                    </script>

                    <div class="top-button" style="margin-left: 140px">
                        <button type="button" class="btn btn-success add-btn" data-toggle="modal" data-target="#addInOrder" style="margin-right: 50px">增加入库单</button>
                        <a href="<%= request.getContextPath()%>/inorderdetail" class="btn btn-primary active" role="button" style="text-decoration: none">查看所有入库单详情</a>
                    </div>

                    <div class="modal fade" id="addInOrder" tabindex="-1" role="dialog" aria-labelledby="title">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="title" style="color: black">添加入库单</h4>
                                </div>
                                <form method="get" action="<%=request.getContextPath()%>/baseinorder/add">
                                    <div class="modal-body">

                                            <div class="form-group">
                                                <label for="goods-name" class="control-label">商品名</label>
                                                <input type="text" class="form-control" id="goods-name" name="goods-name">
                                            </div>

                                            <div class="form-group">
                                                <label for="goods-id" class="control-label">货号id</label>
                                                <input type="text" class="form-control" id="goods-id" name="goods-id"></input>
                                            </div>

                                            <div class="form-group">
                                                <label for="in-count" class="control-label">进货数目</label>
                                                <input type="text" class="form-control" id="in-count" name="in-count">
                                            </div>

                                        <div class="form-group">
                                            <label for="true-in-count" class="control-label">实际进货数目</label>
                                            <input type="text" class="form-control" id="true-in-count" name="true-in-count">
                                        </div>

                                        <div class="form-group">
                                            <label for="goods-price" class="control-label">单价</label>
                                            <input type="text" class="form-control" id="goods-price" name="goods-price">
                                        </div>

                                        <div class="form-group">
                                            <label for="goods-buy" class="control-label">买方</label>
                                            <input type="text" class="form-control" id="goods-buy" name="goods-buy">
                                        </div>

                                        <div class="form-group">
                                            <label for="goods-sale" class="control-label">卖方</label>
                                            <input type="text" class="form-control" id="goods-sale" name="goods-sale">
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
                        $(function () {
                            $(".top-button button").click(function () {

                                $("#addInOrder .modal-footer input").unbind("click").click(function () {

                                    let modalBody = $("#addInOrder .modal-body");

                                    if (modalBody.find("#goods-name").val().length == 0) {
                                        showMsg("请输入商品名");
                                        return false;
                                    }

                                    if (modalBody.find("#goods-id").val().length == 0) {
                                        showMsg("请输入货号id");
                                        return false;
                                    }

                                    if (modalBody.find("#in-count").val().length == 0) {
                                        showMsg("请输入进货数目");
                                        return false;
                                    }

                                    if (modalBody.find("#true-in-count").val().length == 0) {
                                        showMsg("请输入实际进货数");
                                        return false;
                                    }

                                    if (modalBody.find("#goods-price").val().length == 0) {
                                        showMsg("请输入单价");
                                        return false;
                                    }

                                    if (modalBody.find("#goods-buy").val().length == 0) {
                                        showMsg("请输入买方");
                                        return false;
                                    }

                                    if (modalBody.find("#goods-sale").val().length == 0) {
                                        showMsg("请输入卖方");
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
            <div class="col-md-10 col-md-offset-1 base-inorder-tb">

                <table class="table table-hover table-bordered  table-striped">
                    <thead>
                    <tr>
                        <td class="col-md-2">商品编号</td>
                        <td class="col-md-2">商品名</td>
                        <td class="col-md-2">实际进货量</td>
                        <td class="col-md-2">总价</td>
                    </tr>
                    </thead>
                    <tbody>

                        <c:if test="${selInOrderBase != null}">
                            <tr>
                                <td class="row-id">${selInOrderBase.id}</td>
                                <td>${selInOrderBase.goodsName}</td>
                                <td>${selInOrderBase.trueCount}</td>
                                <td>${selInOrderBase.totalPrice}
                                    <!-- 编辑删除按钮 -->
                                    <div class="option hidden">
                                        <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${selInOrderBase.id}</span>编辑</a>
                                        <a href="<%= request.getContextPath()%>/inorderdetail/sel?id=${selInOrderBase.id}" class="btn btn-danger btn-xs active" role="button">查看详情</a>
                                        <a href="/baseinorder/del?id=${selInOrderBase.id}" class="btn btn-danger btn-xs active delete-btn" data-toggle="modal" data-target=".bs-example-modal-sm" role="button">删除</a>
                                    </div>
                                </td>
                            </tr>

                        </c:if>

                        <c:if test="${inorderBases != null}">
                            <c:forEach items="${inorderBases}" var="inorderBase">
                                <tr>
                                    <td class="row-id">${inorderBase.id}</td>
                                    <td>${inorderBase.goodsName}</td>
                                    <td>${inorderBase.trueCount}</td>
                                    <td>${inorderBase.totalPrice}
                                        <!-- 编辑删除按钮 -->
                                        <div class="option hidden">
                                            <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${inorderBase.id}</span>编辑</a>
                                            <a href="<%= request.getContextPath()%>/inorderdetail/sel?id=${inorderBase.id}" class="btn btn-primary btn-xs active" role="button">查看详情</a>
                                            <a href="/baseinorder/del?id=${inorderBase.id}" class="btn btn-danger btn-xs active delete-btn" data-toggle="modal" data-target=".bs-example-modal-sm" role="button">删除</a>
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
                                $(".base-inorder-tb tbody tr .option a:nth-child(3)").click(function () {
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
                                        <h4 class="modal-title" id="updmodaltitle" style="color: black">编辑入库单基本信息</h4>
                                    </div>
                                    <form method="post" action="<%=request.getContextPath()%>/baseinorder/upd">
                                        <div class="modal-body">

                                            <div class="form-group hidden">
                                                <input type="text" class="form-control" id="upd-id" name="id">
                                            </div>

                                            <%--设置编辑的数据的id--%>
                                            <script type="text/javascript">
                                                $(".base-inorder-tb tbody tr .option a:first-child").click(function () {
                                                    let id = $(this).find("span").text();
                                                    $("#updmodal .modal-body .form-group:first input").val(id);

                                                    $("#updmodal .modal-footer input[type=submit]").unbind("click").click(function () {
                                                        let modalBody = $("#updmodal .modal-body");
                                                        if (modalBody.find("#upd-goods-name").val().length == 0) {
                                                            showMsg("请输入商品名");
                                                            return false;
                                                        }

                                                        if (modalBody.find("#upd-true-in-count").val().length == 0) {
                                                            showMsg("请输入实际进货数");
                                                            return false;
                                                        }

                                                        if (modalBody.find("#upd-total-price").val().length == 0) {
                                                            showMsg("请输入总价");
                                                            return false;
                                                        }
                                                    });


                                                });

                                            </script>

                                            <div class="form-group">
                                                <label for="upd-goods-name" class="control-label">新的商品名</label>
                                                <input type="text" class="form-control" id="upd-goods-name" name="goods-name">
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-true-in-count" class="control-label">新的实际进货量</label>
                                                <input type="text" class="form-control" id="upd-true-in-count" name="upd-true-in-count"></input>
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-total-price" class="control-label">新的总价</label>
                                                <input type="text" class="form-control" id="upd-total-price" name="upd-total-price"></input>
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
                            $(".base-inorder-tb tbody tr").mouseover(function(){
                                $(this).find(".option").removeClass("hidden");
                            });

                            $(".base-inorder-tb tbody tr").mouseleave(function(){
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