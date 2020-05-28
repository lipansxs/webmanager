<%--
  Created by IntelliJ IDEA.
  User: sxs
  Date: 2020/5/24
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link rel="stylesheet" href= "<%=request.getContextPath()%>/css/indexstyle.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/managestyle.css">
    <title>货号管理界面</title>
</head>
<body>
    <!-- 包含顶部按钮网页 -->
    <jsp:include page="/baseModule/top.jsp"></jsp:include>

    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="search-add bg-success">
                    <div class="search">
                        <form class="form-inline" method="get" action="<%= request.getContextPath()%>/goodsnum/sel">
                            <div class="form-group">
                                <label class="sr-only" for="goodsnum-input">goodsnum</label>
                                <div class="input-group">
                                    <div class="input-group-addon">货号</div>
                                    <input type="text" class="form-control" id="goodsnum-input" placeholder="货号" name="goods-num">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary">搜索</button>
                        </form>
                    </div>
                    <span class="connect"></span>
                    <div class="search">
                        <form class="form-inline" action="<%= request.getContextPath()%>/goodsnum/sel">
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
                    <button type="button" class="btn btn-success add-btn" data-toggle="modal" data-target="#addgoodnum" >增加货号</button>

                    <div class="modal fade" id="addgoodnum" tabindex="-1" role="dialog" aria-labelledby="addgoodsnumtitle">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                            aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="addgoodsnumtitle" style="color: black">添加货号</h4>
                                </div>
                                <form method="get" action="<%=request.getContextPath()%>/goodsnum/add">
                                    <div class="modal-body">

                                        <div class="form-group">
                                            <label for="goods-num" class="control-label">货号</label>
                                            <input type="text" class="form-control" id="goods-num" name="goods-num">
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
            <div class="col-md-10 col-md-offset-1 goods-num-tb">

                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <td class="col-md-3">货号id</td>
                        <td class="col-md-3">货号</td>
<%--                        <td class="col-md-3">用户密码</td>--%>
                    </tr>
                    </thead>
                    <tbody>

                    <%--查询的货号数据--%>
                    <c:if test="${selGoodsNum != null}">
                        <tr>
                            <td>${selGoodsNum.id}</td>
                            <td>${selGoodsNum.goodsNum}
                                <!-- 编辑删除按钮 -->
                                <div class="option hidden">
                                    <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${selGoodsNum.id}</span>编辑</a>
                                    <a href="<%= request.getContextPath()%>/goodsnum/del?id=${user.id}" class="btn btn-danger btn-xs active" role="button">删除</a>

                                </div>
                            </td>
                        </tr>

                    </c:if>

                    <%--查询所有的货号数据--%>
                    <c:if test="${goodsnums != null}">
                        <c:forEach items="${goodsnums}" var="goodsnum">
                            <tr >
                                <td>${goodsnum.id}</td>
                                <td>${goodsnum.goodsNum}
                                    <!-- 编辑删除按钮 -->
                                    <div class="option hidden">
                                        <a href="#" class="btn btn-primary btn-xs active" data-toggle="modal" data-target="#updmodal" role="button"><span class="hidden">${goodsnum.id}</span>编辑</a>
                                        <a href="<%= request.getContextPath()%>/goodsnum/del?id=${goodsnum.id}" class="btn btn-danger btn-xs active" role="button">删除</a>

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
                                    <h4 class="modal-title" id="updmodaltitle" style="color: black">编辑货号</h4>
                                </div>
                                <form method="post" action="<%=request.getContextPath()%>/goodsnum/upd">
                                    <div class="modal-body">

                                        <div class="form-group hidden">
                                            <input type="text" class="form-control" id="upd-id" name="id">
                                        </div>

                                        <%--设置编辑的数据的id--%>
                                        <script type="text/javascript">
                                            $(".goods-num-tb tbody tr .option a:first-child").click(function () {
                                                let id = $(this).find("span").text(); // 获取点击编辑按钮的数据的id
                                                $("#updmodal .modal-body .form-group:first input").val(id);
                                            });
                                        </script>

                                        <div class="form-group">
                                            <label for="upd-name" class="control-label">新的货号</label>
                                            <input type="text" class="form-control" id="upd-name" name="goods-num">
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
                            $(".goods-num-tb tbody tr").mouseover(function(){
                                $(this).find(".option").removeClass("hidden");
                            });

                            $(".goods-num-tb tbody tr").mouseleave(function(){
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
