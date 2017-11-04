<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>药方管理</title>
    <script>

        function fn_LoadRpList(page) {
            $('#hidPage').val(page);
            $('#btnSubmit').click();
        }

        function fn_AddRp(reLibId) {
            var index = layer.open({
                type: 2,
                maxmin: true,
                title: '查看/添加药方',
                area: ['900px', '720px'],
                scrollbar: false,
                content: '/rp/toadd/' + reLibId
            });
            layer.full(index);
        }
        $(function () {
            $("#nav-manage").addClass("active");
            $('#currentPage').keydown(function (event) {
                if (event.keyCode == 13) {
                    var page = $('#currentPage').val();
                    if (isNaN(page))page = 1;
                    if (page == "")page = 1;
                    if (page < 1)page = 1;
                    var total = ${rplibPage.totalPages};
                    if (page > total)page = total;
                    fn_LoadRpList(page - 1);
                    return false;
                }
            });
        });

        function goToThisPage() {
            var page = $('#currentPage').val();
            if (isNaN(page))page = 1;
            if (page == "")page = 1;
            if (page < 1)page = 1;
            var total = ${rplibPage.totalPages};
            if (page > total)page = total;
            fn_LoadRpList(page - 1);
            return false;
        }
    </script>
    <script>
        $(function () {
            //设置偶数行和奇数行
            $("tbody>tr:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
            $("tbody>tr:even").addClass("backg2");  // 为偶数行设置样式类
        });

    </script>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/message" class="btn btn-default">短信发送</a></li>
            </c:if>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li class="active"><a href="#" class="btn btn-default">药方库</a></li>
            <li><a href="${ctx}/config/symptom" class="btn btn-default">数据整理</a></li>
            <%--<c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            </c:if>--%>
            <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            </c:if>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            </c:if>
            <div class="analysis" style="overflow: hidden; padding: 15px 10px;">
                <form id="fromRpQuery" action="/rplib" method="post" class="form-inline">
                    <input id="hidPage" type="hidden" name="page"/>

                    <div class="col-md-4 col-sm-4">
                        <div class="form-group">
                            <label for="sltCate" class="control-label">药方类别</label>
                            <select name="categoryId" class="form-control" id="sltCate">
                                <option value="">全部</option>
                                <c:forEach var="cate" items="${categories}">
                                    <option value="${cate.id}"
                                            <c:if test="${cate.id==categoryId}">selected</c:if>>${cate.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4">
                        <div class="form-group">
                            <label for="sltType" class="control-label">药方类型</label>
                            <select name="medicineType" class="form-control" id="sltType">
                                <option value="">全部</option>
                                <c:forEach var="t" items="${medicineTypes}">
                                    <option value="${t.key}"
                                            <c:if test="${t.key==medicineType}">selected</c:if> >${t.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4">
                        <div class="form-group" style="margin-left: -60px; padding: 0;">
                            <label for="txtName" class="control-label">药方名称</label>
                            <input type="text" name="name" value="${name}" class="form-control"
                                   id="txtName"/>
                        </div>
                        <button id="btnSubmit" type="submit" class="btn pull-right">搜索</button>
                    </div>

                </form>
            </div>

        </ul>


        <table class="rp-table" width="100%">
            <colgroup width="8%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="36%"></colgroup>
            <colgroup width="21%"></colgroup>
            <thead>
            <tr style="height: 64px;">
                <th>序号
                </td>
                <th>药方类别</th>
                <th>药方类型</th>
                <th>药方名称</th>
                <th>药方详情</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${rplibPage.content}" var="rp" varStatus="status">
                <tr>
                    <td class="text-center">${status.count+(rplibPage.number * rplibPage.getSize())}</td>
                    <td>${rp.categoryName}</td>
                    <td>${medicineTypes[rp.medicineType]}</td>
                    <td><c:out value='${rp.name}'/></td>
                    <td>
                        <div class="demo"><c:out value='${rp.remark}'/></div>
                    </td>
                    <td>
                        <button onclick="fn_AddRp(${rp.id})" type="button" class="btn btn-default">查看/添加</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="text-center">
            <c:if test="${rplibPage.number > 0}">
                <button type="button" onclick="fn_LoadRpList(${rplibPage.number - 1})" class="btn btn-default"
                        style="width: 100px; height: 50px; margin-right: 15px;">上一页
                </button>
            </c:if>
            <c:if test="${rplibPage.number + 1 < rplibPage.totalPages}">
                <button type="button" onclick="fn_LoadRpList(${rplibPage.number + 1})" class="btn btn-default"
                        style="width: 100px; height: 50px;">下一页
                </button>
            </c:if>
            &nbsp;&nbsp;&nbsp;&nbsp;
            第 <input type="text" class="form-control" style="width:50px; text-align: center;" id="currentPage"
                     value="${rplibPage.number + 1}">页/共<span>${rplibPage.totalPages}</span>页
            <a href="#" onclick="javascript:goToThisPage();" class="btn btn-success">跳转</a>
        </div>
    </div>
</div>
</body>
</html>