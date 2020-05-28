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
    <title>出库单详细信息管理界面</title>

</head>
<body>

    <!-- 包含顶部按钮网页 -->
    <jsp:include page="/baseModule/top.jsp"></jsp:include>

    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
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

                        <button type="button" class="btn btn-success add-btn" data-toggle="modal" data-target="#addInOrder" style="margin-left: 500px">增加出库单</button>

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

                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-11 col-md-offset-1 outorder-tb">

                <table class="table table-hover table-bordered">
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
                                        <a href="<%= request.getContextPath()%>/baseoutorder/del?id=${selOutOrder.id}" class="btn btn-danger btn-xs active" role="button">删除</a>
                                    </div>
                                </td>
                            </tr>

                        </c:if>

                        <c:if test="${outOrders != null}">
                            <c:forEach items="${outOrders}" var="outOrder">
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
                                            <a href="<%= request.getContextPath()%>/baseoutorder/del?id=${outOrder.id}" class="btn btn-danger btn-xs active" role="button">删除</a>
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
                                        <h4 class="modal-title" id="updmodaltitle" style="color: black">编辑入库单基本信息</h4>
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
                                                });

                                            </script>

                                            <div class="form-group">
                                                <label for="upd-goods-id" class="control-label">货号id</label>
                                                <input type="text" class="form-control" id="upd-goods-id" name="upd-goods-id"></input>
                                            </div>

                                            <div class="form-group">
                                                <label for="upd-in-count" class="control-label">进货数目</label>
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

    </div>
</body>
</html>