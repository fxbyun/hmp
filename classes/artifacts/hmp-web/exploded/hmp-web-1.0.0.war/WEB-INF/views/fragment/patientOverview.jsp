<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
    <div class="panel panel-default" style="display: none">
        <div class="panel-heading" role="tab" id="headingone">
            <h4 class="panel-title" href="#collapseone" role="button" data-toggle="collapse"
                data-parent="#accordion" aria-expanded="true" aria-controls="collapseone">
                <img src="${ctx}/assets/styles/images/01.png" />

                <p>血压报告</p>
                <a class="pull-right">
                    <i class="fa fa-angle-right"></i>
                </a>
            </h4>
        </div>
        <div id="collapseone" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingone">
            <div class="panel-body">
                <%--<div id="blood-press" style="min-width:900px;height:400px"></div>--%>
                暂无数据
            </div>
        </div>
    </div>
    <div class="panel panel-default" style="display: none">
        <div class="panel-heading" role="tab" id="headingtwo">
            <h4 class="panel-title" href="#collapsetwo" role="button" data-toggle="collapse"
                data-parent="#accordion" aria-expanded="true" aria-controls="collapsetwo">
                <img src="${ctx}/assets/styles/images/02.png" />

                <p>心率报告</p>
                <a class="pull-right">
                    <i class="fa fa-angle-right"></i>
                </a>
            </h4>
        </div>
        <div id="collapsetwo" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingtwo">
            <div class="panel-body">
                <%--<div id="hr-report" style="min-width:900px;height:400px"></div>--%>
                暂无数据
            </div>
        </div>
    </div>
    <div class="panel panel-default" style="display: none">
        <div class="panel-heading" role="tab" id="headingthree">
            <h4 class="panel-title" href="#collapsethree" role="button" data-toggle="collapse"
                data-parent="#accordion" aria-expanded="false" aria-controls="collapsethree">
                <img src="${ctx}/assets/styles/images/03.png" />

                <p>血糖报告</p>
                <a class="pull-right">
                    <i class="fa fa-angle-right"></i>
                </a>
            </h4>
        </div>
        <div id="collapsethree" class="panel-collapse collapse " role="tabpanel"
             aria-labelledby="headingthree">
            <div class="panel-body">
                <%--<div id="glucose-report" style="min-width:900px;height:400px"></div>--%>
                暂无数据
            </div>
        </div>
    </div>
    <div class="panel panel-default" style="display: none">
        <div class="panel-heading" role="tab" id="headingfour">
            <h4 class="panel-title" href="#collapsefour" role="button" data-toggle="collapse"
                data-parent="#accordion" aria-expanded="false" aria-controls="collapsefour">
                <img src="${ctx}/assets/styles/images/04.png" />

                <p>其他报告</p>
                <a class="pull-right">
                    <i class="fa fa-angle-right"></i>
                </a>
            </h4>
        </div>
        <div id="collapsefour" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingfour">
            <div class="panel-body">
                暂无数据
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingfive">
            <h4 class="panel-title" href="#collapsefive" role="button" data-toggle="collapse"
                data-parent="#accordion" aria-expanded="false" aria-controls="collapsefive">
                <img src="${ctx}/assets/styles/images/history.png" />

                <p>历史病历</p>
                <a class="pull-right"> <i class="fa fa-angle-right"></i> </a>
            </h4>
        </div>
        <div id="collapsefive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingfive">
        </div>
    </div>
</div>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/prescription/prescription.js" type="js"/>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
<script type="text/javascript">
    function fn_LoadPatientEmrList(page, month) {
        var url = '/fragment/patient/${patient.uid}/emrList?refer=${refer}';
        if (month)
            $('#collapsefive').load(url, {'page': page, 'month': month});
        else if (page)
            $('#collapsefive').load(url, {'page': page});
        else
            $('#collapsefive').load(url);
    }

    function fn_Old_LoadPatientEmrList(page, month) {
        var url = '/oldPatient/oldPtDetail/${patient.uid}/emrList?refer=${refer}';
        if (month)
            $('#collapsefive').load(url, {'page': page, 'month': month});
        else if (page)
            $('#collapsefive').load(url, {'page': page});
        else
            $('#collapsefive').load(url);
    }

    $('#collapsefive').on('show.bs.collapse', fn_LoadPatientEmrList());
</script>
