<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<script type="text/javascript">


    /* function fn_LoadPatientTags(patientId) {

     $('#txtPatientTag').load("/fragment/patientTags?patientId=" + patientId);
     $('#patientTags').load("/fragment/patientTags?patientId=" + patientId);
     }*/

    function fn_AddPatientTag(patientId) {
        var tag = $('#txtPatientTag').val();
        //if (tag) {
        $.postJSON("/patientTag/add", {'patientId': patientId, 'tag': tag}, function (res) {
            if (res.success) {
                /*layer.msg('标签已添加');*/
                $('#patientTags').val(tag);
                /*fn_LoadPatientTags(patientId);*/
            } else {
                layer.alert(res.msg);
            }
        });
        //}
    }


    function fn_EditPatient() {
        layer.open({
            type: 2,
            title: '修改患者信息',
            area: ['500px', '400px'],
            scrollbar: false,
            content: '/fragment/patient/update/${patient.uid}',
            end: function () {
                fn_LoadPatient(${patient.uid});
            }
        });
    }
    $(function () {
        <c:if test="${patient.status == 'Unactivated'}">
        var index = layer.confirm("初次开卡用户，请完善用户信息", {
            btn: ["确定", "取消"]
        }, function () {
            fn_EditPatient();
            layer.close(index);
        }, function () {
            window.location.reload()
        });
        </c:if>
    });


    /**
     * 为处方签预览图 填充值
     */
    function pullpatientInfoToPager() {
        <c:if test="${not empty patient}">
        var paperName = '${patient.name}';
        var paperSex = '${genderMap[patient.gender]}';
        var paperAge = '${patient.age}';
        var paperPhoneNum = '${patient.mobile}';
        var paperAddrs = '${patient.province}${patient.city}${patient.area}${patient.address}';
        var patientTags = '<c:forEach var="tag" items="${patient.patientTagList}">${tag.name}</c:forEach>';
        $("#paperName").val(paperName);
        $("#examName").text(paperName);
        $("#paperSex").val(paperSex);
        $("#examGender").text(paperSex);
        $("#paperAge").val(paperAge);
        $("#examAge").text(paperAge);
        $("#paperPhoneNum").val(paperPhoneNum);
        $("#examMobo").text(paperPhoneNum);
        $("#paperAddrs").val(paperAddrs);
        $("#patientTags").val(patientTags);


        //中医理疗
        $("#patientName").text(paperName);
        $("#patientGender").text(paperSex);
        $("#patientAge").text(paperAge);
        $("#patientMobile").text(paperPhoneNum);


        //附加费用
        $("#fujiaName").text(paperName);
        $("#fujiaGender").text(paperSex);
        $("#fujiaAge").text(paperAge);
        $("#fujiaMobo").text(paperPhoneNum);


        </c:if>


    }

    $(function () {
        pullpatientInfoToPager();
    })

</script>
<c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">${error}</div>
</c:if>
<c:if test="${not empty patient}">
    <div style="display: none">
        <div class="row">
            <div class="col-md-3 col-sm-3">
                <label class="word-dis">姓 名:</label>
                <input type="hidden" name="patient.id" value="${patient.id}"/>
                <input type="hidden" id="hidPatientUid" name="patientUid" value="${patient.uid}"/>
                <input type="hidden" name="patientName" value="${patient.name}"/>
                <span>${patient.name}</span>
            </div>
            <div class="col-md-3 col-sm-3">
                <div class="form-group">
                    <label class="word-dis">年 龄:</label>
                    <span>${patient.age}</span>
                </div>
            </div>
            <div class="col-md-3 col-sm-3">
                <label class="word-dis">性别:</label>
                <span>${genderMap[patient.gender]}</span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3 col-sm-3">
                <div class="form-group">
                    <label>手机号:</label>
                    <span>${patient.mobile}</span>
                </div>
            </div>
            <div class="col-md-3 col-sm-3">
                <div class="form-group">
                    <label>身份证:</label>
                    <span>${patient.sfId}</span>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <div class="form-group">
                    <label class="word-dis">地址:</label>
                    <span>${patient.province}${patient.city}${patient.area}${patient.address}</span>
                </div>
            </div>
            <div class="col-md-2 col-sm-2 text-right" style="padding-right: 0">

            </div>
        </div>
    </div>
    <div class="row patient-label" style="padding-top: 0;">

        <div class="col-md-12 col-sm-12">

            <div class="form-group add-disease">
                <input id="txtPatientTag" type="text" class="form-control" placeholder="添加病史"
                       onkeydown="doBack(this)" onkeyup="doBack(this)"
                       value='<c:forEach var="tag" items="${patient.patientTagList}">${tag.name}</c:forEach>'>
                <button onclick="fn_AddPatientTag(${patient.id});" type="button" class="btn btn-info add-labels">修改病史
                </button>
            </div>
            <button onclick="fn_EditPatient();" type="button" class="btn btn-info pull-right infro-change">患者信息修改
            </button>
        </div>

    </div>

    <script type="application/javascript">

        function doBack(obj) {
            $(obj).keypress(function (e) {
                var key = window.event ? e.keyCode : e.which;
                if (key.toString() == "13") {
                    return false;
                }
            });


        }


    </script>
</c:if>