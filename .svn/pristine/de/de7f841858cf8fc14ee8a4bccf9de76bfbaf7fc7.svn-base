<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/assets/styles/default.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css"/>
    <link rel="stylesheet" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/getPinYin.js" type="js"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>

    <script type="text/javascript">
        var id = "${medicine.id}";
        function fn_AddCompanies(companies) {
            var ids = $.map($('input[name="defaultCompanyId"]'), function (n) {
                return $(n).val();
            });
            $.each(companies, function (i, co) {
                if ($.inArray(co.id, ids) == -1) {
                    var ele = '<div class="radio"><a href="javascript:" onclick="$(this).parent().remove()"><i class="fa fa-times"></i></a>&nbsp;&nbsp;&nbsp;<label>' +
                            '<input type="radio" name="defaultCompanyId" value="' + co.id + '" >' +
                            '<input type="hidden" name="companyList" value="' + co.id + '" />' + co.name + '</label></div>';
                    $('#divCompanyList').append(ele);
                }
            });
        }
        var isFormChange = false;
        $(function () {
            isFormChange = false;
        })

        $(function () {
            var index = parent.layer.getFrameIndex(window.name);

            $('#btnSelectCompany').click(function () {
                layer.open({
                    type: 2,
                    title: '选择添加药厂',
                    area: ['600px', '550px'],
                    scrollbar: false,
                    content: '/adv/fragment/companies'
                });
            });
            $('#btnCloseMedicineForm').click(function () {

                if ("${iaiType}" != "") {
                    parent.layer.closeAll();
                    return;
                }
                var msg = $("#msgId").text();
//                console.info(($("#msgId").val());
                if (isFormChange) {
                    layer.confirm("信息已修改，关闭将丢失所有信息。确认关闭？", function () {

                        if (id != null && id != "" && msg == "药品信息已保存") {
//
                            parent.fn_ShowSelectMedicine(id);
                        }
//                        parent.layer.close(index);
                        parent.layer.closeAll()
                    });
                } else {

                    if (id != null && id != "" && msg == "药品信息已保存") {
//
                        parent.fn_ShowSelectMedicine(id);
                    }
//                    parent.layer.close(index)
                    parent.layer.closeAll()

                }
                ;
            });

            $('#frmMedicine').change(function () {
                isFormChange = true;
            });
            $('#frmMedicine').submit(function () {
                var companyList = $('input[name="companyList"]');
                if (companyList.length == 0) {
                    layer.alert("请选择药厂并勾选默认药厂");
                    return false;
                }
            });
            $("#frmMedicine").validate();


            // 适用与不适用的 显示隐藏
            $('input[name="type"]').change(function () {
                var type = $('input[name="type"]:checked').val();
                if (type == 0) {
                    $('#showDiv').show();
                } else {
                    $('#showDiv').hide();
                }
            });

            $('input[name="type"]').change();


            $("#selWMCate").change(function () {
                $("#category").val($(this).val());
            });
            $("#selCMCate").change(function () {
                $("#category").val($(this).val());
            });
        })

        function pullPinYin() {
            $("#helpCode").val(pinyin.getCamelChars($("#txtName").val()));
//            alert("1")
            return true;
        }
        setInterval("setFalse()", 500);
        function setFalse() {
            if ($("#msgId").val() == "") {
                isFormChange = false;
            }
        }

        $(function () {
            if ($("#msgId").text() == "药品信息已保存") {
                parent.addMedId = id;
            }
        })


    </script>
</head>
<body style="background-color: #fff;">
<div style="margin: 20px;">
    <form:form action="${ctx}/adv/medicine/save" id="frmMedicine" cssClass="form-horizontal" method="post"
               modelAttribute="medicine" onsubmit="pullPinYin()">
        <form:hidden path="id"/>
        <form:hidden path="category"/>
        <form:hidden path="type"/>
        <input type="hidden" name="iaiType" value="${iaiType}">
        <div class="form-group">
            <label for="txtName" class="col-xs-2 col-sm-2 control-label text-right">药品名称</label>

            <div class="col-xs-7 col-sm-7">
                <c:if test="${medicine.id == null}">
                    <form:input path="name" cssClass="form-control required" id="txtName" placeholder="输入药品名称"/>

                </c:if>
                <c:if test="${medicine.id != null}"> <form:input path="name" readonly="true" value="${medicine.name}"
                                                                 cssClass="form-control required"
                                                                 id="txtName" placeholder="输入药品名称"/> </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 col-sm-2 control-label text-right">药品分类</label>

            <div class="col-xs-7 col-sm-7">
                <c:if test='${medicine.type=="Western"}'>
                    <select id="selWMCate" class="form-control">
                        <option value="">未分类</option>
                        <c:forEach var="c" items="${westernMedicineCate}">
                            <option value="${c}" <c:if test='${medicine.category==c}'>selected</c:if>>${c}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test='${medicine.type=="Chinese"}'>
                    <select id="selCMCate" class="form-control">
                        <option value="">未分类</option>
                        <c:forEach var="c" items="${chineseMedicineCate}">
                            <option value="${c}" <c:if test='${medicine.category==c}'>selected</c:if>>${c}</option>
                        </c:forEach>
                    </select>
                </c:if>


            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-2 col-sm-2 control-label text-right">治疗方式</label>

            <div class="col-xs-7 col-sm-7">
                <form:select path="useMode" cssClass="form-control" items="${medicineUseModes}"/>
            </div>
        </div>


        <div class="form-group">
            <label for="selUseTimes" class="col-xs-2 col-sm-2 control-label text-right">标准用量</label>

            <div class="col-xs-9 col-sm-9"
                 style="background-color: #F2F2F2; border:1px solid #ccc; margin-left: 13px; padding-left: 0;">
                <div class="col-xs-12 col-sm-12" style="padding: 10px 15px;">
                    <input type="radio" name="type" value="0" checked> 适用
                    <input type="radio" name="type" style="margin-left: 10px;" value="1"> 不适用
                </div>
                <div class="col-xs-12 col-sm-12" id="showDiv" style="margin-bottom: 10px; padding-left: 0;">
                    <div class="col-xs-3 col-sm-3" style="padding-right: 0;">
                        <form:select path="useTimes" id="selUseTimes" cssClass="form-control"
                                     items="${medicineUseTimes}"/>
                    </div>
                    <label class="col-xs-2 col-sm-1 text-center" style="line-height: 32px; padding: 0;"> 每次</label>

                    <div class="col-xs-2 col-sm-2" style="padding-left: 0px; margin-left: -15px;">
                        <form:input path="useQty" cssClass="form-control text-center"/>
                    </div>
                    <div class="col-xs-2 col-sm-2" style="padding:0;">
                        <form:select path="useUnit" cssClass="form-control" items="${medicineUnits}"/>
                    </div>
                    <div class="col-xs-3 col-sm-3">
                        <form:select path="usingTime" id="txtUsingTime" cssClass="form-control"
                                     items="${medicineUsingTimes}"/>
                    </div>
                </div>


            </div>

        </div>

        <%--药品规格--%>
        <div class="form-group">
            <label for="txtName" class="col-xs-2 col-sm-2 control-label text-right">药品规格</label>

            <div class="col-xs-7 col-sm-7">
                <c:if test="${medicine.standard == null}">
                    <form:input path="standard" cssClass="form-control" id="txtStandard" placeholder="输入药品规格"/>

                </c:if>
                <c:if test="${medicine.standard != null}"> <form:input path="standard" readonly="true"
                                                                       value="${medicine.standard}"
                                                                       cssClass="form-control"
                                                                       id="txtName" placeholder="输入药品规格"/> </c:if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-2 col-sm-2 control-label text-right">统计单位</label>

            <div class="col-xs-10 col-sm-10">
                <div class="btn-group" data-toggle="buttons">
                    <c:forEach var="u" items="${medicineUnits}">
                        <label class="btn btn-default <c:if test='${medicine.unit==u.key}'>active</c:if>">
                            <input type="radio" name="unit" value="${u.key}" title="${u.value}"
                                   <c:if test='${medicine.unit==u.key}'>checked</c:if>>${u.value}
                        </label>
                    </c:forEach>
                </div>
            </div>
        </div>


        <div class="form-group">
            <label for="txtSpec" class="col-xs-2 col-sm-2 control-label text-right">药厂/产地</label>

            <div class="col-xs-10 col-sm-10">
                <button id="btnSelectCompany" type="button" class="btn btn-sm btn-success"><i
                        class="fa fa-check-square"></i> 选择药厂
                </button>
                <span class="text-danger">请选择药厂并勾选默认药厂</span>
            </div>
        </div>
        <div class="form-group">
            <div id="divCompanyList" class="col-xs-offset-2 col-sm-offset-2 col-xs-10 col-sm-10">
                <c:forEach var="co" items="${medicine.companyList}">
                    <div class="radio">
                        <a href="javascript:" onclick="$(this).parent().remove()"><i class="fa fa-times"></i></a>&nbsp;&nbsp;
                        <label>
                            <input type="radio" name="defaultCompanyId" value="${co.id}"
                                   <c:if test="${medicine.defaultCompany == co}">checked</c:if> />
                            <input type="hidden" name="companyList" value="${co.id}"/>${co.name}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <%--<div class="form-group">
            <label for="txtSpec" class="col-xs-2 col-sm-2 control-label">说明</label>

            <div class="col-xs-10 col-sm-10">
                <form:textarea path="specification" cssClass="form-control" id="txtspec" rows="2"></form:textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-sm-offset-2 col-xs-10 col-sm-10">
                <label>
                    <form:checkbox path="used" value="true" /> 加入常用药品
                </label>
            </div>
        </div>--%>
        <div class="form-group">
            <div class="col-xs-offset-2 col-sm-offset-2 col-xs-10 col-sm-10" id="msgId">${msg}</div>
        </div>
        <div class="form-group">
            <div class="col-xs-12 col-sm-12 text-center">
                <button type="submit" onclick="pullPinYin()" class="btn btn-success"><i class="fa fa-save"></i> 保存药品
                </button>
                <c:if test="${medicine.id != null}">
                    <a href="/medicine/add?type=${medicine.type}" class="btn btn-success"><i class="fa fa-plus"></i>
                        继续新增</a>
                </c:if>
                <button id="btnCloseMedicineForm" type="button" class="btn btn-default"><i class="fa fa-times"></i> 关闭
                </button>
            </div>
            <input type="text" style="display: none" name="helpCode" id="helpCode"/>
        </div>

    </form:form>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
</body>
</html>