<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<script type="text/javascript">
    $(function () {
        $('input[name="month"]').change(function () {
            var month = $('input[name="month"]:checked').val();
            fn_Old_LoadPatientEmrList(0, month)
        });
    });

    var medDataSum = {};
    var medChinaDataSum = {};
    var addEmrId = "";
    var addType = "";

    function setEmrId(id, addType1) {
        addEmrId = "emrId" + id;
        addType = addType1;
    }


    //新增药方
    function fn_AddRp() {

    }

    function load_old_emr_list() {
        parent.fn_LoadPatientEmrList();
        return false;
    }

</script>
<div class="panel-body text-center">
    <div class="btn-group" data-toggle="buttons">
        <c:forEach var="m" varStatus="status" items="${months}">
            <label class="btn btn-success <c:if test='${month==m}'>active</c:if>">
                <input type="radio" name="month" value="<fmt:formatDate value='${m}' pattern='yyyy/MM'/>"
                       autocomplete="off"
                       <c:if test='${month==m}'>checked</c:if>>
                <fmt:formatDate value='${m}' pattern='yy年MMM' />
            </label>
        </c:forEach>
    </div>
    <button class="btn btn-success " type="button" onclick=" load_old_emr_list();">查看新数据</button>
</div>
<c:if test="${empty emrPage.content}">无病历记录</c:if>
<c:forEach var="emr" items="${emrPage.content}">
    <div class="history-report">
        <h3><fmt:formatDate value="${emr.createTime}" pattern="yyyy/MM/dd HH:mm" />&nbsp;</h3>

        <div class="talk-content">
            <h4>患者主诉</h4>

            <p>无
            </p>
            <h4>生命体症</h4>

            <p>
                无
            </p>
            <h4>诊断结果</h4>

            <p>
                    ${emr.zhenDuan}
            </p>
            <h4>处方---西药及中成药房 &nbsp;&nbsp; </h4>

            <div class="content">
                <div>
                    <div style="width: 80%; line-height: 30px;">
                            ${ emr.chuFang}
                    </div>
                </div>
            </div>
            <h4>收费金额：${emr.shouFei}</h4>
        </div>
    </div>
</c:forEach>
<div class="text-center">
    <c:if test="${emrPage.number > 0}">
        <button type="button"
                onclick="parent.fn_Old_LoadPatientEmrList(${emrPage.number - 1},'<fmt:formatDate value='${month}'
                                                                                                 pattern='yyyy/MM'/>')"
                class="btn btn-success"><i class="fa fa-chevron-left"></i>上一页
        </button>
    </c:if>
    <c:if test="${emrPage.number + 1 < emrPage.totalPages}">
        <button type="button"
                onclick="parent.fn_Old_LoadPatientEmrList(${emrPage.number + 1},'<fmt:formatDate value='${month}'
                                                                                                 pattern='yyyy/MM'/>')"
                class="btn btn-success"><i class="fa fa-chevron-right"></i>下一页
        </button>
    </c:if>
    &nbsp;&nbsp;&nbsp;&nbsp;
    第&nbsp;${emrPage.number + 1}&nbsp;页/共&nbsp;${emrPage.totalPages}&nbsp;页
</div>
<br />