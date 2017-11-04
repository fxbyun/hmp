<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: 130%">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-search"></i> 诊后建议-查找
                        </div>
                        <div class="row-fluid">
                            <div class="span12" style="padding: 15px">
                                <form action="${ctx}/suggest/query" method="post" class="form-inline">
                                    <label>建议内容：</label>
                                    <input type="text" name="content" class="form-control" value="${content}"/>&nbsp;&nbsp;
                                    <label>诊断结果：</label>
                                    <input type="text" name="tagName" class="form-control" value="${tagName}"/>&nbsp;&nbsp;
                                    <input type="hidden" name="pageNo" id="pageNo"/>
                                    <button type="submit" class="btn btn-info" id="btn_search">查找</button>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="${ctx}/suggest/add/${s.id}" class="btn btn-info"><i class="icon-plus"></i>添加诊后建议</a>
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
                            <i class="icon-list"></i> 诊后建议列表
                        </div>
                        <div class="row-fluid">
                            <div class="span12">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th width="50px">序号</th>
                                        <th>建议内容</th>
                                        <th width="180px">诊断结果</th>
                                        <th width="70px"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${page.content}" var="s" varStatus="v">
                                        <tr>
                                            <td>${v.index + 1}</td>
                                            <td>${s.content}</td>
                                            <td>${s.tagName}</td>
                                            <td><a href="${ctx}/suggest/edit/${s.id}">查看/修改</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>
                                    <tr>
                                        <td colspan="4" style="text-align: right;margin-right: 10px">
                                            第&nbsp;${page.number + 1}&nbsp;页/共&nbsp;${page.totalPages}&nbsp;页--总数据${page.totalElements}条
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
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