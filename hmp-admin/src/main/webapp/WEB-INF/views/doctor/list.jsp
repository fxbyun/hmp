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
                                <form action="${ctx}/doctor/query" method="post" class="form-inline">
                                    <label>门诊名称：</label>
                                    <input type="text" name="outName" class="input-medium" value="${outName}"/>&nbsp;&nbsp;
                                    <label>医生姓名：</label>
                                    <input type="text" name="doctorName" class="input-small" value="${doctorName}"/>&nbsp;&nbsp;
                                    <label>状态：</label>
                                    <select name="status" class="input-small">
                                        <option value=""></option>
                                        <option value="Normal" <c:if test="${status == 'Normal'}">selected</c:if>>正常</option>
                                        <option value="Disabled" <c:if test="${status == 'Disabled'}">selected</c:if>>锁定</option>
                                        <option value="Committed" <c:if test="${status == 'Committed'}">selected</c:if>>未审核</option>
                                    </select>
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
                            <i class="icon-group"></i> 医生列表
                        </div>
                        <div class="row-fluid">
                            <div class="span12">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>门诊名称</th>
                                        <th>地址</th>
                                        <th>医生姓名</th>
                                        <th>性别</th>
                                        <th>手机</th>
                                        <th>注册日期</th>
                                        <th>积分</th>
                                        <th>状态</th>
                                        <th>推荐人</th>
                                        <th>余额</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.content}" var="d">
                                        <tr>
                                            <td>${d.outpatientService}</td>
                                            <td>${d.province}${d.city}${d.area}${d.businessAddr}</td>
                                            <td>${d.name}</td>
                                            <td><c:if test="${d.gender == 'Male'}">男</c:if><c:if test="${d.gender == 'Female'}">女</c:if></td>
                                            <td>${d.mobile}</td>
                                            <td><fmt:formatDate value="${d.createOn}" pattern="yyyy/MM/dd"/></td>
                                            <td>${d.integral}</td>
                                            <td>
                                                <c:if test="${d.status == 'Normal'}">正常</c:if>
                                                <c:if test="${d.status == 'Committed'}">未审核</c:if>
                                                <c:if test="${d.status == 'Disabled'}">审核不通过</c:if>
                                            </td>
                                            <td>${d.recommender}-${d.recommendMobile}</td>
                                            <td>
                                                <c:if test="${empty d.msgMoney }">0</c:if>
                                                <c:if test="${not empty d.msgMoney }">${d.msgMoney.deposit}</c:if>
                                            </td>
                                            <td>
                                                <a href="${ctx}/doctor/view/${d.id}">查看</a> &nbsp;&nbsp;
                                                <a href="${ctx}/doctor/edit/${d.id}">修改</a>
                                            </td>
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
            $("#pageNo").val(page);
            $("#btn_search").click();
        }
    }
    $('#pagination').bootstrapPaginator(options);
</script>