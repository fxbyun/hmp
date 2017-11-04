<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 100%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-user"></i> 药方详情
                        </div>
                        <div class="row-fluid">
                            <div style="width: 70%;margin: 10px auto;">
                                <table border="0" width="100%" style="font-size: 14px">
                                    <tr style="height: 40px">
                                        <td align="right" width="100px"><b>药方类别: &nbsp;</b></td>
                                        <td>${prescription.categoryName}</td>
                                    </tr>
                                    <tr style="height: 40px">
                                        <td align="right"><b>药方分类: &nbsp;</b></td>
                                        <td>${medicineTypes[prescription.medicineType]}</td>
                                    </tr>
                                    <tr style="height: 40px">
                                        <td align="right"><b>适用病症: &nbsp;</b></td>
                                        <td>${prescription.diagnosis}</td>
                                    </tr>
                                    <tr style="height: 40px">
                                        <td align="right"><b>药方名称: &nbsp;</b></td>
                                        <td>${prescription.name}</td>
                                    </tr>
                                    <tr style="height: 40px">
                                        <td align="right"><b>药方详情: &nbsp;</b></td>
                                        <td>${prescription.remark}</td>
                                    </tr>
                                    <tr>
                                        <td align="right"><b>用药清单: &nbsp;</b></td>
                                        <td>
                                            <div class="label-box">
                                                <c:forEach var="item" items="${prescription.prescriptionItemList}">
                                                    <span class="tag">${item.medicineName}&nbsp;${item.qty}${medicineUnits[item.unit]}x${item.copies}份</span>
                                                </c:forEach>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <div style="margin: 0 auto;width:180px">
                                                <c:if test="${prescription.status == 'Normal'}">
                                                    <a class="btn btn-info" href="${ctx}/rezept/share/${prescription.id}">共享药方</a>
                                                </c:if>
                                                <c:if test="${prescription.status == 'Used'}">
                                                    <a class="btn btn-info" href="${ctx}/rezept/cancelShare/${prescription.id}">取消共享</a>
                                                </c:if>
                                                <a class="btn btn-info" href="${ctx}/rezept/list">返回列表</a>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="permit"></div>
    </section>
    <footer class="footer">
        <h5 class="transparent-text center">
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备15089657号-2</a></div>
        </h5>
    </footer>
</div>
<script type="text/javascript">
    $(function () {

    });
</script>