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
                                <form action="${ctx}/patient/query" method="post" class="form-inline">
                                    <input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
                                    <label>姓名：</label>
                                    <input type="text" name="name" class="input-small" value="${name}"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label>手机号：</label>
                                    <input type="text" name="mobile" class="input-medium" value="${mobile}"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <label>卡号：</label>
                                    <input type="text" name="uid" class="input-medium" value="${uid}"/>&nbsp;&nbsp;&nbsp;&nbsp;
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
                            <i class="icon-group"></i> 患者列表
                        </div>
                        <div class="row-fluid">
                            <div class="span12">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>姓名</th>
                                        <th>性别</th>
                                        <th>出生年月</th>
                                        <th>住址</th>
                                        <th>手机号</th>
                                        <th>卡号</th>
                                        <th>身份证号</th>
                                        <th>历史病症</th>
                                        <th>绑定微信</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.content}" var="p">
                                        <tr>
                                            <td>${p.name}</td>
                                            <td><c:if test="${p.gender == 'Male'}">男</c:if><c:if test="${p.gender == 'Female'}">女</c:if></td>
                                            <td><fmt:formatDate value="${p.birthday}" pattern="yyyy/MM/dd"/></td>
                                            <td>${p.province}${p.city}${p.area}${p.address}<></td>
                                            <td>${p.mobile}</td>
                                            <td>${p.uid}<></td>
                                            <td>${p.sfId}<></td>
                                            <td>
                                                <c:if test="${not empty p.patientTagList}">
                                                    <c:forEach items="${p.patientTagList}" var="tag">
                                                        ${tag.name} &nbsp;
                                                    </c:forEach>
                                                </c:if>
                                            </td>
                                            <td><c:if test="${p.wxId != null}">是</c:if><c:if test="${p.wxId == null}">否</c:if></td>
                                            <td><a href="${ctx}/patient/edit/${p.id}">查看/修改</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="10" style="text-align: right;margin-right: 10px">
                                            第&nbsp;${page.number + 1}&nbsp;页/共&nbsp;${page.totalPages}&nbsp;页--总数据${page.totalElements}条
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="10">
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