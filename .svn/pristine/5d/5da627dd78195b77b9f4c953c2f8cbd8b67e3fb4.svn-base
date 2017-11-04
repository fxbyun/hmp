<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/21
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>

    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/assets/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/getPinYin.js" type="js"/>
    <script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/assets/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/select2/select2.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/hmp.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/acupuncturePoint.js" type="js"/>

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/pinyin.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/typeahead/typeahead.bundle.js" type="js"/>
    <style>
        .mes-seach span, .mes-seach input, .mes-seach select {
            float: left;
            font-weight: normal;
        }

        .mes-seach span {
            line-height: 35px;
            font-size: 16px;
            padding-right: 5px;
        }

        .mes-seach span.twitter-typeahead {
            padding: 0;
        }

        .mes-table tr input[type=checkbox] {
            line-height: 30px;
            margin-top: 0;
            margin-right: 5px;
            vertical-align: middle;
        }

        .fenye {
            margin-top: 15px;
            margin-bottom: 0;
            font-size: 14px;
        }

        .btn-success {
            background-color: #218a3f;
            border-color: #218a3f;
        }

        .tr-background {
            background-color: #dfdfdf;
        }

    </style>
    <script>
        $(function () {
            function initTableCheckbox() {
                var $thr = $('table thead tr');
                var $checkAllTh = $('<th><input style="display: none" type="checkbox" id="checkAll" name="checkAll" /></th>');
                /*将全选/反选复选框添加到表头最前，即增加一列*/
//                $thr.prepend($checkAllTh);
                /*“全选/反选”复选框*/
                var $checkAll = $thr.find('input');
                $checkAll.click(function (event) {
                    /*将所有行的选中状态设成全选框的选中状态*/
                    $tbr.find('input').prop('checked', $(this).prop('checked'));
                    /*并调整所有选中行的CSS样式*/

                    var ids = [];
                    $('table tbody tr').find('input').each(function () {
                        ids.push($(this).val())
                    })
                    if ($(this).prop('checked')) {
                        $tbr.find('input').parent().parent().addClass('tr-background');
                        $.postJSON("${ctx}/msPage/editThisPatient", {"ids": ids, "type": "add"}, function (result) {
                            if (result.success) {
                                parent.Alert.success("批量增加成功!");
                            }
                        })

                    } else {
                        $tbr.find('input').parent().parent().removeClass('tr-background');
                        $.postJSON("${ctx}/msPage/editThisPatient", {"ids": ids, "type": "del"}, function (result) {
                            if (result.success) {
                                parent.Alert.success("批量删除成功!");
                            }
                        })
                    }
                    /*阻止向上冒泡，以防再次触发点击操作*/
                    event.stopPropagation();
                });
                /*点击全选框所在单元格时也触发全选框的点击操作*/
                $checkAllTh.click(function () {
                    $(this).find('input').click();
                });
                var $tbr = $('table tbody tr');
                var $checkItemTd = $('<td><input type="checkbox" name="checkItem" /></td>');
                /*每一行都在最前面插入一个选中复选框的单元格*/
//                $tbr.prepend($checkItemTd);
//                $tbr.each(function(){
//                 $(this).prepend()
//                })
                /*点击每一行的选中复选框时*/
                $tbr.find('input').click(function (event) {
                    /*调整选中行的CSS样式*/
                    $(this).parent().parent().toggleClass('tr-background');
                    /*如果已经被选中行的行数等于表格的数据行数，将全选框设为选中状态，否则设为未选中状态*/
                    $checkAll.prop('checked', $tbr.find('input:checked').length == $tbr.length ? true : false);
                    /*阻止向上冒泡，以防再次触发点击操作*/
                    event.stopPropagation();
                });
                /*点击每一行时也触发该行的选中操作*/
                $tbr.click(function () {
                    $(this).find('input').click();
                });
            }

            initTableCheckbox();
            var examLab_list = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.whitespace,
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                remote: {
                    url: '/msPage/seachDiag?key=%QUERY',
                    wildcard: '%QUERY'
                }
            });

            $('#diagonsisNameId').typeahead({
                minLength: 1,
                highlight: true
            }, {
                name: 'examLab_list',
                limit: 10,
                async: true,
                display: 'name',
                source: examLab_list,
                templates: {
                    'empty': '<div class="tt-suggestion">无结果</div>',
                    'suggestion': function (o) {
                        console.info(o);
                        return '<div><strong>{0}</strong></div>'.format(o.name);
                    }
                }
            });
        });


        function doChang(obj) {
            if (obj.checked) {
                $.postJSON("${ctx}/msPage/editThisPatient", {"ids": $(obj).val(), "type": "add"}, function (result) {
                    if (result.success) {
                        parent.Alert.success("增加成功!");
                    }
                })

            } else {
                $.postJSON("${ctx}/msPage/editThisPatient", {"ids": $(obj).val(), "type": "del"}, function (result) {
                    if (result.success) {
                        parent.Alert.success("删除成功!");
                    }
                })
            }
        }

        function pullDataVil() {
            $.postJSON("${ctx}/msPage/GetPatientInfo", {type: "add"}, function (result) {
                if (result.length > 0) {
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.patientInfo = result;
                    var onePatient = '<span onclick="removeThisPatien(\'{0}\',\'{1}\',$(this))" class="mes-span-tag">' +
                        '{2}；<i class=""></i></span>';
                    result.forEach(function (e) {
                        if ($.inArray(e.id, parent.patientIdList) >= 0) {
                            parent.Alert.warning("患者:" + e.name + " 已经存在于收件人中!无需再次增加!");
                        } else {
                            parent.patientIdList.push(e.id);
                            parent.mobList.push(e.mobile);
                            parent.$("#addPatienList").append(onePatient.format(e.id, e.mobile, e.name));
                        }
                    })
                    parent.ageTop = $("#ageTops").val();
                    parent.ageFor = $("#ageFlooer").val();
                    parent.txtStartDate = $("#txtStartDate").val();
                    parent.txtEndDate = $("#txtEndDate").val();
                    parent.sex = $("select[name='genderSex']").val();
                    parent.diagonsisName = $("#diagonsisNameId").val();
                    parent.layer.close(index);
                } else {
                    parent.Alert.warning("请选择收件人!");
                }

            })
        }

        $(function () {
//            var ssssss = $("#diagonsisNameId").select2({
//                language: "zh-CN",
//                width: 126
//            });
//            $($("#select2-diagonsisNameId-container").parent()).attr("style", "width:125px");

            $("#addAll").click(function () {


                $("#addType").val($("#addAll").text() == "全选" ? "add" : "del");
                $("#addPatientForm").submit();

            });

            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                $.postJSON("${ctx}/msPage/GetPatientInfo", {"type": "del"}, function (result) {
                    parent.layer.close(index);
                })

            });
            $('.form_date').datetimepicker({
                language: 'zh-CN',
                format: 'yyyy/mm/dd',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0
            });
        });
        function fn_LoadEmrList(page) {
            $('#hidPage').val(page);
            $('#btnSubmit').click();
        }
        function pullData() {
            var nowDate = new Date();
            var scanDate = new Date(nowDate);
            var ageTops = $("#ageTops").val();
            var ageFlooer = $("#ageFlooer").val();

            //endDate
            if (ageTops != "") {
                if (ageTops != "0") {
                    ageTops = nowDate.getFullYear() - ageTops + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
                } else {
                    ageTops = nowDate.getFullYear() + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
                }
            } else {
                ageTops = nowDate.getFullYear() + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
            }

            //startDate
            if (ageFlooer != "") {
                if (ageFlooer != "0") {
                    ageFlooer = nowDate.getFullYear() - ageFlooer + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
                } else {
                    ageFlooer = nowDate.getFullYear() - 1 + "/" + (parseInt(nowDate.getMonth()) + 1) + "/" + nowDate.getDate();
                }
            } else {
                ageFlooer = "1900/01/01";
            }
            if (ageTops != undefined && ageTops != "") {

                if (ageTops.indexOf("NaN") > 0)
                    ageTops = ageTops.replace("NaN", new Date().getFullYear());
            }


            $("#ageTopsVal").val(ageTops);
            $("#ageFlooerval").val(ageFlooer);
            return true;
        }
    </script>
</head>
<body style="background-color: #fff;">
<div style="margin: 10px 20px;">
    <form id="addPatientForm" action="/msPage/msAddPeople" method="post" onsubmit="pullData();return true;">
        <input id="hidPage" type="hidden" name="page"/>
        <input id="ageTopsVal" type="hidden" name="ageTops"/>
        <input id="ageFlooerval" type="hidden" name="ageFlooer"/>
        <input id="addType" type="hidden" value="${addType}" name="addType"/>

        <div>
            <label style="font-size: 16px;">收件人：</label>

            <div class="form-group mes-seach clearfix">

                <span style="padding-left: 2em;">搜索姓名</span>
                <input type="text" class="form-control"
                       name="patient" id="patientName"
                       value="${patientName}" style="width: 170px;">

                <span style="padding-left: 3em;">年龄</span>
                <input type="number" class="form-control"
                       name="ageTopsString" id="ageTops"
                       value="${ageTopsString}" style="width: 80px;">
                <span style="padding: 0 5px;">至</span>
                <input type="number" class="form-control"
                       id="ageFlooer" name="ageFlooerString"
                       value="${ageFlooerString}" style="width: 80px;">

            </div>

            <div class="form-group mes-seach clearfix">
                <span style="padding-left: 2em">主治医生</span>
                <select class="form-control" name="subDoctorId" style="width: 100px;display: inline-block;">
                    <option value="">全部</option>
                    <option value="${doctor.id}"
                            <c:if test="${doctor.id==subDoctorId}"> selected</c:if>
                    >${doctor.name}</option>
                    <c:forEach items="${subDoctorList}" var="one">
                        <option value="${one.id}" <c:if
                                test="${one.id==subDoctorId}"> selected</c:if> >${one.name}</option>
                    </c:forEach>
                </select>
                <span style="padding-left: 2em;">性别</span>
                <select style="width: 80px; display: inline-block;" name="genderSex" class="form-control">
                    <option value="">全部</option>
                    <option value="Male"
                            <c:if test="${genderSex=='Male'}">
                                selected="selected"
                            </c:if>
                    >男
                    </option>
                    <option value="Female"
                            <c:if test="${genderSex=='Female'}">
                                selected="selected"
                            </c:if>
                    >女
                    </option>
                </select>
                <span style="padding-left: 2em">病症</span>
                <input type="text"
                       style="width: 124px;" class="form-control"
                       name="diagonsisName"
                       value="${diagonsisName}"
                       id="diagonsisNameId"/>
                </button>
            </div>
            <div class="form-group mes-seach clearfix">
                <span for="txtStartDate" style="padding-left: 2em;">搜索日期</span>
                <input type="text" value="<fmt:formatDate value="${startDate}" pattern="yyyy/MM/dd" />"
                       name="startDate" class="form-control form_date"
                       id="txtStartDate" style="width:120px;" readonly/>
                <span style="padding: 0 5px;">至</span>
                <input type="text" value="<fmt:formatDate value="${endDate}" pattern="yyyy/MM/dd" />"
                       name="endDate" style="width:120px;"
                       class="form-control form_date" id="txtEndDate" readonly/>
                <button type="submit" id="btnSubmit" class="btn btn-success"
                        style="width: 100px; float:right; margin-right: 17px;">搜索
                </button>
            </div>

            <div class="form-group" style="min-height: 300px; margin-bottom: 0;">
                <table class="mes-table" width="100%" style="border: 1px solid #ccc;">
                    <colgroup width="10%"></colgroup>
                    <colgroup width="26%"></colgroup>
                    <colgroup width="17%"></colgroup>
                    <colgroup width="12%"></colgroup>
                    <colgroup width="12%"></colgroup>
                    <colgroup width="23%"></colgroup>
                    <thead>
                    <tr>
                        <th><input style="display: none" type="checkbox" id="checkAll" name="checkAll"/></th>
                        <th>日期</th>
                        <th>姓名</th>
                        <th>年龄</th>
                        <th>性别</th>
                        <th>电话</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${patientPage.content}" var="oneEmr" varStatus="emrNum">
                        <tr>
                            <td>
                                <input type="checkbox" name="checkItem" onclick="doChang(this)"
                                       value="${oneEmr.patient.id}"
                                        <c:if test="${not empty chackPatient[oneEmr.patient.id]}">
                                            checked
                                        </c:if>
                                />
                            </td>
                            <td><fmt:formatDate value="${oneEmr.createOn}"
                                                pattern="yyyy/MM/dd HH:mm"/>
                            </td>
                            <td>${oneEmr.patient.name}</td>
                            <td>${oneEmr.patient.age}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${oneEmr.patient.gender== 'Male'}">
                                        男
                                    </c:when>
                                    <c:when test="${oneEmr.patient.gender=='Female'}">
                                        女
                                    </c:when>
                                    <%--<c:otherwise>--%>
                                    <%--未录入--%>
                                    <%--</c:otherwise>--%>
                                </c:choose>
                            </td>
                            <td>${oneEmr.patient.mobile}</td>
                        </tr>
                    </c:forEach>


                    </tbody>
                </table>
                <div class="fenye">
                    <div class="fenye-buttom text-center">
                        <c:if test="${patientPage.number !=0}">
                            <button type="button" onclick="fn_LoadEmrList(${patientPage.number -1})"
                                    class="btn btn-default" style="width: 80px; margin-right: 10px;">上一页
                            </button>
                        </c:if>
                        <c:if test="${patientPage.number+1 != patientPage.totalPages }">
                            <button type="button" onclick="fn_LoadEmrList(${patientPage.number +1})"
                                    class="btn btn-default" style="width: 80px;" s> 下一页
                            </button>
                        </c:if>
                        <div class="form-group" style="margin-left: 20px;">
                            第 <span>${patientPage.number+1}</span>页/共<span>${patientPage.totalPages}</span>页
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </form>
    <div class="text-center">
        <button class="btn btn-success" style="width: 100px;" onclick="pullDataVil()">确定</button>
        <button id="btnClose" class="btn btn-default" style="width: 100px;">取消</button>
        <button type="button" id="addAll" class="btn btn-success" style="width: 100px;"><c:if
                test="${'del'.equals(addType) ||  empty addType}">全选</c:if><c:if
                test="${'add'.equals(addType)}">取消</c:if></button>
    </div>
</div>
<script type=" text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/scripts/hmp.js"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
</body>
</html>
