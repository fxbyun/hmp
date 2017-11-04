<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springside.org.cn/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="body" style="height: auto">
    <section id="main">
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-box">
                        <div class="content-box-header">
                            <i class="icon-user"></i> 新增药方库
                        </div>
                        <div class="row-fluid">
                            <div style="width: 70%;margin: 10px auto;">
                                <form:form action="${ctx}/rplib/save" cssClass="form-horizontal" method="post" modelAttribute="rplib" id="frmRp">
                                    <form:hidden path="id"/>
                                    <table border="0" width="100%">
                                        <tr>
                                            <td align="right" width="100px"><b>药方类别: &nbsp;</b></td>
                                            <td colspan="2">
                                                <form:select path="categoryId" items="${categories}" itemValue="id" itemLabel="name" cssClass="form-control" id="sltCategory"/> &nbsp;&nbsp;
                                                <button onclick="fn_EditCategory()" type="button" class="btn btn-info btn-sm">修改类别</button> &nbsp;&nbsp;
                                                <button onclick="fn_AddCategory()" type="button" class="btn btn-success btn-sm"> 新增类别</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>药方分类: &nbsp;</b></td>
                                            <td colspan="2"><form:bsradiobuttons path="medicineType" items="${medicineTypes}" readonly="true" labelCssClass="inline"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>适用病症: &nbsp;</b></td>
                                            <td width="120px"><button onclick="fn_SelectDiagnosis()" type="button" class="btn btn-info btn-sm">选择诊断结果</button></td>
                                            <td><form:input path="diagnosis" cssClass="" id="txtDiagnosis" placeholder="适用病症"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>药方名称: &nbsp;</b></td>
                                            <td colspan="2"><form:input path="name" cssClass="form-control required" id="txtName" placeholder="请输入名称"/></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>药方详情: &nbsp;</b></td>
                                            <td colspan="2"><form:input path="remark" cssClass="form-control" id="txtspec"></form:input></td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>用药清单: &nbsp;</b></td>
                                            <td colspan="2">
                                                <div id="divItems" class="label-box">
                                                    <c:forEach var="item" items="${rplib.prescriptionLibItemList}">
                                                        <span class="tag" id="rpMedicineTag_${item.medicineId}">${item.medicineName}&nbsp;${item.qty}${medicineUnits[item.unit]}x${item.copies}份
                                                            <input name="medicineIds" value="${item.medicineId}" type="hidden">
                                                            <input name="companyIds" value="${item.companyId}" type="hidden">
                                                            <input name="medicineQty" value="${item.qty}" type="hidden">
                                                            <input name="medicineRate" value="${item.rate}" type="hidden">
                                                            <input name="medicineUnit" value="${item.unit}" type="hidden">
                                                            <input type="hidden" name="medicineCopies" value="${item.copies}" />
                                                            <input type="hidden" name="medicineUseModes" value="${item.useMode}" />
                                                            <input type="hidden" name="medicineHasUsages" value="${item.hasUsage}" />
                                                            <a href="javascript:" onclick="fn_ShowEditMedicine(${item.medicineId})"><i class="icon-cog"></i></a>
                                                            <a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i class="icon-remove"></i></a>
                                                        </span>
                                                    </c:forEach>
                                                </div>
                                                <div id="divMedicines" class="medicine-label-box"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" style="text-align: center">
                                                <button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> 保存药方</button>
                                                <a href="${ctx}/rplib/list" class="btn btn-default">返回列表</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" style="text-align: center">
                                                <h5 class="text-danger">${msg}</h5>
                                            </td>
                                        </tr>
                                    </table>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="permit"></div>
    </section>
    <footer class="footer">
        <h5 class="transparent-text center">
            <div>©2015 深圳市乔北科技有限责任公司版权所有 <a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action">粤ICP备15089657号-2</a></div>
        </h5>
    </footer>
</div>
<script type="text/javascript">
    function fn_LoadCategories() {
        var sel = $('#sltCategory').val();
        $.getJSON("${ctx}/rpCate", function (data) {
            $('#sltCategory').empty();
            $.each(data, function (i, n) {
                $('#sltCategory').append('<option value="{0}">{1}</optioin>'.format(n.id, n.name));
            })
            $('#sltCategory').val(sel);
        })
    }
    function fn_AddCategory() {
        layer.open({
            type: 2,
            title: '新增药方类别',
            area: ['500px', '320px'],
            scrollbar: false,
            content: '${ctx}/fragment/rpCate/add/',
            end: fn_LoadCategories
        });
    }

    function fn_EditCategory() {
        var sel = $('#sltCategory').val();
        if (sel) {
            layer.open({
                type: 2,
                title: '编辑药方类别',
                area: ['500px', '320px'],
                scrollbar: false,
                content: '${ctx}/fragment/rpCate/edit/' + sel,
                end: fn_LoadCategories
            });
        }
    }
    function fn_RemoveElement(ele) {
        $(ele).remove();
    }
    function fn_GetMedicineInfo(medicineId) {
        var $tag = $('#rpMedicineTag_' + medicineId);
        if ($tag.length == 0) return;
        return {
            'companyId': $tag.find('input[name="companyIds"]').val(),
            'qty': $tag.find('input[name="medicineQty"]').val(),
            'rate': $tag.find('input[name="medicineRate"]').val(),
            'unit': $tag.find('input[name="medicineUnit"]').val(),
            'copies': $tag.find('input[name="medicineCopies"]').val(),
            'useMode': $tag.find('input[name="medicineUseModes"]').val(),
            'hasUsage': $tag.find('input[name="medicineHasUsages"]').val()
        }
    }
    function fn_ShowSelectMedicine(medicineId) {
        var arr = $.map($('#divItems li input[name="medicineIds"]'), function (n, i) {
            return $(n).val();
        });
        var tmp = "" + medicineId;
        if ($.inArray(tmp, arr) != -1) {
            var index = layer.confirm("此药品已添加，是否修改已选药品用量", function () {
                layer.close(index);
                layer.open({
                    type: 2,
                    title: '选择药品',
                    area: ['400px', '350px'],
                    scrollbar: false,
                    content: '${ctx}/fragment/medicine/select/' + medicineId
                });
            });
        } else {
            layer.open({
                type: 2,
                title: '选择药品',
                area: ['400px', '350px'],
                scrollbar: false,
                content: '${ctx}/fragment/medicine/select/' + medicineId
            });
        }
    }
    function fn_LoadMedicinePage(type, page, name) {
        if (page == undefined) page = 0;
        if (name == undefined) name = "";
        var url = "${ctx}/fragment/medicines/"+type+"?page="+page+"&name="+name+"";
        $('#divMedicines').load(url);
    }
    function fn_SelectMedicine(data) {
        var arr = $.map($('#divItems span input[name="medicineIds"]'), function (n, i) {
            return $(n).val();
        });
        var tmp = "" + data.medicineId;
        var exist = ($.inArray(tmp, arr) != -1);
        var tag = "rpMedicineTag_" + data.medicineId;
        var ele = '<span class="tag" id="{0}">{1}&nbsp;{2}{3}x{4}份'.format(tag, data.medicineName, data.qty, data.unitLabel, data.copies) +
                '<input type="hidden" name="medicineIds" value="' + data.medicineId + '" />' +
                '<input type="hidden" name="companyIds" value="' + data.companyId + '" />' +
                '<input type="hidden" name="medicineQty" value="' + data.qty + '" />' +
                '<input type="hidden" name="medicineRate" value="' + data.rate + '" />' +
                '<input type="hidden" name="medicineUnit" value="' + data.unit + '" /> ' +
                '<input type="hidden" name="medicineCopies" value="' + data.copies + '" /> ' +
                '<input type="hidden" name="medicineUseModes" value="' + data.useMode + '" /> ' +
                '<input type="hidden" name="medicineHasUsages" value="' + data.hasUsage + '" /> ' +
                '<a href="javascript:" onclick="fn_ShowEditMedicine({0})"><i class="icon-cog"></i></a> '.format(data.medicineId) +
                '<a href="javascript:" onclick="fn_RemoveElement($(this).parent())"><i class="icon-remove"></i></a> ' +
                '</span>';
        if (exist) {
            var $tag = $("#" + tag);
            $tag.after(ele);
            $tag.remove();
        }
        else {
            $('#divItems').append(ele);
        }
    }
    function fn_ShowEditMedicine(medicineId) {
        layer.open({
            type: 2,
            title: '选择药品',
            area: ['400px', '350px'],
            scrollbar: false,
            content: '${ctx}/fragment/medicine/select/' + medicineId
        });
    }
    function fn_MedicineTypeChange() {
        var type = $('input[name="medicineType"]:checked').val();
        fn_LoadMedicinePage(type, 0);
    }
    function fn_SelectDiagnosis(){
        layer.open({
            type: 2,
            title: '选择诊断结果',
            area: ['780px', '450px'],
            scrollbar: false,
            content: '${ctx}/fragment/rplib/diagnosis'
        });
    }
    $(function () {
        $('input[name="medicineType"]').change(fn_MedicineTypeChange);
        fn_MedicineTypeChange();
    });
</script>