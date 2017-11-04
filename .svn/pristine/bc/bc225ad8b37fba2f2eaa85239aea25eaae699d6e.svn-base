<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/23
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>财务统计</title>
    <script type="application/javascript">
        function printFinancial(doctorId) {
            var url = "${ctx}/pub/printFinacialTables?doctorId={0}&startDate={1}&endDate={2}".format(doctorId, $("#txtStartDate").val(), $("#txtEndDate").val())
            layer.open({
                type: 2,
                maxmin: false,
                btn: ["关闭"],
                yes: function (index, layero) {
                    layer.closeAll();
                },
                title: '打印财务统计',
                area: ['530px', '700px'],
                scrollbar: false,
                content: url
            });
        }
    </script>
</head>
<body>
<div class="manage" style="margin-bottom: 10px;">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/message" class="btn btn-default" onclick="isDebug()">短信发送</a></li>
            </c:if>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li><a href="${ctx}/rplib" class="btn btn-default">药方库</a></li>
            <li><a href="${ctx}/config/symptom" class="btn btn-default">数据整理</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            </c:if>
            <%--是否开始有效期，记得注释--%>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                <li><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            </c:if>
            <li class="active"><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            <ul class="navigation-sub">
                <li class="active"><a href="${ctx}/financial" class="btn btn-default">日经营统计</a></li>
                <li><a href="${ctx}/statisics/patientDetail" class="btn btn-default" style="width: 152px;">日患者明细统计</a>
                </li>
            </ul>
        </ul>
        <div class="infro-content">
            <form action="/financial" method="post">
                <div class="row form-inline">
                    <div class="col-md-5 col-sm-5 pull-right" style="margin-right: 20px;">
                        <div class="form-group" style="padding-right: 1em;">
                            <label for="txtStartDate">查询日期</label>
                            <input type="text" name="startDate"
                                   value="<fmt:formatDate value="${startDate}" pattern="yyyy/MM/dd" />"
                                   class="form-control form_day" id="txtStartDate"
                                   style="width:120px;" readonly="">
                            至
                            <input type="text" name="endDate" style="width:120px;"
                                   class="form-control form_day"
                                   value="<fmt:formatDate value="${endDate}" pattern="yyyy/MM/dd" />"
                                   id="txtEndDate" readonly="">
                        </div>
                        <div class="form-group">
                            <button id="btnSubmit" type="submit" class="btn btn-info pull-right">
                                搜索
                            </button>
                        </div>
                        <div class="form-group">
                            <button onclick="printFinancial('${doctor.id}')" type="button"
                                    class="btn btn-info pull-right">
                                打印数据
                            </button>
                        </div>
                    </div>
                </div>
            </form>
            <div class="financial clearfix">
                <table width="100%" border="1">
                    <colgroup width="17%"></colgroup>
                    <colgroup width="18%"></colgroup>
                    <colgroup width="17%"></colgroup>
                    <colgroup width="18%"></colgroup>
                    <colgroup width="14%"></colgroup>
                    <colgroup width="16%"></colgroup>
                    <tr>
                        <td colspan="6" style="font-size: 20px;">经营综合数据</td>
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
                        <td colspan="6" style="font-size: 20px;">收入综合数据</td>
                    </tr>
                    <tr>
                        <th>总收入</th>
                        <td>${countCost+retAllPrice}</td>
                        <%--<th>退款</th>
                        <td>20.00</td>--%>
                        <th>挂账总金额</th>
                        <td>${HANG_UP}</td>
                        <th>
                            零售总金额
                        </th>
                        <td>
                            ${retAllPrice}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" style="font-size: 20px;">各个项目总金额</td>
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
                    <%-- 新增 零售各项金额 2017-1-10 14:47:30 --%>
                    <tr>
                        <td colspan="6" style="font-size: 20px;">零售各项金额</td>
                    </tr>
                    <tr>
                        <th>中药</th>
                        <td>${retChPrice}</td>
                        <th>西药</th>
                        <td>${retWePrice}</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%--  end --%>
                </table>
                <table width="50%" border="1">
                    <colgroup width="33%"></colgroup>
                    <colgroup width="34%"></colgroup>
                    <colgroup width="33%"></colgroup>
                    <tr>
                        <td colspan="3" style="font-size: 20px;">医生坐诊业绩统计</td>
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
                <table width="30%" border="1">
                    <colgroup width="55%"></colgroup>
                    <colgroup width="45%"></colgroup>
                    <tr>
                        <td colspan="2" style="font-size: 20px;">柜员值班收入统计</td>
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
        </div>
    </div>
</div>
</body>
</html>
