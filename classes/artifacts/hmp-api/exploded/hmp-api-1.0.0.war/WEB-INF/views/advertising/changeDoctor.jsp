<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/10
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>选择医生</title>
    <script type="application/javascript">
        var adcertPicAndTextSize = "${picAndTextCount}";
    </script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/adv.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/changeDoctor.js" type="js"/>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });
        })
    </script>

</head>
<body style="background-color: #fff;">
<div class="doctor-info">
    <ul class="clearfix nav" id="doctor_list">
        <%--@elvariable id="doctorList" type="java.util.List"--%>
        <c:forEach items="${doctorList}" var="doc" varStatus="status">
            <li data-toggle="tab" id="${doc.id}" onclick="fn_ClickRegistrTmp(${doc.id})">
                <div class="text-center">
                        <%--@elvariable id="docHeadUrl" type="java.util.List"--%>
                    <c:if test="${empty docHeadUrl[doc.id]}">
                        <img src="${ctx}/temp/doctor.jpg" title="医生头像" width="80" height="80">
                    </c:if>

                    <c:if test="${not empty docHeadUrl[doc.id]}">
                        <img src="${ctx}/temp/${docHeadUrl[doc.id]}" title="医生头像" width="80" height="80">
                    </c:if>

                </div>
                <p class="text-center" style="margin-bottom: 5px;"><label style="font-size: 16px;">${doc.name}</label>
                </p>
                <p style="padding: 0 5px 5px;">
                    <label>擅长领域：</label>
                    <span style="color: #666;">${doc.specialty}</span>
                </p>
            </li>
        </c:forEach>


    </ul>
    <div class="clearfix" style="margin-top: 15px;">
        <div class="col-xs-8 col-sm-8 text-right btn-width">
            <%--<button class="btn btn-success" type="button" onclick="fn_ClickRegistrTmp()">确认</button>--%>
            <button id="btnClose" class="btn btn-default" type="button">关闭</button>
        </div>
        <div class="col-xs-4 col-sm-4" style="padding: 0;">
            <ul style="float:right;">
                <li class="active" data-toggle="tab" style="margin-right: 10px;">
                    <button type="button" class="btn btn-default" onclick="fn_LoadRpList(${page.number - 1})">上一页
                    </button>
                </li>
                <li data-toggle="tab">
                    <button type="button" class="btn btn-default"
                            onclick="fn_LoadRpList(${page.number + 1},${page.totalPages})">下一页
                    </button>
                </li>
            </ul>


        </div>
    </div>

</div>
<script>
    $(function () {

    })
    //选择医生点击确定
    function fn_ClickRegistrTmp(doctorId) {
        //var doctorId = $("#doctor_list").children(".active").attr("id");
        if (doctorId == undefined) {
            layer.msg("请选择一个医生！");
            return false;
        }
        var index = parent.layer.getFrameIndex(window.name);
        fn_ClickRegistr("", index, doctorId);
    }

    function fn_GoDetail(page) {
        var url = "${ctx}/advertising/changeDoctor?page={0}".format(page);
        location.href = url;
    }

    function fn_LoadRpList(page, totalPage) {
        if (page + 1 > totalPage) {
            layer.msg("这是是最后一页了，已经没有下一页了！");
            return;
        }
        if (page < 0) {
            layer.msg("这已经是第一页了");
            return;
        }
        fn_GoDetail(page)
    }

    /**
     * 点击 挂号按钮 打开 挂号刷卡窗口
     */
    function fn_ClickRegistr(msg, layerIndex, doctorId) {

        parent.layer.open({
            type: 2,
            title: ['排队挂号', 'font-size:35px; height:60px; padding:8px 10px; background-color:#529b47; color:#fff;'],
            area: ['800px', '550px'],
            offset: ['600px', '150px'],
            //offset: '10%',
            scrollbar: false,
            move: false,
            closeBtn: 0,
            content: ctx + '/advertising/registration?msg={0}&doctorId={1}'.format(msg, doctorId)
            //end:parent.layer.close(layerIndex)
        });


        setTimeout(function () {
            parent.layer.close(layerIndex)
        }, 500)
    }

</script>
</body>
</html>
