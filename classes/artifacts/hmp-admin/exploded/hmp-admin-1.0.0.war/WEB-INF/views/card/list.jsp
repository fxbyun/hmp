<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <form action="${ctx}/card/query" method="post" class="form-inline">
                                    <label>卡号：</label>
                                    <input type="text" name="cardNo" class="input-medium" value="${cardNo}"/>&nbsp;&nbsp;
                                    <label>识别码：</label>
                                    <input type="text" name="udid" class="input-medium" value="${udid}"/>&nbsp;&nbsp;
                                    <label>状态：</label>
                                    <select name="status" class="input-small">
                                        <option value=""></option>
                                        <option value="Normal" <c:if test="${status == 'Normal'}">selected</c:if>>未使用</option>
                                        <option value="Used" <c:if test="${status == 'Used'}">selected</c:if>>已使用</option>
                                    </select>
                                    <input type="hidden" name="pageNo" id="pageNo"/>
                                    <button type="submit" class="btn btn-info" id="btn_search">查找</button>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a class="btn btn-info" href="${ctx}/card/add">添加</a>
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
                            <i class="icon-list"></i> 卡片列表
                        </div>
                        <div class="row-fluid">
                            <div class="span12">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>卡号</th>
                                        <th>识别码</th>
                                        <th>状态</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.content}" var="c">
                                        <tr>
                                            <td>${c.cardNo}</td>
                                            <td>${c.udid}</td>
                                            <td>${cardStatus[c.status]}</td>
                                            <td><a href="${ctx}/card/view/${c.id}">查看</a></td>
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
        <%--pageUrl: function(type, page, current){--%>
            <%--return "${ctx}/card/list/" + page;--%>
        <%--},--%>
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