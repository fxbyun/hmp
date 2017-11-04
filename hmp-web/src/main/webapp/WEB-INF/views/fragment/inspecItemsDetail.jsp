<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/27
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>检查项目明细</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/typeahead/typeahead.bundle.js" type="js"/>
    <script>
        $(function () {
            console.info();
            //是否设置能改价格
            var allowUpdatePrice = ${primaryDoctor.allowSubDoctorUpdatePrice==''|| !primaryDoctor.allowSubDoctorUpdatePrice?false:true};
            if ('<shiro:principal property='doctorType'/>' == "Sub_Doctor" && !allowUpdatePrice) {
                $(".fa-edit").removeAttr("onclick");
                $(".fa-edit").click(function () {
                    parent.Alert.warning("只有主治医生才能修改价格!");
                })
            }
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });
            function trClick() {
                var $tbr = $('table tbody tr');
                /*点击每一行的选中单选框时*/
                $tbr.find('input').click(function (event) {
                    event.stopPropagation();
                });
                $tbr.click(function () {
                    $(this).find('input').click();
                });
            }

            trClick();
            setInterval(function () {
                $(".twitter-typeahead").attr("style", "");
            })
        });

        function haveSelectExamLab(jClassAdviceDictId, adviceName) {
            $.postJSON("/examLab/checkHaveDoctorUse",
                {
                    jClassAdviceDictId: jClassAdviceDictId
                }, function (ret) {
                    if (ret.success) {
                        haveSelect = true;
                        parent.parent.Alert.success("您已经选择 " + adviceName);
                        ret = ret.data;
                        parent.parent.fn_editItems(ret.id, ret.subId, ret.subTwoId);
                    }
                });

        }
        function fm_addExamLab() {
            var checkedObj = $("tbody tr input[type='radio']:checked");
            if (checkedObj.length <= 0) {
                layer.msg("请选择项目");
                return false;
            }
            var ids = $(checkedObj).val();
            haveSelectExamLab($("#tr" + ids).val(), $("#name" + ids).val());
        }
    </script>

    <SCRIPT>
        $(function () {
            var examLab_list = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.whitespace,
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                remote: {
                    url: '/examLab/query?key=%QUERY',
                    wildcard: '%QUERY'
                }
            });

            $('#examLabId').typeahead({
                minLength: 1,
                highlight: true
            }, {
                name: 'examLab_list',
                limit: 50,
                async: true,
                display: 'adviceName',
                source: examLab_list,
                templates: {
                    'empty': '<div class="tt-suggestion">无结果</div>',
                    'suggestion': function (o) {
                        o.adviceType = o.adviceType == "JianCha" ? "检查" : "检验";
                        return '<div onclick="haveSelectExamLab({4},\'{1}\')">{0} - {3} – <strong>{1}</strong> - {2}元</div>'.format(o.adviceType, o.adviceName, o.price, o.classAdviceDictName, o.id);
                    }
                }
            });

        });
    </SCRIPT>
</head>
<body style="background-color: #fff;">
<div style="padding: 10px 20px;" class="items-table">
    <div class="form-group">
        <div class="row" id="examLabDiv">
            <label class="col-xs-12 col-sm-12 text-left" style="font-size: 16px;">检查/检验名称</label>
            <div class="col-xs-12 col-sm-12">
                <div class="form-group">
                    <input type="text" class="form-control" id="examLabId" placeholder="请输入 检查/检验 项目名称进行搜索"/>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 320px; overflow-y: auto;">
        <table width="100%" class="table-hover table-bordered">
            <colgroup width="10%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="12%"></colgroup>
            <colgroup width="27%"></colgroup>
            <colgroup width="15%"></colgroup>
            <colgroup width="16%"></colgroup>
            <thead>
            <tr>
                <th>选择</th>
                <th>项目类型</th>
                <th>项目大项</th>
                <th>项目名称</th>
                <th>费用</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${jDoctorExamLabList}" var="one">
                <tr>
                    <td>
                        <input type="radio" name="tr" value="${one.id}">
                        <c:if test="${one.type=='JianYan'}">
                            <input type="hidden" id="tr${one.id}" value="${one.subId}">
                        </c:if>
                        <c:if test="${one.type=='JianCha'}">
                            <input type="hidden" id="tr${one.id}" value="${one.subTwoId}">
                        </c:if>
                        <input type="hidden" id="name${one.id}" value="${one.classAdviceDictName}">

                    </td>
                    <td><span class="val-text" style="width: 160px;"
                              title="${one.examLabName}">${one.examLabName}</span></td>
                    <td>
                        <span title="${one.getMetaValue(one.type)}">
                                ${one.getMetaValue(one.type)}
                        </span>
                    </td>
                    <td>
                        <span title="${one.classAdviceDictName}">
                                ${one.classAdviceDictName}
                        </span>
                    </td>
                    <td>${one.price}<i class="fa fa-edit" onclick="editItemsDetail(${one.id})"></i></td>
                    <td>
                        <button type="button" class="btn btn-default" onclick="deleteItemTr(${one.id})">删除</button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <div class="text-center btn-wd-mr" style="margin-top: 20px;">
        <button type="button" class="btn btn-success" onclick="fm_addExamLab()">添加</button>
        <%--<button type="button" class="btn btn-success" onclick="saveItemsDetail()">新增项目</button>--%>
        <button id="btnClose" type="button" class="btn btn-default">关闭</button>
        <button type="button" class="btn btn-success" onclick="openAddExamOrLabBox()" style="width: 150px;">创建检查/检验项目
        </button>
    </div>
</div>
<script type="application/javascript">
    function openAddExamOrLabBox() {
        var index = layer.confirm("请选择增加项目类型", {
            btn: ["检查", "检验", "取消"]
        }, function () {
            openAdd("JianCha");
        }, function () {
            openAdd("JianYan");
        }, function () {
            parent.layer.close(index);
        });
    }
    var windowHunder = window.location;
    function openAdd(types) {
        parent.layer.open({
            type: 2,
            title: '增加项目',
            area: ['430px', '300px'],
            scrollbar: false,
            content: "/fragment/addExamOrLabBox?type=" + types,
            end: function () {
                windowHunder.reload();
            }
        });
    }

</script>
</body>
</html>
