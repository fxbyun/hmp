<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/28
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>选择理疗</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/jquery.form/jquery.form.js" type="js"/>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.$("#therapyList").find(".edit").removeClass("edit");
                parent.layer.close(index);
            });
        });
    </script>
</head>
<body style="background-color: #fff;">
<div style="margin: 10px 30px;" class="form-horizontal">
    <form action="" method="post">
        <div class="form-group" style="border-bottom: 1px solid #ccc;">
            <h3 style="margin-top: 10px;">${therapy.name}</h3>
            <input type="text" id="therapyId" name="therapyId" value="${therapy.id}" style="display: none;" class="form-control">
            <input type="text" id="flag" value="${flag}" style="display: none;" class="form-control">
        </div>
        <div class="form-group" style="margin-top: 25px;">
            <label class="col-xs-2 col-sm-2 control-label text-right" style="padding: 0;">理疗单价：</label>
            <div class="col-xs-5 col-sm-5">
                <input type="number" name="therapyPrice" value="${therapy.price}" class="form-control">
            </div>
            <label class="col-xs-1 col-sm-1 control-label" style="line-height: 2.5em; margin-left: -20px;">元</label>
        </div>
        <div class="form-group">
            <label class="col-xs-2 col-sm-2 control-label text-right" style="padding: 0;">数量/单位：</label>
            <div class="col-xs-3 col-sm-3">
                <input type="number" min="1" name="useQty" value="${therapy.useQty!=null&&therapy.useQty!=""?therapy.useQty:1}" class="form-control">
            </div>
            <div class="col-xs-3 col-sm-3">
                <select name="useUnit" class="form-control">
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
            <label class="col-xs-1 col-sm-1 control-label" style="line-height: 2.5em; margin-left: -10px;">X</label>
            <div class="col-xs-2 col-sm-2" style="padding: 0;">
                <input type="number" min="1" name="therapyCopy" value="${therapyCopy!=null?therapyCopy:1}" class="form-control">
            </div>
            <label class="col-xs-1 col-sm-1 control-label" style="line-height: 33px; margin-left: -5px;">次</label>
        </div>
        <div class="form-group text-center" style="margin-top: 30px;">
            <button id="btnSubmit" type="button" class="btn btn-success"><i class="fa fa-check"></i>确定</button>
            <button id="btnClose" type="button" class="btn btn-default"><i class="fa fa-times"></i>取消</button>
            <%--<button id="editPhy" type="button" class="btn btn-success"><i class="fa fa-edit"></i>修改理疗</button>--%>
        </div>
    </form>
</div>
<script>
    $(function () {
        $("#btnSubmit").click(function () {
            //判断这个中医料理是否已经添加
            var arrId=[];
            parent.$("#therapyList").find("input[name='therapyId']").each(function () {
                arrId.push($(this).val());
            })
            if($.inArray($("#therapyId").val(),arrId)>=0&&parent.$("#therapyList").find(".edit").length==0){
                layer.msg("该中医理疗已添加！");
                return false;
            }


            $("form").ajaxSubmit({
                url:"${ctx}/therapy/pullTherapy",
                success:function (data) {
                    if(data.success==true){
                        //layer.msg(data.msg)
                        parent.$("#therapyList").find(".edit").parent().remove();
                        //是否添加中医理疗这个标题
                        if (parent.$("#therapyList").html().trim() == "" && parent.$(".divTherapy").find("h4").html() == undefined) {
                            parent.$(".divTherapy").prepend("<h4>中医理疗</h4>")
                        }
                        parent.$("#therapyList").append(data.data);
                        parent.$("#therapyLi").trigger("click");

                        //每一次算一遍中医理疗的所有费用
                        getTherapyPrice();

                        parent.layer.closeAll();
                    }else {
                        //layer.msg(data.msg);
                    }
                },
                error: function(data){

                }
            });
        })


        $("#editPhy").click(function () {
            editPhy(this, function () {
                var index = parent.layer.getFrameIndex(window.name);
                var url = "/fragment/therapy/selectPhy?therapyId={0}".format(${therapy.id});
                //设置关闭刷新之前窗口
                parent.layer.iframeSrc(index, url);
            })
        })
        
    })
</script>
</body>
</html>
