<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cih
  Date: 16/8/9
  Time: 下午3:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="application/javascript">
    //如果当前页面能够找到hidderDiv 说明是在 我的药方详情里面去加载的本页面
    if ($("#hidderDiv")) {
        $("#westernsSelTag").html($("#hidderDiv").html());
        $("#hidderDiv").remove();
    }
    $(function () {
        $(".cleared").click(function () {
            $(".seach-input").val("");
            $(".seach").trigger("click");
        });
    })
</script>
<%--<div class="label-box" id="westernsSelTag"></div>--%>
<div id="divSymptomTags" class="medicine-label-box">
    <div class="tab-box-list west-boxs" id="western_span_tag_div">
        <c:forEach items="${westernsMedPage.content}" var="oneDia">
            <span class="tag" onclick="selMobileMed('${oneDia.id}')">${oneDia.name}</span>
        </c:forEach>
    </div>
    <div class="form-group text-center pre-btn pre-up-down">
        <c:choose>
            <c:when test="${westernsMedPage.number > 0}">
                <button type="button" class="btn btn-success" onclick="loadWesternsTag('${westernsMedPage.number-1}')">
                    上一页
                </button>
            </c:when>
            <c:otherwise>
                <button type="button" class="btn btn-default">上一页</button>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${westernsMedPage.number + 1 < westernsMedPage.totalPages}">
                <button type="button" class="btn btn-success" onclick="loadWesternsTag('${westernsMedPage.number+1}')">
                    下一页
                </button>
            </c:when>
            <c:otherwise>
                <button type="button" class="btn btn-default">下一页</button>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="form-group btn-boxs" style="padding: 0 10px;">
        <input type="text" class="form-control seach-input" placeholder="首字母或名称搜索"
               style="width: 45%;" id="westernsScanInput" value="${name}">
        <button class="btn btn-default cleared" type="button" style="margin-right: 1px;">
            <i class="fa fa-times"></i>
        </button>
        <button class="btn btn-default pull-left seach" type="button" onclick="loadWesternsTag()">搜索</button>
        <a href="#" onclick="openAddMedWind('Western')" class="btn btn-default pull-right"
           style="width: 24%;">新增药品</a>
    </div>
    <%-- 按钮 --%>
    <div class="text-center pre-btn">
        <a href="#tabthree" data-toggle="tab" class="btn btn-success">上一步</a>
        <button type="submit" class="btn btn-success" style="width: 20%;">保存</button>
    </div>
    <%-- 按钮 END --%>
</div>
