<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/11
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>

<script type="application/javascript">
    function say() {
        layer.msg("请先收费，到已收费列表中进行打印！")
    }
</script>
<table class="adv-table" width="100%">
    <colgroup width="5%"></colgroup>
    <colgroup width="8%"></colgroup>
    <colgroup width="5%"></colgroup>
    <colgroup width="9%"></colgroup>
    <colgroup width="10%"></colgroup>
    <colgroup width="17%"></colgroup>
    <colgroup width="8%"></colgroup>
    <colgroup width="15%"></colgroup>
    <colgroup width="8%"></colgroup>
    <colgroup width="15%"></colgroup>
    <thead>
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>电话号码</th>
        <th>初步诊断</th>
        <th>待收金额</th>
        <th>就诊时间</th>
        <th>主治医生</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${emrListPage.content}" varStatus="status"
               var="one"><%--@elvariable id="emrListPage" type="org.springframework.data.domain.Page"--%>

        <tr>
            <td>
                    ${status.count +(emrListPage.number * emrListPage.getSize())}
            </td>
            <td>${one.patientName}</td>
            <td>${genderMap[one.patient.gender]}</td>
            <td>${one.patient.getAge()}</td>
            <td>${one.patient.mobile}</td>
            <td>
                <span class="text-num" style="width: 170px;">
                    <c:forEach items="${one.diagnosisList}" var="oneDiagnosis">
                        <span title="${oneDiagnosis.name}">${oneDiagnosis.name}</span>
                    </c:forEach>
                </span>
            </td>
            <td>
                <fmt:formatNumber type="number" value="${one.cost-one.realCost } " maxFractionDigits="2"/>
            </td>
            <td>
                <fmt:formatDate value="${one.createOn}" pattern="yyyy/MM/dd HH:mm"/>
            </td>
            <td>
                    ${one.doctor.name}
            </td>
            <td>
                <button class="btn btn-default" type="button" onclick="chargeDetails(${one.id})">收费</button>
                <button class="btn btn-default" type="button" onclick="return layer.confirm('是否挂起',function(re) {
                        updateStatus('${one.id}','HANG_UP','${one.realCost}');
                        layer.closeAll();
                        });">挂起
                </button>
                    <%--  <button class="btn btn-success" type="button" style="width: 80px;"
                          &lt;%&ndash;onclick="fn_printReceipt('${one.id}')" &ndash;%&gt; onclick="say()">打印收据
                      </button>--%>
            </td>
        </tr>

    </c:forEach>

    </tbody>

</table>
