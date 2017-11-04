<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/8
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>收费明细</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/style.css" type="css"/>
    <script>
        var unitMap = {
            "bxs": "盒",
            "btl": "瓶",
            "pkg": "包/排",
            "grs": "粒/片",
            "pcs": "支",
            "g": "g",
            "mg": "mg",
            "ml": "ml",
            "oth": "单位",
            "needle": "针",
            "acupoint": "穴位",
            "part": "部位",
            "therapyUnit": "单位",
            "ge": "个",
            "skill": "手法"
        };

        //如果是子医生的话，实收总价格不可编辑
        var doctorType = '${loginDoctor.doctorType}';
        var allowUpdatePrice = ${primaryDoctor.allowSubDoctorUpdatePrice==''|| !primaryDoctor.allowSubDoctorUpdatePrice?false:true};
        debugger
        var medInfoMap = parent.priceMap;
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.$("#cost").val($("#nowPrice").val());
                parent.$("#hiddenSetCost").val($("#nowPrice").val());
                parent.layer.close(index);
            });
            debugger
            loadTable();

        });
        function editMedicinePrice(medPrivateId, unitPrice) {
            if (doctorType == 'Sub_Doctor' && !allowUpdatePrice) {
                layer.msg("药品价格不能修改！");
                return;
            }
            var url = "/fragment/editMedicinePrice?medPrivateId={0}&unitPrice={1}".format(medPrivateId, unitPrice);
            layer.open({
                type: 2,
                title: '修改药品零售价',
                area: ['280px', '200px'],
                scrollbar: false,
                content: url
            });
        }


        function loadTable() {
            $("#tbodyId").html("");
            var countPrice = 0;

            $.each(medInfoMap.keys(), function (index, obj) {
                var medObj = medInfoMap.get(obj);
                debugger
                appedtTrToMedList(medObj);
                if (medObj) {
                    countPrice = (parseFloat(countPrice) + parseFloat(medObj.price));
                }
            });
            $("#countPrice").val(countPrice.toFixed(2));

//            parent.$("#hiddenSetCost").val($("#nowPrice").val());
            if (parent.$("#hiddenSetCost").val() != "0" && parent.$("#hiddenSetCost").val() != "") {
                /*前端会保存一个上一回药品总价,实收总价格的改变,*/
                /*取决这个药品总价是否改变,改变了则说明增加或者减少了药,*/
                /*还取决于是否点击了确定按钮*/
                if (parseInt(parent.$("#hiddenSetCostPrice").val()) != parseInt($("#countPrice").val())) {
                    $("#nowPrice").val($("#countPrice").val());
                    parent.$("#hiddenSetCostPrice").val(countPrice.toFixed(2));
                } else {
                    $("#nowPrice").val(parent.$("#hiddenSetCost").val());
                }
            } else {
                $("#nowPrice").val(countPrice.toFixed(2));
                parent.$("#hiddenSetCostPrice").val(countPrice.toFixed(2));
            }

        }

        /**
         *  增加药品到表格行中
         * @param medObj
         */
        function appedtTrToMedList(medObj) {
            var tr = document.createElement("tr");
            var td1 = document.createElement("td");
            var span1 = document.createElement("span");

            var tmpStr = (parseFloat(medObj.qtyTmp) * parseFloat(medObj.copies)).toFixed(2) + medObj.unitLabel;
            debugger
            if (medObj.tjUnit == "") {
                medObj.tjUnit = medObj.realUnit
            }
            var tmpDanJia = medObj.unitPrice + "/" + unitMap[medObj.tjUnit];
            var tmpOnclick = "editMedicinePrice('" + medObj.medicinePrivateId + "','" + medObj.unitPrice + "');";

            var tmpMedName = medObj.medicineName;

            if (tmpMedName == undefined) {
                tmpMedName = medObj.adviceName;
                if (tmpMedName == undefined)
                    tmpMedName = medObj.name;
                tmpStr = "1次";
                tmpDanJia = medObj.price;
                tmpOnclick = "javascript:layer.msg('请到处方笺修改价格!')";
            }

            //这是中医理疗的套路
            if (medObj.medType == 'therapy') {
                tmpMedName = medObj.name;
                tmpStr = medObj.copies + "次/" + unitMap[medObj.tjUnit];
                tmpOnclick = "javascript:layer.msg('中医理疗请到处方笺修改价格!')";
                tmpDanJia = medObj.unitPrice;
            }


            $(span1).attr("title", tmpMedName);
            $(span1).text(tmpMedName);
            $(td1).append($(span1));
            $(tr).append($(td1));

            var td2 = document.createElement("td");

            $(td2).text(tmpStr);
            $(tr).append($(td2));

            var td3 = document.createElement("td");
            $(td3).text(tmpDanJia);
            var i = document.createElement("i");
            $(i).attr("class", "fa fa-edit");
            $(i).attr("onclick", tmpOnclick)
            $(td3).append($(i))
            $(tr).append($(td3));

            var td4 = document.createElement("td");
            $(td4).text(medObj.price);
            $(tr).append($(td4));
            $("#tbodyId").append($(tr));
        }

    </script>
</head>
<body style="background-color: #fff;">
<div class="chargeDetail">
    <div style="height: 182px; overflow-y: auto;">
        <table width="100%" border="1">
            <colgroup width="40%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="20%"></colgroup>
            <thead>
            <tr>
                <th>药品名称</th>
                <th>产品数量</th>
                <th>零售单价</th>
                <th>合计</th>
            </tr>
            </thead>
            <tbody id="tbodyId">
            <%-- 添加 纸质处方笺  start--%>
            <%--<tr>--%>
            <%--<td>纸质处方笺：201602122510</td>--%>
            <%--<td>0</td>--%>
            <%--<td>0.0</td>--%>
            <%--<td>0.0</td>--%>
            <%--</tr>--%>
            <%-- end --%>
            </tbody>
        </table>
    </div>

    <div style="margin: 20px 0;">
        <label>总价格：</label>
        <input class="form-control" style="width: 20%;display: inline-block;" id="countPrice" value="0" disabled>
        <label style="padding-left: 6em;">实收总价格：</label>
        <input type="number" class="form-control" hmp-input-double style="width: 20%;display: inline-block;"
        <c:if test="${loginDoctor.doctorType=='Sub_Doctor' && (!primaryDoctor.allowSubDoctorUpdatePrice || primaryDoctor.allowSubDoctorUpdatePrice=='')}">
               readonly="readonly"
        </c:if>
               id="nowPrice" value="0">
    </div>
    <div class="text-center" style="padding-top: 12px;">
        <button type="button" id="btnClose" class="btn btn-success" style="width: 80px;">确定</button>
        <%--<button id="btnClose" type="button" class="btn btn-default" style="width: 80px; margin-left: 5px;">取消</button>--%>
    </div>
</div>
</body>
</html>
