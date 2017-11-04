<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<head>
    <%@ include file="/WEB-INF/views/adverting/webSocket.jsp" %>
</head>
<script type="application/javascript">
    function updateAdvingSetting() {
        ;//0 为所有医生
        if (callChang("0", "setting")) {
            alert("更新成功,所有服务终端机即将更新广告位!");
        } else {
            alert("更新失败!");
        }

        <%--$.postJSON("${ctx}/updateAdvingSetting", {}, function () {--%>
        <%----%>
//        })
    }
</script>
<div id="body" style="height: 100%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <form action="${ctx}/adverting/list" method="post" class="form-inline">
                    <div class="span12">
                        <div class="content-box">
                            <div class="content-box-header">
                                <i class="icon-list"></i> 广告列表
                            </div>
                            <div class="row-fluid">
                                <div class="span12">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>名称</th>
                                            <th>类型</th>
                                            <th>位置</th>
                                            <th>内容</th>
                                            <th>地址</th>
                                            <th>排序</th>
                                            <th>状态</th>
                                            <th>创建人</th>
                                            <th>创建时间</th>
                                            <th>剩余天数</th>
                                            <th>
                                                <button class="btn btn-info btn-small"
                                                        onclick="goToAdd()" type="button">
                                                    新建
                                                </button>
                                                <button class="btn btn-info btn-small"
                                                        onclick="updateAdvingSetting()" type="button">
                                                    更新
                                                </button>
                                                <input type="hidden" name="pageNo" id="pageNo"/>
                                                <button type="submit" class="btn btn-info" id="btn_search"
                                                        style="display: none">提交
                                                </button>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${page.content}" var="n">
                                            <tr>
                                                <td>${n.name}</td>
                                                <td>${typeMap[n.type]}</td>
                                                <td>${positionMap[n.position]}</td>
                                                <td>${n.content}</td>
                                                <td>${n.url}</td>
                                                <td>${n.orderNo}</td>
                                                <td><c:if test="${n.status == 'Normal'}">正常</c:if><c:if
                                                        test="${n.status == 'Archived'}">无效</c:if></td>
                                                <td>${n.createBy}</td>
                                                <td><fmt:formatDate value="${n.createOn}"
                                                                    pattern="yyyy/MM/dd HH:mm"/></td>
                                                <td>${n.remainDay}</td>
                                                <td><a href="${ctx}/adverting/edit/${n.id}">查看/修改</a></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <td colspan="10" style="text-align: right;margin-right: 10px">
                                                第&nbsp;${page.number + 1}&nbsp;页/共&nbsp;${page.totalPages}&nbsp;页--总数据${page.totalElements}条
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="10">
                                                <div id="pagination"></div>
                                            </td>
                                        </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <footer class="footer">
        <h5 class="transparent-text center">
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备15089657号-2</a>
            </div>
        </h5>
    </footer>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap-paginator.js"></script>
<script type="text/javascript">
    var options = {
        currentPage: ${page.number + 1},
        totalPages: ${page.totalPages},
        numberOfPages: 8,
        alignment: "center",
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }
        },
        onPageClicked: function (event, originalEvent, type, page) {
            $("#pageNo").val(page);
            $("#btn_search").click();
        }
    }
    $('#pagination').bootstrapPaginator(options);

    function goToAdd() {
        window.location.href = '${ctx}/adverting/add'
    }
</script>