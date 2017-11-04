<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>药方管理</title>
    <script>
        function fn_LoadMedicineTag(page,type, name) {
            if (page == undefined) page = 0;
            if (name == undefined) name = "";
            name = name.replace(/[ ]/g, "");
            if(type == "" || type == undefined)
                type = $('input[name="medicineType"]:checked').val();
            $('#divTags').load('/fragment/configMedicineTags/{0}?page={1}&name={2}'.format(type,page, name));
        }

        function fn_LoadMedicineOtherTag(page,type, name) {
            if (page == undefined) page = 0;
            if (name == undefined) name = "";
            name = name.replace(/[ ]/g, "");
            if(type == "" || type == undefined)
                type = $('input[name="medicineType"]:checked').val();
            $('#divOtherTags').load('/fragment/configMedicineOtherTags/{0}?page={1}&name={2}'.format(type,page, name));
        }

        function fn_SelectMedicineOtherTag(tagId) {
            if (tagId) {
                $.postJSON("/medicine/{0}/used".format(tagId), {"used": true}, function (res) {
                    if (res && res.success) {
                        if (res.msg)layer.msg(res.msg);
                        fn_LoadMedicineTag(0);
                        fn_LoadMedicineOtherTag(0);
                    }
                })
            }
        }
        function fn_SelectMedicineTag(tagId) {
            if (tagId) {
                var index = layer.confirm("删除此药品标签后需重新添加,确认删除？", function () {
                    layer.close(index);
                    $.postJSON("/medicine/{0}/used".format(tagId), {"used": false}, function (res) {
                        if (res && res.success) {
                            if (res.msg)layer.msg(res.msg);
                            fn_LoadMedicineTag(0);
                            fn_LoadMedicineOtherTag(0);
                        }
                    })
                });
            }
        }
        function fn_MedicineTypeChange() {
            var type = $('input[name="medicineType"]:checked').val();
            fn_LoadMedicineTag(0,type);
            fn_LoadMedicineOtherTag(0,type);
        }
        function fn_AddMedicine(){
            var type = $('input[name="medicineType"]:checked').val();
            layer.open({
                type: 2,
                title: '添加药品',
                area: ['720px', '680px'],
                scrollbar: false,
                content: '/medicine/add?type=' + type,
                end: function () {
                    fn_LoadMedicineTag(0);
                    fn_LoadMedicineOtherTag(0);
                }
            });
        }
        $(function () {
            $("#nav-manage").addClass("active");
            $('input[name="medicineType"]').change(fn_MedicineTypeChange);
            $('input[name="medicineType"]').change();
        });
    </script>
</head>
<body>
<div class="manage">
    <div class="container">
        <ul class="navigation">
            <li><a href="${ctx}/manage">用药分析</a></li>
            <li><a href="${ctx}/infro">信息设置</a></li>
            <%--<li><a href="${ctx}/wealth">我的财富</a></li>--%>
            <li><a href="${ctx}/dm">常用药品</a></li>
            <li><a href="${ctx}/rp">我的药方</a></li>
            <li><a href="${ctx}/rplib">药方库</a></li>
            <li class="active"><a href="#">数据整理</a></li>
        </ul>
        <ul class="navigation navigation-sub">
            <li><a href="${ctx}/config/symptom">患者主诉整理</a></li>
            <li><a href="${ctx}/config/diagnosis">诊断结果整理</a></li>
            <li class="active"><a href="#">药品整理</a></li>
            <li><a href="${ctx}/config/rp">药方整理</a></li>
        </ul>
        <div class="diagnose">
            <p>药品分类</p>
            <input type="radio" class="inline" value="Western" name="medicineType" checked="checked"/> 西药及中成药
            <input type="radio" class="inline" value="Chinese" name="medicineType"/> 中草药
            <p></p>
            <p>常用药品</p>
            <div id="divTags" class="diagnosis-label-box"></div>
            <br />
            <p>药品库</p>
            <div id="divOtherTags" class="diagnosis-label-box"></div>
        </div>
    </div>
</div>
</body>
</html>