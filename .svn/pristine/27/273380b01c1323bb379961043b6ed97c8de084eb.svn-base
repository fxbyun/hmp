<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>我的消息</title>
    <script>
        function fn_LoadPage(page) {
            window.location.href = "${ctx}/notice/" + page;
        }
        $(function () {
            $('#currentPage').keydown(function (event) {
                if (event.keyCode == 13) {
                    var page = $('#currentPage').val();
                    if (isNaN(page)) page = 1;
                    if (page == "") page = 1;
                    if (page < 1) page = 1;
                    var total = ${page.totalPages};
                    if (page > total) page = total;
                    fn_LoadPage(page);
                    return false;
                }
            });
        });
        function fn_ChangeStatus(id, status) {
            if (status == 'Normal') {
                var url = '/notice/item/' + id + "?dt=" + jQuery.now();
                $.getJSON(url, function (result) {
                    if (result.success) {
                        $("#status" + id).removeClass("fa-envelope");
                        $("#status" + id).addClass("fa-envelope-o");
                        if (result.data != null) {
                            $("#onReadCount").html(result.data);
                            $("#itemCount").html(result.data);
                        }
                    }
                });
            }
        }
    </script>
</head>
<body class="my-infrotion">
<div class="container">
    <div class="row infro-content">
        <div class="col-md-2 col-sm-2 my-infro-left">
            <h4 class="text-center">我的消息</h4>
            <ul>
                <%--<li class="active"><a href="javascript:void(0)">拼单采购<span>( 0 )</span></a></li>--%>
                <%--<li><a href="javascript:void(0)">财富消息<span>( 2 )</span></a></li>--%>
                <li class="active"><a href="${ctx}/notice">系统消息(<span id="onReadCount">${noReadCount}</span>)</a></li>
            </ul>
        </div>
        <div class="col-md-10 col-sm-10 my-infro-right">
            <div class="finish">
                <c:forEach items="${page.content}" var="item">
                    <div class="action-now-content">
                        <div class="action-item">
                            <h3 onclick="fn_ChangeStatus(${item.id},'${item.status}')" href="#content${item.id}"
                                role="button" data-toggle="collapse" aria-expanded="true"
                                aria-controls="content${item.id}">
                                <c:if test="${item.status == 'Normal'}">
                                    <i id="status${item.id}" class="fa fa-envelope"></i>
                                </c:if>
                                <c:if test="${item.status == 'Readed'}">
                                    <i id="status${item.id}" class="fa fa-envelope-o"></i>
                                </c:if>
                                    ${item.notice.subject}
                                <span class="pull-right"><fmt:formatDate value="${item.notice.createOn}"
                                                                         pattern="yyyy/MM/dd HH:mm"/></span>
                            </h3>
                            <div id="content${item.id}" class="panel-collapse collapse " role="tabpanel"
                                 aria-labelledby="headingone">
                                <p>${item.notice.content}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="fenye">
                <div class="form-group pull-right text-right">
                    第 <input type="text" class="form-control" id="currentPage"
                             value="${page.number + 1}">页/共<span>${page.totalPages}</span>页
                </div>
            </div>
            <div class="fenye-buttom text-center">
                <c:choose>
                    <c:when test="${page.number > 0}">
                        <button type="button" onclick="fn_LoadPage(${page.number - 1})" class="btn btn-info"><i
                                class="fa fa-chevron-left"></i> 上一页
                        </button>
                    </c:when>
                    <c:when test="${page.number + 1 < page.totalPages}">
                        <button type="button" onclick="fn_LoadPage(${page.number + 1})" class="btn btn-info"><i
                                class="fa fa-chevron-right"></i> 下一页
                        </button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
</html>