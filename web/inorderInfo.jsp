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
    <title>入库单详情管理界面</title>

</head>
<body>

    <%--警告信息--%>
    <div class="container main">
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

    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="search-add bg-success">

                    <div class="search">
                        <form class="form-inline" action="<%= request.getContextPath()%>/inorderdetail/sel">
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
                                if ($(this).prev("div").find("input[type=text]").val().length == 0){
                                    showMsg("请输入搜索内容");
                                    return false;
                                }
                            });
                        });
                    </script>

                    <button type="button" class="btn btn-success add-btn" data-toggle="modal" data-target="#addInOrder" style="margin-left: 530px">增加入库单</button>

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
                        $(".search-add .add-btn").click(function () {

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

        <div class="row">
            <div class="col-md-10 col-md-offset-1 inorder-detail-tb">

                <table class="table table-hover table-bordered  table-striped">
                    <thead>
                    <tr>
                        <td class="col-md-1">商品编号</td>
                        <td class="col-md-1">商品名</td>
                        <td class="col-md-1">货号id</td>
                        <td class="col-md-1">进货量</td>
                        <td class="col-md-1">实进货量</td>
                        <td class="col-md-1">进货差量</td>
                        <td class="col-md-1">单价</td>
                        <td class="col-md-1">总价</td>
                        <td class="col-md-1">买方</td>
                        <td class="col-md-2">卖方</td>
                    </tr>
                    </thead>
                    <tbody>

                    <c:if test="${selInOrder != null}">
                        <tr>
                            <td class="row-id">${selInOrder.id}</td>
                            <td>${selInOrder.goodsName}</td>
                            <td>${selInOrder.goodsNumId}</td>
                            <td>${selInOrder.inCount}</td>
                            <td>${selInOrder.trueCount}</td>
                            <td>${selInOrder.subCount}</td>
                            <td>${selInOrder.price}</td>
                            <td>${selInOrder.totalPrice}
                            <td>${selInOrder.buy}</td>
                            <td>${selInOrder.sale}
                                <!-- 编辑删除按钮 -->
                                <div class="option hidden">
                                    <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${selInOrder.id}</span>编辑</a>
                                    <a href="<%= request.getContextPath()%>/baseinorder/del?id=${selInOrder.id}" class="btn btn-danger btn-xs active" role="button">删除</a>
                                </div>
                            </td>
                        </tr>

                    </c:if>

                    <c:if test="${inorders != null}">
                        <c:forEach items="${inorders}" var="inorder">
                            <tr>
                                <td class="row-id">${inorder.id}</td>
                                <td>${inorder.goodsName}</td>
                                <td>${inorder.goodsNumId}</td>
                                <td>${inorder.inCount}</td>
                                <td>${inorder.trueCount}</td>
                                <td>${inorder.subCount}</td>
                                <td>${inorder.price}</td>
                                <td>${inorder.totalPrice}
                                <td>${inorder.buy}</td>
                                <td>${inorder.sale}
                                    <!-- 编辑删除按钮 -->
                                    <div class="option hidden">
                                        <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${inorder.id}</span>编辑</a>
                                        <a href="<%= request.getContextPath()%>/baseinorder/del?id=${inorder.id}" class="btn btn-danger btn-xs active" role="button">删除</a>
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
                                    <h4 class="modal-title" id="updmodaltitle" style="color: black">编辑入库单信息</h4>
                                </div>
                                <form method="post" action="<%=request.getContextPath()%>/inorderdetail/upd">
                                    <div class="modal-body">

                                        <div class="form-group hidden">
                                            <input type="text" class="form-control" id="upd-id" name="id">
                                        </div>

                                        <%--设置编辑的数据的id--%>
                                        <script type="text/javascript">
                                            $(".inorder-detail-tb tbody tr .option a:first-child").click(function () {
                                                let id = $(this).find("span").text();
                                                $("#updmodal .modal-body .form-group:first input").val(id);

                                                $("#updmodal .modal-footer input").click(function () {
                                                    let modalBody = $("#updmodal .modal-body");

                                                    if (modalBody.find("#upd-goods-name").val().length == 0) {
                                                        showMsg("请输入商品名");
                                                        return false;
                                                    }

                                                    if (modalBody.find("#upd-goods-id").val().length == 0) {
                                                        showMsg("请输入货号id");
                                                        return false;
                                                    }

                                                    if (modalBody.find("#upd-in-count").val().length == 0) {
                                                        showMsg("请输入进货数目");
                                                        return false;
                                                    }

                                                    if (modalBody.find("#upd-true-in-count").val().length == 0) {
                                                        showMsg("请输入实际进货数目");
                                                        return false;
                                                    }

                                                    if (modalBody.find("#upd-goods-price").val().length == 0) {
                                                        showMsg("请输入单价");
                                                        return false;
                                                    }

                                                    if (modalBody.find("#upd-goods-buy").val().length == 0) {
                                                        showMsg("请输入买方");
                                                        return false;
                                                    }

                                                    if (modalBody.find("#upd-goods-sale").val().length == 0) {
                                                        showMsg("亲输入卖方");
                                                        return false;
                                                    }

                                                });

                                            });

                                        </script>

                                        <div class="form-group">
                                            <label for="goods-name" class="control-label">商品名</label>
                                            <input type="text" class="form-control" id="upd-goods-name" name="goods-name">
                                        </div>

                                        <div class="form-group">
                                            <label for="goods-id" class="control-label">货号id</label>
                                            <input type="text" class="form-control" id="upd-goods-id" name="goods-id"></input>
                                        </div>

                                        <div class="form-group">
                                            <label for="in-count" class="control-label">进货数目</label>
                                            <input type="text" class="form-control" id="upd-in-count" name="in-count">
                                        </div>

                                        <div class="form-group">
                                            <label for="true-in-count" class="control-label">实际进货数目</label>
                                            <input type="text" class="form-control" id="upd-true-in-count" name="true-in-count">
                                        </div>

                                        <div class="form-group">
                                            <label for="goods-price" class="control-label">单价</label>
                                            <input type="text" class="form-control" id="upd-goods-price" name="goods-price">
                                        </div>

                                        <div class="form-group">
                                            <label for="goods-buy" class="control-label">买方</label>
                                            <input type="text" class="form-control" id="upd-goods-buy" name="goods-buy">
                                        </div>

                                        <div class="form-group">
                                            <label for="goods-sale" class="control-label">卖方</label>
                                            <input type="text" class="form-control" id="upd-goods-sale" name="goods-sale">
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
                            $(".inorder-detail-tb tbody tr").mouseover(function(){
                                $(this).find(".option").removeClass("hidden");
                            });

                            $(".inorder-detail-tb tbody tr").mouseleave(function(){
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