<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/28
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingsix">
            <h4 class="panel-title" href="#collapsesix" role="button" data-toggle="collapse"
                data-parent="#accordion" aria-expanded="false" aria-controls="collapsesix">
                <img src="${ctx}/assets/styles/images/history.png"/>

                <p>历史检查项目</p>
                <a class="pull-right"> <i class="fa fa-angle-right"></i> </a>
            </h4>
        </div>
        <div id="collapsesix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingsix">
        </div>
    </div>
</div>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/prescription/prescription.js" type="js"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
<script type="text/javascript">
    function fn_LoadPhyOverview(page, month) {
        var url = '/fragment/phyList';
        if (month)
            $('#collapsesix').load(url, {'page': page, 'month': month});
        else if (page)
            $('#collapsesix').load(url, {'page': page});
        else
            $('#collapsesix').load(url);
    }

    $('#collapsesix').on('show.bs.collapse', fn_LoadPhyOverview());
</script>