<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <%--<link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
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
    <script type="text/javascript" src="${ctx}/assets/scripts/getPinYin.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/extend/layer.ext.js"></script>
    <script type="text/javascript" src="${ctx}/assets/scripts/hmp.js"></script>--%>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <script type="text/javascript">
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnSubmit').click(function () {
                var data = $.map($('input[name="companyIds"]:checked'), function (n) {
                    debugger
                    return {'id': $(n).val(), 'name': $(n).attr('alt')}
                });
                if (data && data.length > 0) {
                    parent.fn_AddCompanies(data);
                    parent.layer.close(index);
                }
            });
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
            $('#chkAll').click(function () {
                $('input[name="companyIds"]').prop("checked", $(this).is(":checked"));
            });

            $('#btnSaveTxt').click(function () {
                layer.prompt({title: '添加药厂'}, function (value) {
                    $.postJSON("${ctx}/fragment/saveCompanies",
                            {
                                name: value,
                                code: pinyin.getCamelChars(value)
                            },
                            function (ret) {
                                if (ret.success) {
                                    window.location.reload();
                                    parent.layer.msg("增加成功!");
                                    layer.close(index);
                                }
                            });
                });
            });

        })
        function fn_LoadCompany(page) {
            $('#hidPage').val(page);
            $('#btnQuery').click();
        }

    </script>
</head>
<body style="background-color: #fff;">
<div style="margin: 20px">
    <form action="/adv/fragment/companies" method="post" class="form-horizontal">
        <input id="hidPage" type="hidden" name="page"/>
        <div class="form-group">
            <label for="txtName" class="col-xs-2 col-sm-2 control-label">药厂名称</label>

            <div class="col-xs-5 col-sm-5">
                <input name="name" type="text" class="form-control" id="txtName" value="${name}" placeholder="药厂名称">
            </div>
            <div class="col-xs-2 col-sm-2">
                <button id="btnQuery" type="submit" class="btn btn-success"><i class="fa fa-search"></i> 查询</button>
            </div>
        </div>
    </form>
    <label><input type="checkbox" id="chkAll"/> 全选</label>
    <c:forEach var="co" items="${page.content}">
        <div class="checkbox">
            <label for="companyId${co.id}"><input id="companyId${co.id}" name="companyIds" type="checkbox"
                                                  value="${co.id}" alt="${co.name}">${co.name}</label>
        </div>
    </c:forEach>
    <div class="text-center">
        <c:if test="${page.number > 0}">
            <button onclick="fn_LoadCompany(${page.number - 1})" class="btn btn-success"><i
                    class="fa fa-chevron-left"></i> 上一页
            </button>
        </c:if>
        <c:if test="${page.number + 1 < page.totalPages}">
            <button onclick="fn_LoadCompany(${page.number + 1})" class="btn btn-success">下一页 <i
                    class="fa fa-chevron-right"></i></button>
        </c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;
        第&nbsp;${page.number + 1}&nbsp;页/共&nbsp;${page.totalPages}&nbsp;页
    </div>
    <br/>
    <div style="text-align: center;">
        <button id="btnSaveTxt" class="btn btn-success" type="button"><i class="fa fa-plus"></i> 添加药厂</button>
        <button id="btnSubmit" class="btn btn-success" type="button"><i class="fa fa-check"></i> 添加选择药厂</button>
        <button id="btnClose" class="btn btn-default" type="button"><i class="fa fa-times"></i> 取消</button>
    </div>
</div>
</body>
</html>