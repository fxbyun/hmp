<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>药方管理</title>
    <script>

        function fn_LoadDeList(page) {
            window.location.href = "/dm?page=" + page;
        }

        function fn_SelectMedicines() {
            layer.open({
                type: 2,
                title: '选择药品',
                area: ['720px', '680px'],
                scrollbar: false,
                content: '/fragment/otherMedicines',
                end: function () {
                    window.location.reload();
                }
            });
        }
        function fn_EditMedicine(medicineId, type) {
            layer.open({
                type: 2,
                title: '修改药品',
                area: ['720px', '600px'],
                scrollbar: false,
                content: '/medicine/edit/' + medicineId,
                end: function () {
                    window.location.reload();
                }
            });
        }

        function fn_RemoveDe(deId) {
            layer.confirm("信息删除后不可恢复，确认移除当前药品？", function () {
                $.postJSON(String.format("/dm/dmlete/{0}", deId), function (res) {
                    if (res.success) layer.msg("药品已移除");
                    else layer.alert(res.msg);
                    window.location.reload();
                })
            });
        }

        $(function () {
            $("#nav-manage").addClass("active");
            $('#currentPage').keydown(function (event) {
                if (event.keyCode == 13) {
                    var page = $('#currentPage').val();
                    if(isNaN(page))page = 1;
                    if(page == "")page = 1;
                    if(page < 1)page = 1;
                    var total = ${medicinePage.totalPages};
                    if(page > total )page = total;
                    window.location.href = "${ctx}/dm?page=" + (page-1);
                }
            });
        });

        function goToThisPage(){
            var page = $('#currentPage').val();
            if (isNaN(page))page = 1;
            if (page == "")page = 1;
            if (page < 1)page = 1;
            var total = ${medicinePage.totalPages};
            if (page > total)page = total;
            window.location.href = "${ctx}/dm?page=" + (page - 1);
        }
    </script>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage">用药分析</a></li>
            <li><a href="${ctx}/infro">信息设置</a></li>
            <li class="active"><a>常用药品</a></li>
            <li><a href="${ctx}/rp">我的药方</a></li>
            <li><a href="${ctx}/rplib">药方库</a></li>
            <li><a href="${ctx}/config/symptom">数据整理</a></li>
        </ul>

        <table class="table">
            <colgroup width="5%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="10%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="35%"></colgroup>
            <colgroup width="20%"></colgroup>
            <thead>
            <tr>
                <th>序号</th>
                <th>药品名称</th>
                <th>药品类型</th>
                <th>默认药厂</th>
                <th>药品描述</th>
                <th>
                    <button onclick="fn_SelectMedicines();" type="button" class="btn btn-success"><i class="fa fa-check"></i> 选择药品</button>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${medicinePage.content}" var="med" varStatus="status">
                <tr>
                    <td class="text-center">${status.count}</td>
                    <td>${med.name}</td>
                    <td>${medicineTypes[med.type]}</td>
                    <td>${med.defaultCompanyName}</td>
                    <td>${med.specification}</td>
                    <td>
                        <button onclick="fn_EditMedicine(${med.id},'${med.type}')" type="button" class="btn btn-info btn-sm"><i class="fa fa-edit"></i> 查看/修改</button>
                        <button onclick="fn_RemoveDe(${med.id})" type="button" class="btn btn-danger btn-sm"><i class="fa fa-times"></i> 移除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="text-center">
            <c:if test="${medicinePage.number > 0}">
                <a href="/dm?page=${medicinePage.number - 1}" class="btn btn-info"><i class="fa fa-chevron-left"></i> 上一页</a>
            </c:if>
            <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
                <a href="/dm?page=${medicinePage.number + 1}" class="btn btn-info">下一页 <i class="fa fa-chevron-right"></i></a>
            </c:if>
            &nbsp;&nbsp;&nbsp;&nbsp;
            第 <input type="text" class="form-control" style="width:50px" id="currentPage" value="${medicinePage.number + 1}">页/共<span>${medicinePage.totalPages}</span>页
            <a href="#" onclick="javascript:goToThisPage();" class="btn btn-info">跳转 <i class="fa fa-chevron-right"></i></a>
        </div>
    </div>
</div>
</body>
</html>