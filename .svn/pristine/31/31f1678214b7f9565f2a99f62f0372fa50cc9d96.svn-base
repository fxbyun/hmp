<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div id="body" style="height: auto">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-search"></i> 查找
                        </div>
                        <div class="row-fluid">
                            <div class="span12" style="padding: 15px">
                                <form action="${ctx}/recharge/query" method="post" class="form-inline">
                                    <label>医生名称：</label>
                                    <input type="text" name="doctorName" class="input-medium" value="${doctorName}"/>&nbsp;&nbsp;
                                    <label>充值金额：</label>
                                    <select name="addMoney" class="input-small">
                                        <option value="${addMoney}">${addMoney}</option>
                                        <option value="50">50</option>
                                        <option value="100">100</option>
                                        <option value="150">150</option>
                                        <option value="200">200</option>
                                        <option value="250">250</option>
                                        <option value="300">300</option>
                                    </select>


                                    <label for="txtStartDate">搜索日期</label>
                                    <input type="text" name="startDate"
                                           value="<fmt:formatDate value="${startDate}" pattern="yyyy/MM/dd" />"
                                           class="form-control form_date"
                                           id="txtStartDate" style="width:120px;" readonly>
                                    至
                                    <input type="text" name="endDate" style="width:120px;"
                                           value="<fmt:formatDate value="${endDate}" pattern="yyyy/MM/dd" />"
                                           class="form-control form_date" id="txtEndDate" readonly>

                                    <input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
                                    <button type="submit" class="btn btn-info" id="btn_search">查找</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-group"></i> 充值列表
                        </div>
                        <div class="row-fluid">
                            <div class="span12">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th data-field="index" data-formatter="formatter">序号</th>
                                        <th>医生姓名</th>
                                        <th>余额</th>
                                        <th>充值金额</th>
                                        <th>充值前金额</th>
                                        <th>下单日期</th>
                                        <th>支付日期</th>
                                        <th>充值方式</th>
                                        <th>状态</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.content}" var="r">
                                        <tr>
                                            <td>${r.id}</td>
                                            <td>${r.doctor.name}</td>
                                            <td>${r.afterMoney}</td>
                                            <td>${r.addMoney}</td>
                                            <td>${r.money}</td>
                                            <td><fmt:formatDate value="${r.createDate}" pattern="yyyy/MM/dd"/></td>
                                            <td><fmt:formatDate value="${r.updateDate}" pattern="yyyy/MM/dd"/></td>
                                            <td>${r.rechargeWay}</td>
                                            <td><c:if test="${r.havePay == 'PAY'}">支付成功</c:if></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="11" style="text-align: right;margin-right: 10px">
                                            第&nbsp;${page.number + 1}&nbsp;页/共&nbsp;${page.totalPages}&nbsp;页--总数据${page.totalElements}条
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="11">
                                            <div id="pagination"></div>
                                        </td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <footer class="footer">
        <h5 class="transparent-text center">
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备15089657号-2</a></div>
        </h5>
    </footer>
</div>

<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap-paginator.js"></script>
<script type="text/javascript" >
    var options = {
        currentPage: ${page.number + 1},
        totalPages: ${page.totalPages},
        numberOfPages: 8,
        alignment: "center",
        field: 'Number',
        title: 'Number',
        formatter: function (value, row, index) {
            return index+1;
        },
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return  page;
            }
        },
        onPageClicked:function(event, originalEvent, type, page){
            $("#pageNo").val(page - 1);
            $("#btn_search").click();
        }
    }
   $(function () {
       $('#pagination').bootstrapPaginator(options);

       <!--时间插件加载-->
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
   })
</script>

