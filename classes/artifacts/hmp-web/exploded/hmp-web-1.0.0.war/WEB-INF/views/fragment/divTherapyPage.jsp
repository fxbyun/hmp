<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/9 0009
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="diagnose">

    <div class="medicine-label-box">
        <div id="divTherapy" class="tab-box-list first-box-list">
            <c:forEach items="${therapyList}" var="the">
                <span class="tag" onclick="selectPhy(this,${the.id})">${the.name}</span>
            </c:forEach>
        </div>

    </div>
    <div class="form-group btn-boxs" style="margin-top: 25px; margin-bottom: 0;">

        <div class="up-dpwn-bt" style="width: 100%;">

            <button type="button" onclick="preTherapy()" class="btn btn-info">上一批</button>
            <button type="button" onclick="nextTherapy()" class="btn btn-info">下一批</button>
            <div class="pull-right">
                <div class="input-group">
                    <input value="${helpCode}" type="text" id="TherapyOrHelpCode" class="form-control" placeholder="项目名称">
                    <span class="input-group-btn">
                        <button onclick="clean(this)" class="btn btn-primary" type="button"><i
                                class="fa fa-times"></i></button>
                        <button onclick="search()" class="btn btn-primary" type="button"
                                style="border-left: 1px solid #fff;"><i
                                class="fa fa-search"></i></button>
                    </span>
                </div>


                <button onclick="savetPhy()" type="button" class="btn btn-success"><i
                        class="fa fa-plus"></i> 添加理疗
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {

    })
    function clean(even) {
        $("#TherapyOrHelpCode").val("");
    }

    function search() {

        fn_LoadTherapyTag(0,$("#TherapyOrHelpCode").val());
    }

    function preTherapy() {

        fn_LoadTherapyTag(${page.number-1},$("#TherapyOrHelpCode").val());
    }

    function nextTherapy() {
        fn_LoadTherapyTag(${page.number+1},$("#TherapyOrHelpCode").val());
    }
</script>