<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/28
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加理疗</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery.form/jquery.form.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/getPinYin.js" type="js"/>
    <%--<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/therapy.js" type="js"/>--%>

    <script>
        $(function () {

            var saveFlag;
            $("#btnClose").click(function () {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                //保存成功时，点击关闭时
                /*if(saveFlag){
                    $.postJSON("/",{},function () {

                    })

                }else {
                    parent.layer.close(index);
                }*/


            });

            $("#btnSubmit").click(function () {

                $("#helpCode").val(pinyin.getCamelChars($("#therapyName").val()));
                $("#formTherapy").ajaxSubmit({
                    url:"${ctx}/therapy/saveTherapy",
                    success:function (data) {
                        if(data.success==true){
                            layer.msg(data.msg)
                            saveFlag = true;

                            var url = "/fragment/therapy/therapyList?page={0}&helpCode={1}".format(0, "");

                            /*var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);*/

                            //修改的
                            if($("#therapyId").val()!=""){
                                $('#divTherapy').load(url);
                                var index = parent.layer.getFrameIndex(window.name)
                                parent.layer.close(index);
                            }else {
                                //添加的
                                parent.$('#divTherapy').load(url);
                                parent.layer.closeAll();
                            }
                        }else {
                            layer.msg(data.msg);
                            saveFlag = true;
                        }
                    },
                    error: function(data){
                        label.msg(data.msg);
                    }
                });
            });







        });


    </script>
</head>
<body style="background-color: #fff;">
<div class="form-horizontal" style="margin: 10px 20px;">
    <form id="formTherapy" action="${ctx}/therapy/saveTherapy" method="post" >
        <div class="form-group">
            <label class="col-xs-3 col-sm-3 control-label text-right">理疗名称：</label>
            <div class="col-xs-8 col-sm-8">
                <input type="text" class="form-control" id="therapyName" name="therapyName" value="${therapy.name}">
                <input type="text" class="form-control" id="helpCode" name="helpCode" value="${therapy.helpCode}" style="display: none;">
                <input type="text" class="form-control" id="therapyId" name="therapyId" value="${therapy.id}" style="display: none;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 col-sm-3 control-label text-right">理疗费用：</label>
            <div class="col-xs-5 col-sm-5">
                <input type="number" class="form-control" name="therapyPrice" value="${therapy.price}">
            </div>
            <label class="col-xs-1 col-sm-1 control-label" style="line-height: 2.5em; margin-left: -20px;">元</label>

        </div>
        <div class="form-group">
            <label class="col-xs-3 col-sm-3 control-label text-right">单位：</label>
            <div class="col-xs-5 col-sm-5">
                <select name="therapyUnits" class="form-control">
                    <c:forEach items="${therapyUnits}" var="unit">
                        <c:if test="${unit.key==therapy.useUnit}">
                            <option value="${unit.key}" selected="selected">${unit.value}</option>
                        </c:if>

                        <c:if test="${unit.key!=therapy.useUnit}">
                            <option value="${unit.key}">${unit.value}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group text-center" style="margin-top: 15px;">
            <button id="btnSubmit" type="button" class="btn btn-success">保存</button>
            <button id="btnClose" type="button" class="btn btn-default">关闭</button>
        </div>
    </form>

</div>
</body>
</html>
