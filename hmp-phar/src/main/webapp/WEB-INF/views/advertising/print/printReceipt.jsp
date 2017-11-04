<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/16
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>打印收据</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/styles/print.css" type="css"/>
    <script type="application/javascript">
        $(function () {
            <c:if test="${emr.realCost==null||emr.realCost==0}">
            $("#xxText").text('${emr.realCost}');
            var dxPrice = DX('${emr.realCost}');
            </c:if>

            <c:if test="${emr.realCost!=null&&emr.realCost!=0}">
            $("#xxText").text('${emr.realCost}');
            var dxPrice = DX('${emr.realCost}');
            </c:if>

            var arr = dxPrice.split("-").reverse();
            var objArr = new Array();
            $.each($(".tmp-dx"), function (index, obj) {
//                if (arr.length > index) {
//                    $(this).text(arr[(arr.length-index)]);
//                } else {
//                    $(this).text("零");
//                }
                objArr.push(obj);
            })
            $.each(objArr.reverse(), function (index, obj) {
                if (arr.length > index) {
                    $(this).text(arr[index]);
                } else {
                    $(this).text("零");
                }
            })
            window.print();
        })
    </script>
</head>
<body style="font-family: '微软雅黑','Microsoft YaHei'">
<div class="print-box">
    <div class="text-center" style="margin-bottom: 3px;">
        <span class="print-title">&nbsp;</span>
    </div>
    <p class="text-right" style="font-size: 12px;">
        <span>
            <fmt:formatDate value="${nowDate}" pattern="yyyy"/>
        </span>
        <span></span>
        <span>
            <fmt:formatDate value="${nowDate}" pattern="MM"/>
        </span>
        <span></span>
        <span>
            <fmt:formatDate value="${nowDate}" pattern="dd"/>
        </span>
        <span></span>
    </p>
    <p class="text-right" style="margin-bottom: 4px;">
        <span></span><span style="font-size: 14px;"></span>
        <span></span><span style="font-size: 14px;"></span>
        <span></span><span style="font-size: 14px;"></span>
    </p>
    <table width="100%" border="0">
        <colgroup width="9%"></colgroup>
        <colgroup width="12%"></colgroup>
        <colgroup width="13%"></colgroup>
        <colgroup width="13%"></colgroup>
        <colgroup width="13%"></colgroup>
        <colgroup width="14%"></colgroup>
        <colgroup width="13%"></colgroup>
        <colgroup width="13%"></colgroup>
        <tr>
            <td></td>
            <td colspan="2">${emr.patient.name}</td>
            <td colspan="2"></td>
            <td colspan="3"></td>
        </tr>
        <tr>
            <td colspan="2"><%--医保/公医记账--%></td>
            <td colspan="2"></td>
            <td><%--个人缴费--%></td>
            <td></td>
            <td><%--结算方式--%></td>
            <td><%--现金--%></td>
        </tr>
        <tr>
            <th><%--医药费--%></th>
            <th><%--金额--%></th>
            <th><%--诊查费--%></th>
            <th><%--金额--%></th>
            <th><%--治疗费--%></th>
            <th><%--金额--%></th>
            <th><%--其他--%></th>
            <th><%--金额--%></th>
        </tr>
        <tr>
            <td>中药费</td>
            <td>${chinesePrice}</td>
            <td>检查费</td>
            <td>${labPrice}</td>
            <td>治疗费</td>
            <td>${fuJiaPrice}</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>西药费</td>
            <td>${westernPrice}</td>
            <td>检验费</td>
            <td>${examPrice}</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td colspan="8"></td>
        </tr>
        <tr>
            <td colspan="2"></td>
            <td colspan="4">
                <div class="print-dx">
                    <span id="dx_7" class="tmp-dx">壹</span><span><%--拾--%></span>
                    <span id="dx_6" class="tmp-dx">贰</span><span><%--万--%></span>
                    <span id="dx_5" class="tmp-dx">叁</span><span><%--仟--%></span>
                    <span id="dx_4" class="tmp-dx">肆</span><span><%--佰--%></span>
                    <span id="dx_3" class="tmp-dx">伍</span><span><%--拾--%></span>
                    <span id="dx_2" class="tmp-dx">陆</span><span><%--元--%></span>
                    <span id="dx_1" class="tmp-dx">柒</span><span><%--角--%></span>
                    <span id="dx_0" class="tmp-dx">捌</span><span><%--分--%></span>
                </div>

            </td>
            <td colspan="2">
                <div class="text-left" style="padding-left: 30px;" id="xxText"><%--￥：--%>0</div>
            </td>
        </tr>
        <tr>
            <td rowspan="2" colspan="8"></td>
        </tr>
        <tr></tr>
    </table>
    <div class="clearfix" style="margin-top: 7px;">
        <div style="float:left; width: 65%;"><span>&nbsp;</span></div>
        <div style="float: left; width: 35%;">
            <span><%--收费员：--%></span>
            <span>${user.name}</span>
        </div>
    </div>

</div>
</body>
</html>
