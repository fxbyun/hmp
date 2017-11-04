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
                                <form action="${ctx}/apply/query" method="post" class="form-inline">
                                    <label>申请人：</label>
                                    <input type="text" name="applyName" class="input-medium" value="${applyName}"/>&nbsp;&nbsp;
                                    <label>药品名称：</label>
                                    <input type="text" name="name" class="input-medium" value="${name}"/>&nbsp;&nbsp;
                                    <label>状态：</label>
                                    <select name="status" class="input-small">
                                        <option value=""></option>
                                        <option value="Applied" <c:if test="${status == 'Applied'}">selected</c:if>>未审核</option>
                                        <option value="Verified" <c:if test="${status == 'Verified'}">selected</c:if>>已审核</option>
                                        <option value="Canceled" <c:if test="${status == 'Canceled'}">selected</c:if>>驳回</option>
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
                            <i class="icon-group"></i> 申请列表
                        </div>
                        <div class="row-fluid">
                            <div class="span12">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>药品名称</th>
                                        <th>药厂</th>
                                        <th>原单位</th>
                                        <th>新单位</th>
                                        <th>值</th>
                                        <th>申请人</th>
                                        <th>申请时间</th>
                                        <th>状态</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.content}" var="c">
                                        <tr>
                                            <td>${c.medicineName}</td>
                                            <td>${c.medicine.defaultCompany.name}</td>
                                            <td>${units[c.fromUnit]}</td>
                                            <td>${units[c.toUnit]}</td>
                                            <td>${c.rate}</td>
                                            <td>${c.applyBy}</td>
                                            <td><fmt:formatDate value="${c.applyOn}" pattern="yyyy/MM/dd HH:mm"/></td>
                                            <td> ${applyStatus[c.status]}</td>
                                            <td>
                                                <a href="${ctx}/apply/pass/${c.id}">通过</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a href="${ctx}/apply/cancel/${c.id}">驳回</a>
                                            </td>
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
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action" target="_blank">粤ICP备15089657号-2</a></div>
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