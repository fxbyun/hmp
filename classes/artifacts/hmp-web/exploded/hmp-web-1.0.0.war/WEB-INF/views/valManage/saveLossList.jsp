<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/20
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>添加损耗单</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/validityManage/validity.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery.form/jquery.form.js" type="js"/>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage" class="btn btn-default">用药分析</a></li>
            <li><a href="${ctx}/message" class="btn btn-default" onclick="isDebug()">短信发送</a></li>
            <li><a href="${ctx}/rp" class="btn btn-default">我的药方</a></li>
            <li><a href="${ctx}/rplib" class="btn btn-default">药方库</a></li>
            <li><a href="${ctx}/config/symptom" class="btn btn-default">数据整理</a></li>
            <li><a href="${ctx}/appointment" class="btn btn-default">预约挂号</a></li>
            <li class="active"><a href="${ctx}/validityManage" class="btn btn-default">有效期管理</a></li>
            <li><a href="${ctx}/financial" class="btn btn-default">财务统计</a></li>
            <ul class="navigation-sub">
                <li><a href="${ctx}/validityManage" class="btn btn-default">库存管理</a></li>
                <li class="active"><a href="${ctx}/validityManage/lossRecord" class="btn btn-default">损耗记录</a></li>
                <li><a href="${ctx}/validityManage/entryRecord" class="btn btn-default">入货库记录</a></li>
            </ul>
        </ul>
        <div class="validityManage saveValList saveLossList">
            <div class="val-list-h4"><h4>添加损耗单</h4></div>
            <form id="saveLoss" action="${ctx}/IaiLossManage/saveLoss" method="post">
                <input type="text" name="iaiLossId" id="iaiLossId" value="${iaiLoss.id}" style="display: none;"/>
                <div class="clearfix" style="margin-top: 15px;">
                    <div class="pull-left" style="width: 50%; padding-right: 5%;">
                        <div class="form-group text-right">
                            <span>报损时间：</span><input type="text" name="createDate" readonly value="<c:if test="${empty iaiLoss.createDate}"><fmt:formatDate value="${now}" pattern="yyyy/MM/dd"/></c:if><c:if test="${not empty iaiLoss.createDate}"><fmt:formatDate value="${iaiLoss.createDate}" pattern="yyyy/MM/dd"/></c:if>" class="form-control form_day"
                                                     style="display: inline-block; width: 72%;"><br>
                        </div>
                        <div class="form-group text-right">
                            <span>报损人：</span><input type="text" value="${iaiLoss.whoCreate}" name="whoCreate" class="form-control"
                                                    style="display: inline-block; width: 72%;">
                        </div>
                    </div>
                    <div class="pull-right" style="width: 50%; padding-right: 5%;">
                        <span style="vertical-align: top;">备注：</span><textarea name="remark" class="form-control">${iaiLoss.remark}</textarea>
                    </div>
                </div>
            </form>
            <div class="val-list-medicine">
                <div class="clearfix">
                    <input type="text" id="medNameInput" class="form-control"
                           style="display: inline-block; width: 15%;"
                           placeholder="输入您要查询的药品名称">
                    <button class="btn btn-defalut" type="button" onclick="searchLossMed()">搜索</button>
                    <a href="javascript:" onclick="addMedicine()" class="btn btn-success">新增药品</a>
                    <div class="list-medicine clearfix">
                        <%--你是个滚轮，向左滚--%>
                        <a class="prev fa fa-chevron-left" onclick="scrollPage('left')"></a>
                            <div class="list-tab" id="iaiIntoMed">

                            </div>
                        <%--你是个滚轮，向左滚--%>
                        <a class="next fa fa-chevron-right" onclick="scrollPage('right')"></a>
                    </div>
                </div>
            </div>
            <table class="rp-table" width="100%">
                <colgroup width="5%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="11%"></colgroup>
                <colgroup width="10%"></colgroup>
                <colgroup width="7%"></colgroup>
                <colgroup width="7%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="5%"></colgroup>
                <colgroup width="6%"></colgroup>
                <colgroup width="6%"></colgroup>
                <colgroup width="8%"></colgroup>
                <colgroup width="7%"></colgroup>
                <thead>
                <tr style="background-color: #eeeeee;">
                    <th>序号</th>
                    <th>药品名称</th>
                    <th>药厂</th>
                    <th>条码</th>
                    <th>有效期</th>
                    <th>进货价(元)</th>
                    <th>零售价(元)</th>
                    <th>产品数量</th>
                    <th>预警线</th>
                    <th>规格</th>
                    <th>统计单位</th>
                    <th>成本总额</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${lossDetails}" var="detail" varStatus="status">
                    <tr onclick="showLossDetail(${detail.medicine.id},${detail.iaiLoss.id},${detail.id},'saveValListPage')">
                        <td>${status.index+1}</td>
                        <td>${detail.medicine.name}</td>
                        <td><span class="val-text">${companyMap[detail.id]}</span></td>
                        <td>${detail.barCode}</td>
                        <td><fmt:formatDate value="${detail.validityDate}" pattern="yyyy/MM/dd" /></td>
                        <td>${detail.bayingPrice}</td>
                        <td>${detail.medicine.price}</td>
                        <td>${detail.totalNumber}</td>
                        <td>${detail.warnLine}</td>
                        <td>${detail.medicine.standard}</td>
                        <td>${medicineUnits[detail.medicine.unit]}</td>
                        <td>${detail.bayingPrice*detail.totalNumber}</td>
                        <td><a href="javascript:" onclick="deleteLossDetail(this,${detail.id})">删除</a></td>
                    </tr>
                </c:forEach>


                </tbody>

            </table>

            </div>
        <div class="text-center">
            <button type="button" class="btn btn-default">上一页</button>
            <button type="button" class="btn btn-default">下一页</button>
            <button type="button" id="btnSaveLoss" class="btn btn-success" style="width: 80px;">保存</button>
            <button type="button" class="btn btn-success">打印耗损单</button>
            <button type="button" class="btn btn-default" style="width: 80px;" onclick="javascript:history.go(-1);">
                返回
            </button>
        </div>
    </div>
</div>
<script>
    $(function () {
        var iaiLossId = $("#iaiLossId").val();
        selectExpireMed("",0,iaiLossId);

        $("#btnSaveLoss").click(function () {
            $("#saveLoss").ajaxSubmit({
                url:"${ctx}/IaiLossManage/saveLoss",
                success:function (data) {
                    if(data.success==true){
                        layer.msg("损耗单添加成功！")
                    }else {
                        layer.msg("损耗单添加失败！")
                    }
                }
            });
        });




    })

    function selectExpireMed(name, page,iaiLossId) {
        if (!name) {
            name = "";
        }
        if (!page) {
            page = 0;
        }
        var url;
        if(iaiLossId==undefined){
            url = "/IaiLossManage/bombBox/expireMedList?name={0}&page={1}".format(name, page);
        }else {
            url = "/IaiLossManage/bombBox/expireMedList?name={0}&page={1}&iaiLossId={2}".format(name, page,iaiLossId);
        }

        $("#iaiIntoMed").load(url);
    }

    
    //弹出添加损耗的编辑窗口
    function showLossDetail(medPrivateId,lossId,lossDetailId) {
        var url="${ctx}/IaiLossManage/bombBox/selectLossItem?medPrivateId={0}&lossId={1}&lossDetailId={2}".format(medPrivateId,lossId,lossDetailId);
        layer.open({
            type: 2,
            maxmin: false,
            title: '编辑药品',
            area: ['700px', '300px'],
            scrollbar: false,
            content: url
        });
    }
    
    function deleteLossDetail(even,lossDetailId) {
        event.stopPropagation();//阻止事件冒泡
        $(even).attr("onclick","");
        layer.confirm("你确定要删除该损耗药品？",function () {
            $.postJSON("${ctx}/IaiLossManage/bombBox/delLossItem",{"lossDetailId":lossDetailId},function (data) {
                if(data.success==true){
                    layer.msg("损耗药品已成功删除！")
                }else {
                    layer.msg("损耗药品删除失败！");
                }

            })
            $(even).parent().parent().remove();

        })
    }

    function searchLossMed() {
        var medName = $("#medNameInput").val();
        var iaiLossId = $("#iaiLossId").val();
        selectExpireMed(medName,0,iaiLossId);
    }





</script>




</body>
</html>

