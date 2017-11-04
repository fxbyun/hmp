<%--
  Created by IntelliJ IDEA.
  User: teemoer@cntv.cn
  Date: 2016/7/6 0006
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css"
      rel="stylesheet"/>
<link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/assets/styles/style.css" type="text/css" rel="stylesheet">
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css"/>

<link href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<![endif]-->
<link href="${ctx}/assets/laypage/skin/laypage.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>

<html>
<head>
    <title>错误统计页面</title>
    <script type="application/javascript">
        $(function () {
            laypage({
                cont: "page11",
                pages: ${errorLogPage.getTotalPages()}, //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18
                curr: function () { //通过url获取当前页，也可以同上（pages）方式获取
                    var page = ${nowPage};
                    return page;
                }(),
                jump: function (e, first) { //触发分页后的回调
                    if (!first) { //一定要加此判断，否则初始时会无限刷新
                        window.location.href = ('/pub/errorList?page=' + (e.curr - 1));
                    }
                }
            });
        })
    </script>
    <style>
        th {
            text-align: center;
        }

        thead tr {
            height: 50px;
        }

    </style>
</head>
<body>


<div class="container" style="background: #fff; padding: 30px 0;">
    <table class="text-center" width="100%" border="1">
        <colgroup width="5%"></colgroup>
        <colgroup width="10%"></colgroup>
        <colgroup width="10%"></colgroup>
        <colgroup width="10%"></colgroup>
        <colgroup width="25%"></colgroup>
        <colgroup width="25%"></colgroup>
        <colgroup width="15%"></colgroup>
        <thead>
        <tr>
            <th>序号</th>
            <th>类型</th>
            <th>医生</th>
            <th>请求地址</th>
            <th>错误标题</th>
            <th>错误信息</th>
            <th>发生时间</th>
        </tr>
        </thead>
        <tbody id="tableBody">
        <c:forEach items="${errorLogPage.content}" var="oneUser">
            <tr>
                <td>${oneUser.id}</td>
                <td>${oneUser.type}</td>
                <td>${oneUser.doctor.name}</td>
                <td style="word-break: break-all">${oneUser.errorUrl}</td>
                <td style="word-break: break-all">${oneUser.errorTitle}</td>
                <td>
                        <textarea style="width: 100%;height: 200px;word-break: break-all" readonly>
                                ${oneUser.errorInfo}
                        </textarea>
                </td>
                <td>
                    <fmt:formatDate value="${oneUser.createTime}" pattern="yyyy/MM/dd HH:mm" type="date"/>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br/>

    <div>
        <center id="page11">


        </center>


    </div>

</div>

</body>
</html>
