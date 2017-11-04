<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/hmp.js" type="js"/>
    <script type="text/javascript">
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnSubmit').click(function () {
                var data = $.map($('input[name="chkMedicine"]:checked'), function (n) {
                    return $(n).val()
                });
                if (data && data.length > 0) {
                    $.postJSON('/dm/add', {'medicineIds': data}, function (result) {
                        if (result.success) {
                            //fn_LoadMedicine(0);
                            layer.msg("药品成功添加");
                        }
                    })
                }
            });
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
            $('#chkAll').click(function () {
                $('input[name="chkMedicine"]').prop("checked", $(this).is(":checked"));
            });
        });

        function fn_LoadMedicine(page) {
            $('#hidPage').val(page);
            $('#btnQuery').click();
        }
        function fn_AddMedicine(type) {
            layer.open({
                type: 2,
                title: '添加药品',
                area: ['720px', '600px'],
                scrollbar: false,
                content: '/medicine/add?type=' + type,
                end: function () {
                    parent.location.reload();
                }
            });
        }
    </script>
</head>
<body>
<div style="margin: 20px">
    <form action="/fragment/otherMedicines" method="get" class="form-horizontal">
        <input id="hidPage" type="hidden" name="page"/>

        <div class="form-group">
            <label for="txtName" class="col-xs-2 col-sm-2 control-label">药品名称</label>

            <div class="col-xs-5 col-sm-5">
                <input name="name" type="text" class="form-control" id="txtName" value="${name}" placeholder="药品名称">
            </div>
            <div class="col-xs-2 col-sm-2">
                <button id="btnQuery" type="submit" class="btn btn-primary"><i class="fa fa-search"></i> 查询</button>
            </div>
            <div class="col-xs-2 col-sm-2">
                <button id="btnAdd" type="button" class="btn btn-primary" onclick="fn_AddMedicine('Western');">添加新药品</button>
            </div>
        </div>
    </form>

    <table class="table">
        <colgroup width="10%"></colgroup>
        <colgroup width="25%"></colgroup>
        <colgroup width="25%"></colgroup>
        <colgroup width="40%"></colgroup>
        <%--<colgroup width="35%"></colgroup>--%>
        <thead>
        <tr>
            <th><input type="checkbox" id="chkAll"/></th>
            <th>药品名称</th>
            <th>药品类型</th>
            <th>默认药厂</th>
            <%--<th>药品描述</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${medicinePage.content}" var="med" varStatus="status">
            <tr>
                <td><input type="checkbox" name="chkMedicine" value="${med.id}"/></td>
                <td>${med.name}</td>
                <td>${medicineTypes[med.type]}</td>
                <td>${med.defaultCompanyName}</td>
                    <%--<td style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis;">${med.specification}</td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-center">
        <c:if test="${medicinePage.number > 0}">
            <butotn onclick="fn_LoadMedicine(${medicinePage.number - 1})" class="btn btn-info"><i class="fa fa-chevron-left"></i> 上一页</butotn>
        </c:if>
        <c:if test="${medicinePage.number + 1 < medicinePage.totalPages}">
            <button onclick="fn_LoadMedicine(${medicinePage.number + 1})" class="btn btn-info">下一页 <i class="fa fa-chevron-right"></i></button>
        </c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;
        第&nbsp;${medicinePage.number + 1}&nbsp;页/共&nbsp;${medicinePage.totalPages}&nbsp;页
    </div>
    <br />
    <button id="btnSubmit" class="btn btn-success" type="button"><i class="fa fa-check"></i> 添加选择药品</button>
    <button id="btnClose" class="btn btn-default" type="button"><i class="fa fa-times"></i> 取消</button>
</div>
</body>
</html>