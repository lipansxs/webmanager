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
    <title>出库单详细信息管理界面</title>

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
            <div class="col-md-11 col-md-offset-1">
                <div class="search-add bg-success">

                    <div class="search">
                        <form class="form-inline" action="<%= request.getContextPath()%>/outorderdetail/sel">
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
                                if ($(this).prev("div").find("input[type=text]").val().length == 0) {
                                    showMsg("请输入查找内容");
                                    return false;
                                }
                            });
                        });
                    </script>

                    <button type="button" class="btn btn-success add-btn" data-toggle="modal" data-target="#addInOrder" style="margin-left: 620px">增加出库单</button>

                    <div class="modal fade" id="addInOrder" tabindex="-1" role="dialog" aria-labelledby="title">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="title" style="color: black">添加出库单</h4>
                                </div>
                                <form method="get" action="<%=request.getContextPath()%>/baseoutorder/add">
                                    <div class="modal-body">

                                        <div class="form-group">
                                            <label for="goods-id" class="control-label">货号id</label>
                                            <input type="text" class="form-control" id="goods-id" name="goods-id"></input>
                                        </div>

                                        <div class="form-group">
                                            <label for="in-count" class="control-label">进货数目</label>
                                            <input type="text" class="form-control" id="in-count" name="in-count">
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

                                        <div class="form-group">
                                            <label for="goods-name" class="control-label">商品名</label>
                                            <input type="text" class="form-control" id="goods-name" name="goods-name">
                                        </div>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <input type="submit" class="btn btn-primary" id="confirm-add-user-btn" value="确定"/>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>

                    <script>
                        $(function () {
                            $(".search-add .add-btn").click(function () {
                                let form = $("#addInOrder form");
                                form.find(".modal-footer input[type=submit]").unbind("click").click(function () {
                                    let modalBody = form.find(".modal-body");

                                    if (modalBody.find("#goods-id").val().length == 0) {
                                        showMsg("请输入货号id");
                                        return false;
                                    }

                                    if (modalBody.find("#in-count").val().length == 0) {
                                        showMsg("请输入进货数目");
                                        return false;
                                    }

                                    if (modalBody.find("#goods-price").val().length == 0) {
                                        showMsg("请输入单价");
                                        return false;
                                    }

                                    if (modalBody.find("#goods-buy").val().length == 0){
                                        showMsg("请输入买方");
                                        return false;
                                    }

                                    if (modalBody.find("#goods-sale").val().length == 0) {
                                        showMsg("请输入卖方");
                                        return false;
                                    }

                                    if (modalBody.find("#goods-name").val().length == 0) {
                                        showMsg("请输入商品名");
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
            <div class="col-md-11 col-md-offset-1 outorder-tb">

                <table class="table table-hover table-bordered table-striped">
                    <thead>
                    <tr>
                        <td class="col-md-1">商品id</td>
                        <td class="col-md-1">货号id</td>
                        <td class="col-md-1">数目</td>
                        <td class="col-md-1">单价</td>
                        <td class="col-md-1">总价</td>
                        <td class="col-md-1">买方</td>
                        <td class="col-md-1">卖方</td>
                        <td class="col-md-2">时间</td>
                        <td class="col-md-2">商品名</td>
                    </tr>
                    </thead>
                    <tbody>

                        <c:if test="${selOutOrder != null}">
                            <tr>
                                <td class="row-id">${selOutOrder.id}</td>
                                <td>${selOutOrder.goodsNumId}</td>
                                <td>${selOutOrder.count}</td>
                                <td>${selOutOrder.price}</td>
                                <td>${selOutOrder.totalPrice}</td>
                                <td>${selOutOrder.buy}</td>
                                <td>${selOutOrder.sale}</td>
                                <td>${selOutOrder.time}</td>
                                <td>${selOutOrder.goodsName}
                                    <!-- 编辑删除按钮 -->
                                    <div class="option hidden">
                                        <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${selOutOrder.id}</span>编辑</a>
                                        <a href="/baseoutorder/del?id=${selOutOrder.id}" class="btn btn-danger btn-xs active delete-btn" data-toggle="modal" data-target=".bs-example-modal-sm" role="button">删除</a>
                                    </div>
                                </td>
                            </tr>

                        </c:if>

                        <c:if test="${pageInfo != null}">
                            <c:forEach items="${pageInfo.pageList}" var="outOrder">
                                <tr>
                                    <td class="row-id">${outOrder.id}</td>
                                    <td>${outOrder.goodsNumId}</td>
                                    <td>${outOrder.count}</td>
                                    <td>${outOrder.price}</td>
                                    <td>${outOrder.totalPrice}</td>
                                    <td>${outOrder.buy}</td>
                                    <td>${outOrder.sale}</td>
                                    <td>${outOrder.time}</td>
                                    <td>${outOrder.goodsName}
                                        <!-- 编辑删除按钮 -->
                                        <div class="option hidden">
                                            <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${outOrder.id}</span>编辑</a>
                                            <a href="/baseoutorder/del?id=${outOrder.id}" class="btn btn-danger btn-xs active delete-btn" data-toggle="modal" data-target=".bs-example-modal-sm" role="button">删除</a>
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
                                $(".outorder-tb tbody tr .option a:nth-child(2)").click(function () {
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
                                        <h4 class="modal-title" id="updmodaltitle" style="color: black">编辑出库单基本信息</h4>
                                    </div>
                                    <form method="post" action="<%=request.getContextPath()%>/outorderdetail/upd">
                                        <div class="modal-body">

                                            <div class="form-group hidden">
                                                <input type="text" class="form-control" id="upd-id" name="id">
                                            </div>

                                            <%--设置编辑的数据的id--%>
                                            <script type="text/javascript">
                                                $(".outorder-tb tbody tr .option a:first-child").click(function () {
                                                    let id = $(this).find("span").text();
                                                    $("#updmodal .modal-body .form-group:first input").val(id);

                                                    $("#updmodal .modal-footer input[type=submit]").click(function () {

                                                        let modalBody = $("#updmodal .modal-body");

                                                        if (modalBody.find("#upd-goods-id").val().length == 0){
                                                            showMsg("请输入货号id");
                                                            return false;
                                                        }

                                                        if (modalBody.find("#upd-in-count").val().length == 0) {
                                                            showMsg("请输入进货数目");
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
                                                            showMsg("请输入卖方");
                                                            return false;
                                                        }

                                                        if (modalBody.find("#upd-goods-name").val().length == 0) {
                                                            showMsg("请输入商品名");
                                                            return false;
                                                        }
                                                    });
                                                });

                                            </script>

                                            <div class="form-group">
                                                <label for="upd-goods-id" class="control-label">货号id</label>
                                                <input type="text" class="form-control" id="upd-goods-id" name="upd-goods-id"></input>
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-in-count" class="control-label">出货数目</label>
                                                <input type="text" class="form-control" id="upd-in-count" name="upd-in-count">
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-goods-price" class="control-label">单价</label>
                                                <input type="text" class="form-control" id="upd-goods-price" name="upd-goods-price">
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-goods-buy" class="control-label">买方</label>
                                                <input type="text" class="form-control" id="upd-goods-buy" name="upd-goods-buy">
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-goods-sale" class="control-label">卖方</label>
                                                <input type="text" class="form-control" id="upd-goods-sale" name="upd-goods-sale">
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-goods-name" class="control-label">商品名</label>
                                                <input type="text" class="form-control" id="upd-goods-name" name="upd-goods-name">
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
                            $(".outorder-tb tbody tr").mouseover(function(){
                                $(this).find(".option").removeClass("hidden");
                            });

                            $(".outorder-tb tbody tr").mouseleave(function(){
                                $(this).find(".option").addClass("hidden");
                            });
                        });
                    </script>
                </table>
            </div>
        </div>

        <script>
            $(function () {
                $(".outorder-tb tbody tr").click(function () {
                    let goodsId = $(this).find("td:nth-child(2)").text();
                    let count = $(this).find("td:nth-child(3)").text();
                    let price = $(this).find("td:nth-child(4)").text();
                    let buy = $(this).find("td:nth-child(6)").text();
                    let sale = $(this).find("td:nth-child(7)").text();
                    let goodsName = $(this).find("td:nth-child(9)").clone().children().remove().end().text().trim();

                    $("#updmodal #upd-goods-id").val(goodsId);
                    $("#updmodal #upd-in-count").val(count);
                    $("#updmodal #upd-goods-price").val(price);
                    $("#updmodal #upd-goods-buy").val(buy);
                    $("#updmodal #upd-goods-sale").val(sale);
                    $("#updmodal #upd-goods-name").val(goodsName);


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
                                        <a href="/ManageSystem/outorderdetail?pageIndex=${pageInfo.pageIndex - 1 < 1? 1: pageInfo.pageIndex - 1}" aria-label="Previous">上一页</a>
                                    </li>
                                </c:if>

                                <li class="${status.index == pageInfo.pageIndex? "active": ""}"><a href="/ManageSystem/outorderdetail?pageIndex=${status.index}">${status.index}</a></li>

                                <c:if test="${status.last}">
                                    <li>
                                        <a href="/ManageSystem/outorderdetail?pageIndex=${pageInfo.pageIndex + 1 > pageInfo.totalPageCount? pageInfo.totalPageCount: pageInfo.pageIndex + 1}" aria-label="Next">下一页</a>
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