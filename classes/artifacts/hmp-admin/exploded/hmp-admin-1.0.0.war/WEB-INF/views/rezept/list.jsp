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
                                <form action="${ctx}/rezept/query" method="post" class="form-inline">
                                    <input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
                                    <label>药方名称：</label>
                                    <input type="text" name="name" class="input-medium" value="${name}"/>&nbsp;&nbsp;
                                    <label>创建人：</label>
                                    <input type="text" name="createBy" class="input-medium" value="${createBy}"/>&nbsp;&nbsp;
                                    <button type="submit" class="btn btn-info" id="btn_search">查找</button>&nbsp;&nbsp;&nbsp;&nbsp;
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
                            <i class="icon-list"></i> 药方列表
                        </div>
                        <div class="row-fluid">
                            <div class="span12">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th width="150px">药方名称</th>
                                        <th width="80px">药方类别</th>
                                        <th width="100px">药方类型</th>
                                        <th>药方说明</th>
                                        <th width="80px">创建人</th>
                                        <th width="120px">创建时间</th>
                                        <th width="120px">共享状态</th>
                                        <th width="45px"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.content}" var="r">
                                        <tr>
                                            <td>${r.name}</td>
                                            <td>${r.categoryName}</td>
                                            <td>${medicineTypes[r.medicineType]}</td>
                                            <td>${r.remark}</td>
                                            <td>${r.doctorName}</td>
                                            <td><fmt:formatDate value="${r.createOn}" pattern="yyyy/MM/dd HH:mm"/></td>
                                            <td><c:if test="${r.status == 'Normal'}">未共享</c:if><c:if test="${r.status == 'Used'}">已共享</c:if></td>
                                            <td><a href="${ctx}/rezept/view/${r.id}">查看</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="9" style="text-align: right;margin-right: 10px">
                                            第&nbsp;${page.number + 1}&nbsp;页/共&nbsp;${page.totalPages}&nbsp;页--总数据${page.totalElements}条
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="9">
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