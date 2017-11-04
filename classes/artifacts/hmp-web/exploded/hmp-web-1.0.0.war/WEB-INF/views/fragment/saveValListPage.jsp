
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>

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


    <%----%>
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
    <tbody id="tb_page">
    <c:forEach items="${iaiDetails}" var="detail" varStatus="status">
        <tr onclick="saveMedicine(${detail.medicine.medicine.id},${detail.iaiInto.id},${detail.id},'saveValListPage')">
            <td>${status.index}</td>
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
            <td><a href="javascript:" onclick="deleteValListTr(this,${detail.id})">删除</a></td>
        </tr>
    </c:forEach>

    </tbody>

</table>
</div>
<div class="text-center">
    <button type="button" onclick="fn_LoadRpList(${page.number - 1})" class="btn btn-default">上一页</button>
    <button type="button" onclick="fn_LoadRpList(${page.number + 1},${page.totalPages})" class="btn btn-default">下一页</button>
    <button id="btnSaveIaiInto" type="button" class="btn btn-success" style="width: 80px;">保存</button>
    <button type="button" class="btn btn-success" id="printIaiInto">打印入库单</button>
    <button type="button" class="btn btn-default" style="width: 80px;" onclick="javascript:history.go(-1);">
        返回
    </button>
</div>
<script>

    $(function () {
        $("#btnSaveIaiInto").click(function () {
            $("#saveIaiInto").ajaxSubmit({
                url:"${ctx}/validityManage/saveIaiInto",
                success:function (data) {
                    if(data.success==true){
                        layer.msg("入库单添加成功！")
                    }else {
                        layer.msg("入库单添加失败！")
                    }
                }
            })
        });
    })

    function fn_LoadRpList(page,totalPage) {

        if(page+1>totalPage){
            layer.msg("这是是最后一页了，已经没有下一页了！");
            return;
        }
        if(page<0){
            layer.msg("这已经是第一页了");
            return;
        }

        fn_LoadIaiIntoDetailPage(${iaiInto.id},page);
    }

    function printIaiInto() {

    }
    
    
</script>