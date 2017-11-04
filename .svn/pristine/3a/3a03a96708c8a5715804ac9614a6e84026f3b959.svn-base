<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/30
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>打印经营统计表</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/print.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/print.js" type="js"/>
    <script type="application/javascript">
        window.print();
    </script>
</head>
<body>
<div class="print-mg financialTales clearfix">
    <div class="print-title text-center" style="margin-bottom: 15px;">经营统计表</div>
    <p class="print-p clearfix">
        <span class="text-left">机构名称：<span>${doctor.outpatientService}</span></span>
        <span class="text-right">统计时间段：<span>
            <fmt:formatDate value="${startDate}" pattern="yyyy/MM/dd"/>
            -
            <fmt:formatDate value="${endDate}" pattern="yyyy/MM/dd"/>

        </span></span>
    </p>
    <table width="100%" border="1">
        <colgroup width="20%"></colgroup>
        <colgroup width="15%"></colgroup>
        <colgroup width="18%"></colgroup>
        <colgroup width="17%"></colgroup>
        <colgroup width="14%"></colgroup>
        <colgroup width="16%"></colgroup>
        <tr>
            <td colspan="6" style="font-size:16px;">经营综合数据</td>
        </tr>
        <tr>
            <th>出诊医生</th>
            <td>${emrDoctorMap.size()}</td>
            <th>医生总工作量</th>
            <td>${emrList.size()}</td>
            <th>预约人数</th>
            <td>${appointRewardList.size()}</td>
        </tr>
        <tr>
            <th>新患者增加人数</th>
            <td>${newPatientEmrMap.size()}</td>
            <th>老患者人数</th>
            <td>${oldPatientEmrMap.size()}</td>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <td colspan="6" style="font-size:16px;">收入综合数据</td>
        </tr>
        <tr>
            <th>总收入</th>
            <td>${countCost}</td>
            <th>挂账总金额</th>
            <td>${HANG_UP}</td>
            <th></th>
            <td></td>
        </tr>
        <tr>
            <td colspan="6" style="font-size: 16px;">各个项目总金额</td>
        </tr>
        <tr>
            <th>处方中药</th>
            <td>${chinaPriceCount}</td>
            <th>处方西药</th>
            <td>${westernPriceCount}</td>
            <th>中医理疗</th>
            <td>${chineseTherapyPriceCount}</td>
        </tr>
        <tr>
            <th>检查/检验</th>
            <td>${jianChaYanPriceCount}</td>
            <th>附加费用</th>
            <td>${emrExtCostPriceCount}</td>
            <th></th>
            <td></td>
        </tr>
    </table>
    <table width="55%" border="1">
        <colgroup width="33%"></colgroup>
        <colgroup width="34%"></colgroup>
        <colgroup width="33%"></colgroup>
        <tr>
            <td colspan="3" style="font-size: 16px;">医生坐诊业绩统计</td>
        </tr>
        <tr>
            <th>主治医生</th>
            <th>坐诊人数</th>
            <th>坐诊业绩</th>
        </tr>
        <c:forEach items="${emrDoctorMap}" var="oneDoctor">
            <tr>
                <td>${oneDoctor.key.name}</td>
                <td>${oneDoctor.value.size()}</td>
                <td>
                        ${doctorDoubleMap.get(oneDoctor.key)}
                </td>
            </tr>
        </c:forEach>
    </table>
    <table width="40%" border="1">
        <colgroup width="55%"></colgroup>
        <colgroup width="45%"></colgroup>
        <tr>
            <td colspan="2" style="font-size: 16px;">柜员值班收入统计</td>
        </tr>
        <tr>
            <th>收银员</th>
            <th>收银金额</th>
        </tr>
        <c:forEach items="${shouYinPriceMap}" var="oneShouYin">

            <tr>
                <td>${oneShouYin.key}</td>
                <td>${oneShouYin.value}</td>
            </tr>

        </c:forEach>

    </table>
</div>
</body>
</html>
