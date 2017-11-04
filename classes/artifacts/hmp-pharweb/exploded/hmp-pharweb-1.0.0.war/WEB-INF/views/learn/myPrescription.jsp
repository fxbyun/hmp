<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/11
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<html>
<head>
    <title>我的药方</title>
</head>
<body>
<div>
    <div style="margin-top: 1rem;">
        <div>
            <form id="presForm" action="${ctx}/learn/templatePrescription" method="post">
                <input type="hidden" id="pageInput" name="page" value="${rpPage.number}">
                <p class="text-center">
                    <span>药方类别</span>
                    <select class="form-control" name="categoryId" style="width: 60%; display: inline-block;">
                        <option value="">全部</option>

                        <c:forEach var="cate" items="${categories}">
                            <option value="${cate.id}"
                                    <c:if test="${cate.id==categoryId}">selected</c:if>>${cate.name}</option>
                        </c:forEach>
                    </select>
                </p>
                <p class="text-center">
                    <span>药方类型</span>
                    <select class="form-control" name="medicineType" style="width: 60%; display: inline-block;">
                        <option value="">全部</option>
                        <c:forEach var="t" items="${medicineTypes}">
                            <option value="${t.key}"
                                    <c:if test="${t.key==medicineType}">selected</c:if> >${t.value}</option>
                        </c:forEach>
                    </select>
                </p>
                <p class="text-center">
                    <span>药方名称</span>
                    <input type="text" name="name" value="${name}" class="form-control" placeholder="默认全部"
                           style="width: 60%; display: inline-block;">
                </p>

                <p class="text-center btn-boxs">
                    <button type="submit" onclick="javascript:$('#pageInput').val('0')" class="btn btn-default">搜索
                    </button>
                </p>
            </form>
        </div>
        <div style="margin-top: 2rem;">
            <table class="tem-medicine" width="100%">
                <colgroup style="width: 20%"></colgroup>
                <colgroup style="width: 30%"></colgroup>
                <colgroup style="width: 50%"></colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>药方名称</th>
                    <th>药方详情</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="tmpNum" value="${rpPage.number * rpPage.size +1}"></c:set>
                <c:forEach items="${rpPage.content}" var="rp" varStatus="status">
                    <tr onclick="fn_detailShow('${rp.id}')">
                        <td>${tmpNum}、</td>
                        <td>${rp.name}</td>
                        <td>
                            <div class="demo">${rp.remark}</div>
                        </td>
                    </tr>
                    <c:set var="tmpNum" value="${tmpNum+1}"></c:set>
                </c:forEach>

                </tbody>
            </table>

            <%-- 按钮 --%>
            <div class="text-center pre-btn" style="margin-top: 2rem;">
                <c:choose>
                    <c:when test="${rpPage.number > 0}">
                        <button type="button" class="btn btn-success" onclick="toPage('${rpPage.number-1}')">上一页
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-default">上一页</button>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${rpPage.number + 1 < rpPage.totalPages}">
                        <button type="button" class="btn btn-success" onclick="toPage('${rpPage.number+1}')">下一页
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-default">下一页</button>
                    </c:otherwise>
                </c:choose>
                <a href="${ctx}/learn/index">首页</a>
            </div>
            <div class="text-center btn-boxs" style="margin-top: 2rem;">
                <span style="padding-right: 0.5rem;">第</span>
                <input type="text" class="form-control text-center" style="width: 15%; display: inline-block;"
                       value="${rpPage.number + 1}" id="toPageInput">
                <span style="padding:0 0.5rem;">页/共${rpPage.totalPages}页</span>
                <button type="button" class="btn btn-default" onclick="toPage('this','sel')">跳转</button>

            </div>
            <%-- 按钮 END --%>

        </div>
    </div>
</div>

<script>
    $(function () {
        //设置偶数行和奇数行
        $("tbody>tr:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
        $("tbody>tr:even").addClass("backg2");  // 为偶数行设置样式类
    });
    function fn_detailShow(id) {
        window.location.href = "${ctx}/learn/myDetailPrescription/" + id;
    }
    function toPage(num, toNum) {
        if (toNum == "sel") {
            $("#pageInput").val(parseInt($("#toPageInput").val()) - 1);
            $("#presForm").submit();
        } else {
            $("#pageInput").val(num);
            $("#presForm").submit();
        }

    }
</script>
<script>
    $(function () {
        $("#myPre").addClass("active");
    });
</script>
</body>
</html>
