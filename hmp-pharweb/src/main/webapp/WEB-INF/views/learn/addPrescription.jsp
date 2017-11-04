<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/11
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<html>
<head>
    <title>新增药品</title>
    <link href="${ctx}/assets/select2/select2.css" type="text/css" rel="stylesheet">

    <hmp:HmpLoadFile url="/assets/scripts/getPinYin.js" type="js" />
    <hmp:HmpLoadFile url="/assets/scripts/pinyin.js" type="js" />
    <script type="text/javascript" src="${ctx}/assets/select2/select2.js"></script>
    <script type="text/javascript" src="${ctx}/assets/select2/select2.ext.js"></script>
    <script>
        <%----%>
        $(function () {
            parent.layer.close(parent.index_Layer_hunde);
            $("#CompanyId").select2({
                language: "zh-CN"
            });

            $("#needUse").click(function () {
                $("#useDiv").show();
                $("#needDivFh").show();
            });
            $("#unNeeduse").click(function () {
                $("#useDiv").hide();
                $("#needDivFh").hide();
            });

            //点击关闭按钮
            $('#btnCloseMedicine').click(function () {
//                parent.layer.close(index);
                parent.utlis_HiddOrShowHearAndBodyAndFooter(HIDD_SHOW.Show, "add");
            });
        });

        //helpCode患者姓名拼音首字母
        function pullPinYin() {
            $("#helpCode").val(pinyin.getCamelChars($("#txtName").val()));
            return true;
        }


        function sublime() {
            layer.msg("保存中,请稍后!");
            pullPinYin()
            $.ajax({
                type: 'POST',
                url: '/learn/medicine/save',
                data: $("#frmMedicine").serialize(),
                dataType: "json",
                success: function (resu) {
                    console.info(resu);
                    layer.msg(resu.msg);
                    $("#medTitle").text(resu.msg);
                    $("#medTitle").attr("style", "color: red;");
                }
            });
        }

    </script>
</head>
<body>
<div>
    <form:form action="/learn/medicine/save" id="frmMedicine" cssClass="form-horizontal" method="post"
               modelAttribute="medicine">
        <form:hidden path="id" />
        <form:hidden path="category" />
        <form:hidden path="type" />
        <form:hidden path="helpCode" />


        <div style="padding: 1rem;">
            <h3 class="text-center" id="medTitle">新建药品</h3>

            <p><span style="padding-right: 0.9rem;">药品名称</span>
                <form:input path="name"
                            cssClass="form-control required"
                            id="txtName"
                            cssStyle="display: inline-block; width: 77%;"
                            placeholder="输入药品名称" />

            </p>

            <p>
                <span style="padding-right: 0.9rem;">药品分类</span>
                    <%--<select class="form-control" style="display: inline-block; width: 77%;">--%>
                    <%--<option>呼吸道系统类</option>--%>
                    <%--</select>--%>
                <c:if test='${medicine.type=="Western"}'>
                    <select id="selWMCate" class="form-control"
                            style="display: inline-block; width: 77%;">
                        <option value="">未分类</option>
                        <c:forEach var="c" items="${westernMedicineCate}">
                            <option value="${c}" <c:if test='${medicine.category==c}'>selected</c:if>>${c}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test='${medicine.type=="Chinese"}'>
                    <select id="selCMCate" class="form-control"
                            style="display: inline-block; width: 77%;">
                        <option value="">未分类</option>
                        <c:forEach var="c" items="${chineseMedicineCate}">
                            <option value="${c}" <c:if test='${medicine.category==c}'>selected</c:if>>${c}</option>
                        </c:forEach>
                    </select>
                </c:if>
            </p>

            <p>
                <span style="padding-right: 0.9rem;">治疗方式</span>
                <form:select path="useMode"
                             cssClass="form-control"
                             cssStyle="display: inline-block; width: 77%;"
                             items="${medicineUseModes}" />
                    <%--<select class="form-control" style="display: inline-block; width: 77%;">--%>
                    <%--<option>口服</option>--%>
                    <%--<option>滴注</option>--%>
                    <%--</select>--%>
            </p>
            <p><span>标准用量</span></p>

            <div style="border: 1px solid #ccc; padding: 1rem;">
                <p class="text-center">
                        <%--<input type="radio" name="be" style="width: 1.5rem;" checked>适用--%>
                        <%--<input type="radio" name="be">不适用--%>
                    <input id="needUse" type="radio" name="type" value="0" checked> 适用
                    <input type="radio" id="unNeeduse" name="type" style="margin-left: 10px;" value="1"> 不适用
                </p>

                <div style="overflow:hidden;" id="useDiv">
                    <div style="float:left; width: 35%;">
                            <%--<select class="form-control" style="display: inline-block;">--%>
                            <%--<option>每日三次</option>--%>
                            <%--</select>--%>
                        <form:select path="useTimes" id="selUseTimes"
                                     cssClass="form-control"
                                     cssStyle="display: inline-block;"
                                     items="${medicineUseTimes}" />
                    </div>
                    <div class="text-right" style="float:right; width: 60%;">
                        <span>每次</span>
                            <%--<input class="form-control"--%>
                            <%--type="number"--%>
                            <%--style="display: inline-block; width: 25%;">--%>
                        <form:input path="useQty"
                                    cssStyle="display: inline-block; width: 25%;"
                                    cssClass="form-control" />

                        <form:select path="useUnit"
                                     cssClass="form-control"
                                     cssStyle="display: inline-block; width: 45%;"
                                     items="${medicineUnits}" />
                            <%--<select class="form-control" style="display: inline-block; width: 45%;">--%>
                            <%--<option>粒/片</option>--%>
                            <%--</select>--%>
                    </div>
                </div>
                <div style="width: 35%; margin-top: 1rem;" id="needDivFh">
                    <form:select path="usingTime"
                                 id="txtUsingTime"
                                 cssClass="form-control"
                                 cssStyle="display: inline-block;"
                                 items="${medicineUsingTimes}" />
                </div>


            </div>

            <p style="margin-top: 0.8rem;"><span>统计单位</span></p>

            <div>
                <div class="btn-group" data-toggle="buttons">
                    <c:forEach var="u" items="${medicineUnits}">
                        <label class="btn btn-default <c:if test='${medicine.unit==u.key}'>active</c:if>">
                            <input
                                    type="radio"
                                    name="unit"
                                    value="${u.key}"
                                    title="${u.value}"
                                    <c:if test='${medicine.unit==u.key}'>checked</c:if>>${u.value}
                        </label>
                    </c:forEach>

                        <%--<label class="btn btn-default active">--%>
                        <%--<input type="radio" name="unit" value="bxs" title="盒" checked="">盒</label>--%>
                        <%--<label class="btn btn-default ">--%>
                        <%--<input type="radio" name="unit" value="btl" title="瓶">瓶</label>--%>
                        <%--<label class="btn btn-default ">--%>
                        <%--<input type="radio" name="unit" value="pkg" title="包/排">包/排</label>--%>
                        <%--<label class="btn btn-default ">--%>
                        <%--<input type="radio" name="unit" value="grs" title="粒/片">粒/片</label>--%>
                        <%--<label class="btn btn-default ">--%>
                        <%--<input type="radio" name="unit" value="pcs" title="支">支</label>--%>
                        <%--<label class="btn btn-default ">--%>
                        <%--<input type="radio" name="unit" value="g" title="g">g</label>--%>
                        <%--<label class="btn btn-default ">--%>
                        <%--<input type="radio" name="unit" value="mg" title="mg">mg</label>--%>
                        <%--<label class="btn btn-default ">--%>
                        <%--<input type="radio" name="unit" value="ml" title="ml">ml</label>--%>
                        <%--<label class="btn btn-default ">--%>
                        <%--<input type="radio" name="unit" value="oth" title="单位">单位</label>--%>
                </div>
            </div>

            <p style="margin-top: 1.2rem;">
                <span style="padding-right: 0.9rem;">药厂/产地</span>
                <select id="CompanyId" name="defaultCompanyId" class="form-control"
                        style="display: inline-block; width: 75%;">
                    <c:forEach items="${compayList}" var="oneCompay">
                        <option value="${oneCompay.id}">${oneCompay.name}</option>
                    </c:forEach>
                </select>

            </p>
            <p style="margin-top: 3rem;" class="text-center btn-bgcolor">
                <a type="button" onclick="sublime()" class="btn btn-success">确定</a>
                <a type="button" id="btnCloseMedicine" class="btn btn-success">返回</a>
            </p>
        </div>
    </form:form>
</div>
</body>
</html>
