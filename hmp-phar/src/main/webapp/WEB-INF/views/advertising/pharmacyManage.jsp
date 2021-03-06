<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>药房管理</title>
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <link href="${ctx}/assets/toastr/toastr.css" type="text/css" rel="stylesheet">
    <script src="${ctx}/assets/toastr/toastr.js" type="text/javascript"></script>
    <script src="${ctx}/assets/toastr/toastr.ext.js" type="text/javascript"></script>


    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/datetimepicker/jquery.datetimepicker.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"
                     type="js"/>


    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/base.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/print.lodop/LodopFuncs.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <script type="application/javascript" src="http://localhost:8000/CLodopfuncs.js?priority=1"></script>
    <object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <object id="LODOP_EM" TYPE="application/x-print-lodop" width=0 height=0
            style="position:absolute;left:0px;top:-10px;"></object>
    <script>
        $(function () {
            $("#nav-pharmacy").addClass("active");
        })

        function fn_LoadRpList(page) {
            $("#hidPage").val(page);
            $("#fromSubmit").submit();
        }

        function goToThisPage() {
            if (isNaN(page))page = 1;
            if (page == "")page = 1;
            if (page < 1)page = 1;
            var currentPage = $("#currentPage").val();
            $("#hidPage").val(currentPage - 1);
            $("#fromSubmit").submit();
        }

    </script>
</head>
<body>
<%@ include file="/WEB-INF/layouts/advheader.jsp" %>
<div class="container chargeManage">
    <ul class="navigation">
        <li class="active"><a href="${ctx}/adv/pharmacyManage" class="btn btn-default">待发药列表</a></li>
        <li><a href="${ctx}/adv/pharmacyManage?status=Have_Dispensing" class="btn btn-default">已发药列表</a></li>
        <li><a href="${ctx}/adv/pharmacyManage?status=Have_Dispensing_Back" class="btn btn-default">已退药列表</a></li>
    </ul>
    <form action="${ctx}/adv/pharmacyManage" method="post" id="fromSubmit">
        <input id="hidPage" type="hidden" name="page"/>
        <input id="status" type="hidden" name="status" value=""/>
        <div class="adv-container">
            <div class="text-right">

                <span>刷卡：</span><input type="text" class="form-control"
                                       style="display: inline-block; width: 20%; margin-right: 20px;"
                                       name="cardPwd"
                                       value="${cardPwd}"
                                       placeholder="请输入患者的健康卡">
                <span>姓名：</span><input type="text" class="form-control"
                                       style="display: inline-block; width: 20%; margin-right: 20px;"
                                       name="name"
                                       value="${name}"
                                       placeholder="请输入患者姓名">
                <span>电话：</span><input type="text" class="form-control" style="display: inline-block; width: 20%;"
                                       name="phone"
                                       value="${phone}"
                                       placeholder="请输入患者电话号码">
                <button type="submit" class="btn btn-success" style="margin-right: 20px;">搜索</button>
            </div>
            <table class="adv-table" width="100%">
                <colgroup width="6%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="6%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="12%"></colgroup>
                <colgroup width="20%"></colgroup>
                <%--<colgroup width="8%"></colgroup>--%>
                <colgroup width="15%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="16%"></colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>电话号码</th>
                    <th>初步诊断</th>
                    <th>就诊时间</th>
                    <th>主治医生</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${emrListPage.content}" varStatus="status" var="one">
                    <tr>
                        <td>
                                ${status.count +(emrListPage.number * emrListPage.getSize())}
                        </td>
                        <td>${one.patientName}</td>
                        <td>${genderMap[one.patient.gender]}</td>
                        <td>${one.patient.getAge()}</td>
                        <td>${one.patient.mobile}</td>
                        <td>
                            <c:forEach items="${one.diagnosisList}" var="oneDiagnosis">
                                <span class="text-num" title="${oneDiagnosis.name}">${oneDiagnosis.name}</span>
                            </c:forEach>
                        </td>

                        <td>
                            <fmt:formatDate value="${one.createOn}" pattern="yyyy/MM/dd HH:mm"/>
                        </td>
                        <td> ${one.doctor.name}</td>
                        <td>
                            <button class="btn btn-default" type="button" onclick="pharmacyDetails(${one.id})">发药
                            </button>
                            <div class="dropdown" style="width: 102px; display: inline-block;">
                                <a href="#" class="dropdown-toggle btn btn-success" data-toggle="dropdown"
                                   style="width: 100%;">
                                    打印选择
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#" onclick="javascript:printA5or80Cfq('A5打印','${one.id}')">打印处方笺</a>
                                    </li>
                                    <li><a href="#" onclick="javascript:printA5oOr80Jzd('A5打印','${one.id}')">打印就诊单</a>
                                    </li>
                                    <li><a href="#" onclick="printByType('therapy','${one.id}')">打印理疗单</a></li>
                                    <li><a href="#" onclick="printByType('examLab','${one.id}')">打印检查单</a></li>
                                    <li><a href="#" onclick="printByType('fuJia','${one.id}')">打印附加费</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </form>
    <div class="text-center" style="margin-bottom: 20px;">
        <c:if test="${emrListPage.number > 0}">
            <button type="button" onclick="fn_LoadRpList(${emrListPage.number - 1})"
                    class="btn btn-default" style="width: 100px; height: 50px; margin-right: 15px;">上一页
            </button>
        </c:if>
        <c:if test="${emrListPage.number + 1 < emrListPage.totalPages}">
            <button type="button" onclick="fn_LoadRpList(${emrListPage.number + 1})"
                    class="btn btn-default" style="width: 100px; height: 50px;">下一页
            </button>
        </c:if>
        &nbsp;&nbsp;&nbsp;&nbsp;
        第 <input type="text" class="form-control" style="width:50px; text-align: center; display: inline-block;"
                 id="currentPage" value="${emrListPage.number + 1}">页/共<span>${emrListPage.totalPages}</span>页
        <a href="#" class="btn btn-success" onclick="javascript:goToThisPage();">跳转 </a>
    </div>
</div>
<script type="text/javascript">
    //打印处方签
    function printA5or80Cfq(type, emrId) {
        var printModel = '${doctor.printModel}';

        if (type == "A5打印") {
            if (printModel != "预览打印") {
                YJZ_Printer.printUrl('/pub/printRpA5/{0}'.format(emrId));
//                判断是否还需要独立打印
                $.post("/pub/isNeedAlonePrint", {emrId: emrId}, function (ret) {
                    if (ret.success) {
                        YJZ_Printer.printUrl('/pub/printRpA5/{0}?type=alone'.format(emrId));
                        Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                    }
                });
            } else {
                var idex = layer.open({
                    title: "预览打印",
                    content: ("${ctx}/pub/printRpA5/{0}?printModel={1}".format(emrId, printModel)),
                    area: ['520px', '600px'],
                    btn: ["关闭"],
                    yes: function () {
                        layer.close(idex);
                    },
                    scrollbar: false,
                    type: 2,
                    end: function () {
                        //                判断是否还需要独立打印
                        $.postJSON("${ctx}/pub/isNeedAlonePrint", {emrId: emrId}, function (ret) {
                            if (ret.success) {
                                var idexAlon = layer.open({
                                    title: "预览打印",
                                    content: ("${ctx}/pub/printRpA5/{0}?printModel={1}&type=alone".format(emrId, printModel)),
                                    area: ['520px', '600px'],
                                    btn: ["关闭"],
                                    yes: function () {
                                        layer.close(idexAlon);
                                    },
                                    scrollbar: false,
                                    type: 2,
                                    end: function () {
                                        Alert.success("独立打印执行完毕!");
                                    }
                                });
                                Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                            }
                        });
                    }
                });

            }
        } else {
            YJZ_Printer.print80Url('../pub/printRp/{0}'.format(emrId));
            $.postJSON("/pub/isNeedAlonePrint", {emrId: emrId}, function (ret) {
                if (ret.success) {
                    YJZ_Printer.print80Url('../pub/printRp/{0}?type=alone'.format(emrId));
                    Alert.success("打印成功,本次已启用分页打印!请勿重复点击,请稍后!");
                }
            });
        }
    }
    //打印就诊单
    function printA5oOr80Jzd(type, emrId) {
        if (type == "A5打印") {
            YJZ_Printer.printUrl('/pub/printDiagnosisA5/' + emrId);//就诊单默认还是80打印机
        } else {
            YJZ_Printer.print80Url('/pub/printDiagnosis/' + emrId);
        }
    }

    //处方
    function printA5ChuFang(type, emrId) {
        if (type == "A5打印") {
            YJZ_Printer.printUrl('/pub/printRpA5/' + emrId);//就诊单默认还是80打印机
        } else {
            YJZ_Printer.print80Url('/pub/printDiagnosis/' + emrId);
        }
    }


    /*中医理疗打印*/
    function printTherapyA5(url, emrId) {
        var index = layer.open({
            title: "预览打印",
            content: (url.format(emrId)),
            area: ['1140px', '600px'],
            btn: ["关闭"],
            yes: function () {
                layer.close(index);
            },
            scrollbar: false,
            type: 2

        });


    }

    /*------------------改为快速打印的方法--------------------*/
    function fastPrintA5(url, emrId) {
        YJZ_Printer.printUrl(url.format(emrId), '', 'infeed');
    }


    function printByType(type, emrId) {
        //打印下拉选择框
        var print_url = "";
        switch (type) {
            case "therapy":
                print_url += "/pub/printPhyTable?emrId={0}";
                //printTherapyA5(print_url,emrId);
                fastPrintA5(print_url, emrId)
                break;
            case "examLab":
                print_url += "/pub/printItemsTable?emrId={0}";
                //printTherapyA5(print_url,emrId);
                fastPrintA5(print_url, emrId)
                break
            case "fuJia":
                print_url += "/pub/printChargesTable?emrId={0}";
                fastPrintA5(print_url, emrId)
                break;
            default:
                break;
        }
    }


</script>


<%@ include file="/WEB-INF/layouts/advfooter.jsp" %>
</body>
</html>
