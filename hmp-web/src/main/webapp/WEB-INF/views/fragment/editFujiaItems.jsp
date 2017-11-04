<%--
  Created by IntelliJ IDEA.
  User: wxt
  Date: 2016/11/10
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head><hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });
        });

        function editfujia(id) {

            var obj = {};
            obj["id"] = id;
            obj["name"] = $("#name").val();
            obj["price"] = $("#price").val();
            Util_Create_All_Ele(parent.$("#charges"), obj);
            parent.$("#fujiaId").trigger("click");
            var prices = 0;
            parent.priceMap.remove("examLab_fujia_" + obj.id);
            parent.priceMap.put("examLab_fujia_" + obj.id, obj);

            $.each(parent.priceMap.values(), function (index, val) {
                prices = parseFloat(prices) + parseFloat(val.price);
            });
            parent.$("#cost").val(prices.toFixed(2));
            parent.layer.closeAll();
        }

        function delContentById(obj) {
            var thisClassDiv = $(obj);
            if ($(thisClassDiv).parent().find("div").length == 1) {
                $(thisClassDiv).parent().remove();
            } else {
                $(thisClassDiv).remove();
            }
        }

        function Util_Create_All_Ele(classDiv, obj) {

            //判断是否已经有对应的 div 的 ids=obj.id的 div框
            if ($(classDiv).find("div[ids='" + obj.id + "']").length > 0) {
                //如果有 删除 就删除 旧的div
                delContentById($(classDiv).find("div[ids='" + obj.id + "']"));
            }


            //判断是否已经存在对应要增加的类型
            //判断是否已经存在大类  最外面的div和H4标签
            if ($(classDiv).find("div[type='fujia']").length > 0) {
                $($(classDiv).find("div[type='fujia']")).append(Utils_Create_Exam_Cost_One_Div(obj));
            } else {
                var classDivH4 = Utlis_Create_Div_And_H4(obj);
                var oneClassSub = Utils_Create_Exam_Cost_One_Div(obj);
                $(classDivH4).append($(oneClassSub));
                $(classDiv).append($(classDivH4));
            }

        }

        function Utils_Create_Exam_Cost_One_Div(obj) {
            var div2 = Utlis_CreateEle("div");
            $(div2).attr("ids", obj.id);
            var Strong = Utlis_CreateEle("Strong");
            $(Strong).attr("onclick", "editFujiaItems(" + obj.id + ")").text(obj.name);
            var inputName = Utlis_CreateEle("input");
            $(inputName).attr("name", "fuJiaNames").attr("type", "hidden").attr("value", obj.name);
            var span = Utlis_CreateEle("span");
            $(span).text(obj.price);
            var inputPrice = Utlis_CreateEle("input");
            $(inputPrice).attr("name", "fuJiaPrices").attr("type", "hidden").attr("value", obj.price);

            var inputId = Utlis_CreateEle("input");
            $(inputId).attr("name", "fuJiaIds").attr("type", "hidden").attr("value", obj.id);
            var i = Utlis_CreateEle("i");
            $(i).attr("class", "fa fa-trash-o").attr("onclick", "fn_delContent(this," + obj.id + ")");

            $(Strong).append($(inputName));
            $(div2).append($(Strong));
            $(span).append($(inputPrice));
            $(span).append($(inputId));
            $(div2).append($(span));
            $(div2).append($(i));

            return $(div2);
        }

        function Utlis_Create_Div_And_H4(obj) {
            var div1 = Utlis_CreateEle("div");
            $(div1).attr("class", "phy-box charges").attr("type", "fujia");
            var h4 = Utlis_CreateEle("h4");
            $(h4).text("附加项目费用");
            return $(div1).append($(h4));

        }

        function Utlis_CreateEle(ele) {
            return document.createElement(ele);
        }
    </script>
</head>
<body>
<div style="padding: 10px 20px;">
    <div class="form-group">
        <span>项目名称：</span>
        <input type="text" class="form-control" value="${name}"
               style="display: inline-block; width: 70%;" id="name" ${isAllowUpdatePrice?"":"readonly"}>
    </div>
    <div class="form-group">
        <span>项目费用：</span>
        <input type="number" hmp-input-double class="form-control" value="${price}"
               style="display: inline-block; width: 40%;"
               id="price" ${isAllowUpdatePrice?"":"readonly"}><span> 元/次</span>
    </div>
    <div class="text-center btn-wd-mr">
        <button type="button" class="btn btn-success" onclick="editfujia(${id})">确认</button>
        <button id="btnClose" type="button" class="btn btn-default">取消</button>
    </div>
</div>

</body>
</html>