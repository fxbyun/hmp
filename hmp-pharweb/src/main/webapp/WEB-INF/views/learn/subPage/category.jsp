<%--
  Created by IntelliJ IDEA.
  User: cih
  Date: 16/8/9
  Time: 下午3:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<script>
    $(function () {
        $(".pre-btn>a").click(function () {
            if ($(".tab-pane").is(".active")) {
                var num = $(this).attr("href");
                $(".pre-detail-nav>.nav>li").removeClass("active");
                var num2 = $(".pre-detail-nav>.nav>li>a");
                if (num == "#tabone") {
                    num = 1;
                } else if (num == "#tabtwo") {
                    num = 2;
                } else if (num == "#tabthree") {
                    num = 3;
                    $(".pre-detail-nav>.nav>li>a").eq(2).trigger("click");
                } else if (num == "#tabfour") {
                    num = 4;
                }

                if (num == 4) {
                    if (!utlis_CheackNameIsInput()) {
                        return false;
                    }
                }


                num2.eq(num - 1).parent().attr("class", "active");

            }

        });
    });

</script>

<%--药方名称和药方类别录入--%>
<p style="font-size: 18px; text-align: center; line-height: 3rem; color: #B8B8B8;">给药方起个名字吧</p>
<p class="pre-name-p">
    <span>药方名称：</span>
    <input class="form-control" name="name" type="text" placeholder="跪求个名字">
</p>

<p class="pre-name-p">
    <span>药方分类：</span>
    <span>

    <label for="medicineType1" class="radio-inline">
        <input id="medicineType1" name="medicineType" readonly="true"
               type="radio" value="Western" checked="checked" style="width: 20%; margin-top: 0.4em;">
        西药及中成药</label>
    <label for="medicineType2" class="radio-inline">
        <input id="medicineType2" name="medicineType" readonly="true"
               type="radio" value="Chinese" style="width: 20%; margin-top: 0.4em;">
        中草药</label>

 </span>
</p>

<p style="font-size: 18px; text-align: center; line-height: 3rem; color: #B8B8B8;">您能大概描述一下药方的作用吗</p>
<p class="pre-name-p">
    <span>药方详情：</span>
    <textarea id="txtspec" name="remark" class="form-control" rows="3"></textarea>

</p>


<p style="font-size: 18px; text-align: center; line-height: 3rem; margin-top:3rem; color: #B8B8B8;">类别可以助您快速找到药方噢</p>

<p class="pre-name-p">
    <span>药方类别：</span>
    <select name="categoryId" class="form-control" title="" id="medCategorySelect">
        <c:forEach var="cate" items="${categories}">
            <option value="${cate.id}"
                    <c:if test="${cate.id==categoryId}">selected</c:if>>${cate.name}</option>
        </c:forEach>
    </select>

</p>
<div class="pre-cate">
    <a href="#" onclick="openMobileCategoryManagerWinDow('${ctx}/learn/subPage/categoryManage')">新增/修改/删除</a>
</div>
<%-- 按钮 --%>
<div class="text-center pre-btn" style="margin: 10%;">
    <a href="#tabtwo" data-toggle="tab" class="btn btn-success">上一步</a>
    <a href="#tabfour" data-toggle="tab" class="btn btn-success">下一步</a>
    <a href="#tabfour" data-toggle="tab">跳过</a>
</div>
<%-- 按钮 END --%>