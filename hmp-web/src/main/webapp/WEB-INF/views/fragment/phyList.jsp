<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/28
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>

<script type="text/javascript">
    $(function () {
        $('input[name="month"]').change(function () {
            var month = $('input[name="month"]:checked').val();
            fn_LoadPhyOverview(0, month)
        });
    });
</script>
<div class="panel-body text-center">
    <div class="btn-group" data-toggle="buttons">
        <div class="btn-group" data-toggle="buttons">
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2015/12" autocomplete="off">
                15年十二月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/01" autocomplete="off">
                16年一月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/02" autocomplete="off">
                16年二月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/03" autocomplete="off">
                16年三月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/04" autocomplete="off">
                16年四月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/05" autocomplete="off">
                16年五月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/06" autocomplete="off">
                16年六月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/07" autocomplete="off">
                16年七月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/08" autocomplete="off">
                16年八月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/09" autocomplete="off">
                16年九月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/10" autocomplete="off">
                16年十月</label>
            <label class="btn btn-success ">
                <input type="radio" name="month" value="2016/11" autocomplete="off">
                16年十一月</label>
        </div>
    </div>
    <button class="btn btn-warning " onclick="fn_Old_LoadPatientEmrList();return false">查看旧数据</button>
</div>
<%-- 添加的检查项目需求  --%>
<div class="doctors-action" style="padding-top: 0;">
    <div class="pt-items" style="min-height: 280px;">
        <ul class="nav nav-pills">
            <li class="active"><a href="#" class="btn btn-default">B超</a></li>
            <li><a href="#" class="btn btn-default">胃镜</a></li>
        </ul>
        <div id="detailTable" style="margin-top: 10px;">
            <%@include file="../newPage/sunPage/sunTable.jsp" %>
        </div>
    </div>
</div>
<%-- END --%>
