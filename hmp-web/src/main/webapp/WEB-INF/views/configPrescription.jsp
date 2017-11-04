<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>药方管理</title>
    <script>
        function fn_LoadPrescriptionLib() {
            var type = $('input[name="medicineType"]:checked').val();
            var cateId = $("#sltLibCate").val();
            $.getJSON("${ctx}/rplib/list?type={0}&cateId={1}".format(type,cateId) ,function(result){
                $('#tblRpLib').empty();
                $.each(result.data,function(i,n){
                    var ele = "<tr>" +
                                "<td><input type='checkbox' name='rplibIds' value='{0}'/>&nbsp;</td>".format(n.id) +
                                "<td>{0}</td>".format(n.name) +
                                "</tr>";
                    $('#tblRpLib').append(ele);
                });
            })
        }

        function fn_LoadPrescription() {
            var type = $('input[name="medicineType"]:checked').val();
            var cateId = $("#sltCate").val();
            $.getJSON("${ctx}/rp/list?type={0}&cateId={1}".format(type,cateId) ,function(result){
                $('#tblRp').empty();
                $.each(result.data,function(i,n){
                    var ele = "<tr>" +
                            "<td><input type='checkbox' name='rpIds' value='{0}'/>&nbsp;</td>".format(n.id) +
                            "<td>{0}</td>".format(n.name) +
                            "</tr>";
                    $('#tblRp').append(ele);
                });
            })
        }

        function fn_MedicineTypeChange() {
            fn_LoadPrescriptionLib();
            fn_LoadPrescription();
        }

        function fn_checkAllForRpLib(check){
            $('input[name="rplibIds"]').prop("checked", check);
        }
        function fn_checkAllForRp(check){
            $('input[name="rpIds"]').prop("checked", check);
        }

        function fn_addRP(){
            var ids="";
            $("input[name='rplibIds']:checked").each(function(){
                ids += $(this).val()+","
            })
            if(ids == ""){
                layer.alert("请选择数据！");
                return;
            }
            var cateId = $("#sltLibCate").val();
            $.getJSON("${ctx}/rplib/add?cateId={0}&ids={1}".format(cateId,ids) ,function(result){
                if(result.success){
                    layer.msg("药方已添加。");
                    location.reload();
                }else{
                    layer.msg(result.msg);
                }
            })
        }

        function fn_delRp(){
            var ids="";
            $("input[name='rpIds']:checked").each(function(){
                ids += $(this).val()+","
            })
            if(ids == ""){
                layer.alert("请选择数据！");
                return;
            }
            $.getJSON("${ctx}/rp/delete?ids={0}".format(ids) ,function(result){
                if(result.success){
                    layer.msg("药方已删除。");
                    fn_LoadPrescription();
                }
            })
        }

        $(function () {
            $("#nav-manage").addClass("active");
            $('input[name="medicineType"]').change(fn_MedicineTypeChange);
            $('input[name="medicineType"]').change();
            $('#sltLibCate').change(function(){
                fn_LoadPrescriptionLib();
            });
            $('#sltCate').change(function(){
                fn_LoadPrescription();
            });
        });
    </script>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage">用药分析</a></li>
            <li><a href="${ctx}/infro">信息设置</a></li>
            <li><a href="${ctx}/dm">常用药品</a></li>
            <li><a href="${ctx}/rp">我的药方</a></li>
            <li><a href="${ctx}/rplib">药方库</a></li>
            <li class="active"><a href="#">数据整理</a></li>
        </ul>
        <ul class="navigation navigation-sub">
            <li><a href="#">患者主诉整理</a></li>
            <li><a href="${ctx}/config/diagnosis">诊断结果整理</a></li>
            <li><a href="${ctx}/config/medicine">药品整理</a></li>
            <li class="active"><a href="#">药方整理</a></li>
        </ul>
        <div class="diagnose">
            <p>药方类型</p>
            <input type="radio" class="inline" value="Western" name="medicineType" checked="checked"/> 西药及中成药
            <input type="radio" class="inline" value="Chinese" name="medicineType"/> 中草药
            <p></p>
            <div class="form-group">
                <div class="col-md-5">
                    <div class="panel panel-primary" style="height: 600px">
                        <div class="panel-heading">
                            <h3 class="panel-title">药方库</h3>
                        </div>
                        <div class="panel-body" >
                            <div style="border: 1px #cccccc solid;padding: 8px;height: 488px;overflow-y: auto">
                                <span>需要批量添加时，可以勾选多个药方，点击“批量添加”按钮</span>
                                <select name="categoryId" class="form-control" id="sltLibCate">
                                    <c:forEach var="cate" items="${libCategories}">
                                        <option value="${cate.id}">${cate.name}</option>
                                    </c:forEach>
                                </select>
                                <table border="0" id="tblRpLib"></table>
                            </div>
                        </div>
                        <div class="panel-footer text-center">
                            <a href="javascript:;" onclick="fn_checkAllForRpLib(true)">全选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:;" onclick="fn_checkAllForRpLib(false)">全不选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:;" onclick="fn_addRP()">批量添加</a>
                        </div>
                    </div>
                </div>
                <div class="col-md-1">
                    <span class="fa fa-exchange" style="font-size:50px;color:royalblue;margin-top: 300px;"></span>
                </div>
                <div class="col-md-5">
                    <div class="panel panel-primary" style="height: 600px">
                        <div class="panel-heading">
                            <h3 class="panel-title">我的药方</h3>
                        </div>
                        <div class="panel-body">
                            <div style="border: 1px #cccccc solid;padding: 8px;height: 488px;overflow-y: auto">
                                <span>需要批量删除时，可以勾选多个药方，点击“批量删除”按钮</span>
                                <select name="categoryId" class="form-control" id="sltCate">
                                    <c:forEach var="cate" items="${categories}">
                                        <option value="${cate.id}">${cate.name}</option>
                                    </c:forEach>
                                </select>
                                <table border="0" id="tblRp"></table>
                            </div>
                        </div>
                        <div class="panel-footer text-center">
                            <a href="javascript:;" onclick="fn_checkAllForRp(true)">全选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:;" onclick="fn_checkAllForRp(false)">全不选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="javascript:;" onclick="fn_delRp()">批量删除</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>