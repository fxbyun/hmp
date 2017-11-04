<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/15
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <hmp:HmpLoadFile url="/assets/scripts/getPinYin.js" type="js" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script>
        $(function () {
            //设置偶数行和奇数行
            $("tbody>tr:odd").addClass("backg");   //为奇数行设置样式(添加样式类)
            $("tbody>tr:even").addClass("backg2");  // 为偶数行设置样式类
        });

    </script>
    <style>
        .rp-table tbody tr, .rp-table tbody tr td {
            height: 50px;
        }

        .rp-table thead tr th:first-child, table.rp-table tr td:first-child {
            padding-left: 0;
        }

        i {
            padding-right: 5px;
        }
    </style>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
        });
    </script>
    <script type="application/javascript">
        function fn_EditCategory(id) {
            if (id) {
                layer.open({
                    type: 2,
                    title: '修改药方类别',
                    area: ['500px', '220px'],
                    scrollbar: false,
                    content: '/rpCate/edit/' + id,
                   end: fn_LoadCategories ,
                });
            }
        }
        function fn_AddCategory() {
            layer.open({
                type: 2,
                title: '新增药方类别',
                area: ['500px', '220px'],
                scrollbar: false,
                content: '/rpCate/add/',
                end: fn_LoadCategories
            });
        }
        function fn_LoadCategories() {
            var sel = parent.$('#sltCategory').val();
            $.getJSON("/rpCate", function (data) {
                parent.$('#sltCategory').empty();
                $.each(data, function (i, n) {
                    parent.$('#sltCategory').append('<option value="'+ n.id+'">'+ n.name+'</optioin>');
                })
                parent.$('#sltCategory').val(sel);
            })
            window.location.reload()
        }

        function delCategory(id){
            $.getJSON("/rpCate/manage/del/"+ id,function(data){
                window.location.reload()
            })
        }

    </script>
</head>
<body>

<div style="margin: 20px;">

    <div class="container">
        <table class="rp-table" width="100%">
            <colgroup width="50%"></colgroup>
            <colgroup width="50%"></colgroup>
            <thead>
            <tr>
                <th class="text-center">药方类别</th>
                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="entry" items="${categories}">
                <tr>
                    <td>${entry.name}</td>
                    <td>
                        <button class="btn btn-default" onclick="fn_EditCategory(${entry.id})" style="margin-right: 5px; width: 80px;">编辑</button>
                        <button class="btn btn-default" onclick="delCategory(${entry.id})"><i class="fa fa-trash-o"></i>删除</button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="text-center">
        <button class="btn btn-success" onclick="fn_AddCategory()"><i class="fa fa-plus"></i>新增类别</button>
        <button class="btn btn-default" id="btnClose" style="width: 100px; margin-left: 10px;">关闭</button>
    </div>
</div>

<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
</body>
</html>
