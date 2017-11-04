<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/28 0028
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>

    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css"
          rel="stylesheet"/>
    <link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>
    <script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script>
        $(function () {
            $("#nav-pharmacy").addClass("active");

        })
        function fn_LoadRpList(page) {
            $('#hidPage').val(page);
            $('#btnSubmit').click();
        }
        function goToThisPage() {
            var page = $('#currentPage').val();
            if (isNaN(page))page = 1;
            if (page == "")page = 1;
            if (page < 1)page = 1;
            var total = ${tablePage.totalPages};
            if (page > total)page = total;
            fn_LoadRpList(page - 1);
            return false;
        }


        function openAdd() {
            layer.open({
                type: 2,
                title: ['增加表记录 ', 'font-weight: bold'],
                area: ['720px', '550px'],
                scrollbar: false,
                content: '${ctx}/table/add',
                end: function () {


                }
            });
        }


    </script>
</head>
<body>
<div class="container chargeManage">
    <form action="${ctx}/table/list" method="post">
        <input id="hidPage" type="hidden" name="page"/>
        <input id="status" type="hidden" name="status" value=""/>
        <div class="adv-container">
            <div class="text-right">

                <span>表名：</span><input type="text" class="form-control"
                                       style="display: inline-block; width: 20%; margin-right: 20px;"
                                       name="name"
                                       value="${name}"
                                       placeholder="请输入表名">
                <button type="submit" id="btnSubmit" class="btn btn-success" style="margin-right: 20px;">搜索</button>
                <button type="button" id="add" onclick="openAdd()" class="btn btn-warning" style="margin-right: 20px;">
                    新增
                </button>
            </div>
            <table class="adv-table" width="100%">
                <colgroup width="7%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="7%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="17%"></colgroup>
                <colgroup width="15%"></colgroup>
                <%--<colgroup width="8%"></colgroup>--%>
                <colgroup width="15%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>表名</th>
                    <th>操作</th>
                    <th>列名</th>
                    <th>类型</th>
                    <th>新列名</th>
                    <th>操作人</th>
                    <th>操作时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${tablePage.content}" varStatus="status" var="one">
                    <tr>
                        <td>
                                ${status.count +(tablePage.number * tablePage.getSize())}
                        </td>
                        <td>${one.tableName}</td>
                        <td>${one.type}</td>
                        <td>${one.columnName}</td>
                        <td>${one.columnType}</td>
                        <td> ${one.columnNameNewName}</td>
                        <td> ${one.createBy}</td>
                        <td>
                            <fmt:formatDate value="${one.createDate}" pattern="yyyy/MM/dd HH:mm"/>
                        </td>

                        <td>
                            <button class="btn btn-default" type="button" onclick="pharmacyDetails(${one.id})">已经同步
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </form>
    <div class="text-center" style="margin-bottom: 20px;">
        <c:if test="${tablePage.number > 0}">
            <button type="button" onclick="fn_LoadRpList(${tablePage.number - 1})"
                    class="btn btn-default" style="width: 100px; height: 50px; margin-right: 15px;">上一页
            </button>
        </c:if>
        <c:if test="${tablePage.number + 1 < tablePage.totalPages}">
            <button type="button" onclick="fn_LoadRpList(${tablePage.number + 1})"
                    class="btn btn-default" style="width: 100px; height: 50px;">下一页
            </button>
        </c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;
        第 <input type="text" class="form-control" style="width:50px; text-align: center; display: inline-block;"
                 id="currentPage" value="${tablePage.number + 1}">页/共<span>${tablePage.totalPages}</span>页
        <a href="#" class="btn btn-success" onclick="javascript:goToThisPage();">跳转 </a>
    </div>
</div>
</body>
</html>
