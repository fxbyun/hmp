<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/8
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改药品单价</title>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });
            $('#updateMedPrivateUnitPrice').click(function () {
                $.postJSON("${ctx}/fragment/updateMedPrivateUnitPrice",
                        {
                            medPrivateId:${medPrivateId},
                            unitPrice: $("#unitPriceInput").val()
                        }, function (ret) {
                            if (ret.success) {
                                parent.layer.msg("修改成功!");
                                parent.parent.$("#hiddenSetCost").val("0");
                                updateParentTable(parent.medInfoMap);
                                parent.layer.close(index);
                            } else {
                                layer.msg("修改失败!" + ret.msg);
                            }
                        })
            });

            function updateParentTable(medInfoMap) {
                $.each(medInfoMap.keys(), function (index, obj) {
                    var medObj = medInfoMap.get(obj);
                    if (medObj.medicinePrivateId == "${medPrivateId}") {
                        medInfoMap.remove(obj);
                        medObj.unitPrice = $("#unitPriceInput").val();
                        medObj.price = (medObj.qty * medObj.copies * (medObj.unitPrice / medObj.rate)).toFixed(2);
                        parent.medInfoMap.put(obj, medObj);
                        if (medObj.medType == "Chinese") {
                            $.each(parent.parent.parent.$("#divChineseItems").find("input[name='medicinePrivateIds']"),
                                    function (index, obj) {
                                        if ($(obj).val() == medObj.medicinePrivateId) {
                                            var patentSpan = $(obj).parent();
                                            $(patentSpan).find("input[name='prices']").val(medObj.price);
                                            $(patentSpan).find("input[name='unitPrices']").val(medObj.unitPrice);
                                        }
                                    })
                        } else if (medObj.medType == "Western") {
                            $.each(parent.parent.parent.$("#divWesternItems").find("input[name='medicinePrivateIds']"),
                                    function (index, obj) {
                                        if ($(obj).val() == medObj.medicinePrivateId) {
                                            var patentSpan = $(obj).parent();
                                            $(patentSpan).find("input[name='prices']").val(medObj.price);
                                            $(patentSpan).find("input[name='unitPrices']").val(medObj.unitPrice);
                                        }
                                    })
                        }
                    }

                });
                parent.loadTable();
            }
        });
    </script>
</head>
<body style="background-color: #fff;">
<div style="padding: 12px 20px;" class="text-center">
    <p><strong style="text-align: center;font-size: 16px;">${med.name}</strong></p>

    <input type="number" id="unitPriceInput"
           class="form-control"
           style="width: 40%;
           display: inline-block;"
           hmp-input-double
           value="${med.price}"><span
        style="padding-left: 5px;">/${medicineUnits[med.unit]}</span>
    <p class="text-center" style="margin-top: 20px;">
        <button id="updateMedPrivateUnitPrice" type="button" class="btn btn-success"
                style="width: 65px; margin-right: 10px;">确定
        </button>
        <button id="btnClose" type="button" class="btn btn-success" style="width: 65px;">取消</button>
    </p>
</div>
</body>
</html>
