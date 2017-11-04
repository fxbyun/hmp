<%--@elvariable id="mp" type="com.qiaobei.hmp.modules.entity.MedicinePrivate"--%>
<%--@elvariable id="stock" type="com.qiaobei.hmp.modules.entity.MedicineStock"--%>
<%--@elvariable id="retail" type="com.qiaobei.hmp.modules.entity.retail"--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/12
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>选择药品</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/validity.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery.form/jquery.form.js" type="js"/>
</head>
<body style="background-color: #fff;">
<form action="${ctx}/retail/addMed" method="post" id="medForm">
<div style="padding: 20px;">
    <div class="row">
        <div class="col-sm-12 col-xs-12">
            <div class="form-group">
                <h3 class="media-h3 color-red ">${mp.name}<span style="padding-left: 20px;"
                                                                class="color-red ">库存：${not empty stock.stockNum?stock.stockNum:"未知"}&nbsp;${medicineUnits[mp.unit]}</span>
                    <span style="padding-left: 20px;">药品价格：<span
                            id="medPrice">${not empty mp.price?mp.price:"0.00"}</span>元/${medicineUnits[mp.unit]}</span>
                    <%--修改价格--%>
                    <span <%--id="updatePrice"--%> id="editMed" class="fa fa-edit"></span>
                    <div style="float: right; line-height: 2.2em;" class="color-red ">有效日期：<c:if
                            test="${empty stock.validityDate}">未加入库存</c:if><fmt:formatDate
                            value="${stock.validityDate}" pattern="yyyy/MM/dd"/></div>
                </h3>
            </div>

            <%--隐藏的药品信息--%>
            <div class="form-group" style="padding: 20px 0 0px 0; margin: 0 auto;display: none;">
                <div class="row">
                    <label class="col-xs-4 col-sm-4 control-label text-success text-right"
                           style="padding: 0; line-height: 30px;">条形码</label>
                    <div class="col-xs-4 col-sm-4" style="">
                        <input type="text" class="form-control text-success" id="barCode" name="barCode"
                               value="${stock.barCode}">
                    </div>

                    <div class="col-xs-4 col-sm-4" style="">
                        <input type="text" class="form-control text-success" id="medPrivateId" name="medPrivateId"
                               value="${mp.id}">
                    </div>

                    <div class="col-xs-4 col-sm-4" style="">
                        <input type="text" class="form-control text-success" id="retailId" name="retailId"
                               value="${retail.id}">
                    </div>
                </div>

            </div>

            <div class="form-group" style="padding: 20px 0 0px 0; margin: 0 auto;">
                <div class="row">
                    <label class="col-xs-4 col-sm-4 control-label text-success text-right"
                           style="padding: 0; line-height: 30px;">药品规格</label>
                    <div class="col-xs-4 col-sm-4" style="">
                        <input type="text" class="form-control text-success" id="txtStandard" name="standard"
                               value="${mp.standard}">
                    </div>
                </div>

            </div>
            <div class="form-group text-success" style="padding: 10px 0 10px 0; margin: 0;">
                <div class="row">
                    <label class="col-xs-4 col-sm-4 control-label text-success text-right" for="txtMedicineQty"
                           style="padding: 0; line-height: 30px;">数量/单位</label>

                    <div class="col-xs-2 col-sm-2" style="">
                        <input type="number" min="1" class="form-control text-success" name="qty"
                               id="txtMedicineQty" value="${not empty mp.realQty?mp.realQty:1}">
                    </div>
                    <div class="col-xs-2 col-sm-2" style="padding: 0;">
                        <select id="selMedicineUnit" class="form-control text-success" name="unit">
                            <c:forEach var="u" items="${medicineUnits}">
                                <option value="${u.key}"
                                        <c:if test='${mp.unit==u.key}'>selected</c:if>>${u.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="col-xs-1 col-sm-1 text-center" for="txtMedicineQty"
                           style="line-height: 33px;display: none;">X</label>

                    <div class="col-xs-2 col-sm-2" style="margin-left: -10px;display: none;">
                        <input name="copies" type="number" min="1" class="form-control text-success"
                               id="txtMedicineCopies" value="1">
                    </div>
                    <label class="col-xs-1 col-sm-1" for="txtMedicineQty"
                           style="line-height: 33px; margin-left: -10px;display: none;">份</label>
                </div>
            </div>

            <div id="divConversion" style="display: none;" class="form-group text-success"
                 style="line-height: 33px; margin: 0;">
                <div class="row">
                    <input type="hidden" id="hidConversionId" value="">
                    <label class="col-xs-4 col-sm-4 text-right text-success" style="padding: 0;">换算单位</label>
                    <label class="col-xs-2 col-sm-2 text-success">1 ${medicineUnits[mp.unit]}
                        = </label>
                    <div class="col-xs-3 col-sm-3" style="margin-left: -2em;">
                        <input type="number" min="1" name="rate" class="form-control text-success" id="txtMedicineRate"
                               value="${mp.rate}">
                    </div>
                    <label id="showText" class="col-xs-2 col-sm-2 text-success"
                           style="margin-left: -1.5em;">${medicineUnits[mp.useUnit]}</label>
                </div>
            </div>
            <div class="form-group" style="margin:0;">
                <div class="row">
                    <div class="col-xs-12 col-sm-12">
                        <h5 id="lblConversionMsg" class="text-danger text-center"></h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="text-center btn-width" style="border-top: 1px solid #ccc; padding-top:20px;">
        <%--<button id="updateUnit" class="btn btn-default" type="button" style="width: 81px;">修改药品</button>--%>
        <button id="btnSubmit" class="btn btn-success" type="button">确定</button>
        <button id="btnClose" class="btn btn-default" type="button">取消</button>
    </div>
</div>
</form>
<script type="application/javascript">
    $(function () {
        $("#btnSubmit").click(function () {
            $("#medForm").ajaxSubmit({
                url: "${ctx}/retail/addMed",
                success: function (data) {
                    if (data.success == true) {
                        parent.location.reload();
                        parent.layer.closeAll();

                    } else {
                    }
                }
            })
        });

        //修改药品的unit
        $("#updateUnit").click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.fn_EditMedicine(${mp.id}, '${mp.type}', '中草药房', function () {
                parent.layer.iframeSrc(index,
                        ctx + '/retail/retailMedShow?medId=${mp.id}&retailId=${retail.id}&' + jQuery.now());
            });
        });


        $('#selMedicineUnit').change(function () {
            debugger
            var unit = "${medicineUnits[mp.useUnit]}";
            var $opt = $('#selMedicineUnit option:selected');
            var selUnit = $('#selMedicineUnit').val();
            var text = $opt.text();
            if (unit == selUnit)
                $('#divConversion,#btnConvert').hide();
            else {
                $('#divConversion,#btnConvert').show();
                $("#showText").text(text);
            }
        });
        /*修改药品价格*/
        $("#updatePrice").click(function () {
            layer.prompt({title: '修改药品价格', maxlength: 200}, function (value, index, elem) {

                var money = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
                if (!money.test(value)) {
                    layer.msg("请输入正确的金额！");
                } else {
                    $.postJSON("${ctx}/retail/updateMedPrice", {"medId":${mp.id}, "medPrice": value}, function (data) {
                        if (data.success) {
                            layer.msg("修改成功！");
                            $("#medPrice").text(value);
                            layer.close(index);
                        } else {
                            layer.msg("修改失败！");
                            layer.close(index);
                        }
                    });
                }
            });
        });
        var index = parent.layer.getFrameIndex(window.name);
        //最新修改药品
        $("#editMed").click(function () {
            editMedPrice(${mp.id}, function () {
                var url = ctx + "/retail/retailMedShow?medId=${mp.id}&retailId=${retail.id}";
                parent.layer.iframeSrc(index, url);
            })
        });

        $("#btnClose").click(function () {
            parent.layer.close(index);
        });


    });
    /**
     *  零售开单-修改药品
     */
    function editMedPrice(medId, callback) {
        parent.layer.open({
            type: 2,
            title: '修改药品价格',
            area: ['300px', '180px'],
            scrollbar: false,
            content: ctx + '/retail/bombBox/editMedPrice?medId={0}'.format(medId),
            end: callback
        });
    }
</script>
</body>
</html>
