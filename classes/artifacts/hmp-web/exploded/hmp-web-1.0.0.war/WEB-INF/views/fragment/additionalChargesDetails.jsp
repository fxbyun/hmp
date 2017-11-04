<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/27
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>附加费用明细</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $("#btnClose").click(function () {
                parent.layer.close(index);
            });
            trClick();
        });

        function trClick() {
            var $tbr = $('table tbody tr');
            /*点击每一行的选中单选框时*/
            $tbr.find('input').click(function (event) {
                event.stopPropagation();
            });
            $tbr.click(function () {
                $(this).find('input').click();
            });
        }

        /**
         * 删除检查项目
         * */
        function deleteItem(id) {
            layer.confirm("是否删除此项目！(可以在新增项目重新添加)", function () {
                $.postJSON("/fragment/delDoctorPre", {
                    id: id
                }, function (ret) {
                    if (ret.success) {
                        layer.msg("删除成功!");
                        window.location.reload();
                    } else {
                        layer.msg("删除失败!");
                    }
                });
            });
        }
        /**
         * 添加附加项目
         */
        function addFujia() {
            var obj = {};
            var inputCh = $("input[type='radio']:checked");
            if ($(inputCh).length <= 0) {
                layer.msg("请选择项目");
                return;
            }

            obj["id"] = $(inputCh).val();
            obj["name"] = $($("tr[ids='" + $(inputCh).val() + "']").find("input[name='inputName']")).val();
            obj["price"] = $($("tr[ids='" + $(inputCh).val() + "']").find("input[name='inputPrice']")).val();

            addPresOrDiv(obj);
            parent.layer.closeAll();
        }

        /**
         *  将 选择的 数据 组合成html代码 显示到 处方签上
         * @param obj
         */
        function addPresOrDiv(obj) {
            var classdiv = parent.$("#charges");

            if ($(classdiv).find("div[ids='" + obj.id + "']").length > 0) {
                //如果有 删除 就删除 旧的div
                delContentById($(classdiv).find("div[ids='" + obj.id + "']"));
            }
            Util_Create_All_Ele(classdiv, obj);
            parent.$("#fujiaId").trigger("click");
        }


        function delContentById(obj) {
            var thisClassDiv = $(obj);
            if ($(thisClassDiv).parent().find("div").length == 1) {
                $(thisClassDiv).parent().remove();
            } else {
                $(thisClassDiv).remove();
            }
        }


        /**
         * //创建 html 对象
         * @param classdiv 附加费用在 处方签上面的最大的div对象
         * @param obj       即将添加的 附件费用的  信息 如 id name price
         * @constructor
         */
        function Util_Create_All_Ele(classdiv, obj) {

            var prices = 0;
            parent.priceMap.remove("examLab_fujia_" + obj.id);
            parent.priceMap.put("examLab_fujia_" + obj.id, obj);

            $.each(parent.priceMap.values(), function (index, val) {
                prices = parseFloat(prices) + parseFloat(val.price);
            });
            parent.$("#cost").val(prices.toFixed(2));
            //判断是否已经存在对应要增加的类型
            //判断是否已经存在大类  最外面的div和H4标签
            if ($(classdiv).find("div[type='fujia']").length > 0) {
                $($(classdiv).find("div[type='fujia']")).append(Utils_Create_Exam_Cost_One_Div(obj));
            } else {
                var classDivH4 = Utlis_Create_Div_And_H4_Cost2(obj);
                var oneClassSub = Utils_Create_Exam_Cost_One_Div(obj);
                $(classDivH4).append($(oneClassSub));
                $(classdiv).append($(classDivH4));
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

        function Utlis_Create_Div_And_H4_Cost2(obj) {
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
<body style="background-color: #fff;">
<div style="padding: 10px 20px;" class="items-table">
    <div style="height: 242px; overflow-y: auto;">
        <table width="100%" class="table-hover table-bordered">
            <colgroup width="15%"></colgroup>
            <colgroup width="35%"></colgroup>
            <colgroup width="20%"></colgroup>
            <colgroup width="30%"></colgroup>
            <thead>
            <tr>
                <th>选择</th>
                <th>项目名称</th>
                <th>费用</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pre" items="${jDoctorExtCostList}">
                <tr ids="${pre.id}">
                    <td><input type="radio" name="jextCost" value="${pre.id}"></td>
                    <td><span class="val-text" title="${pre.className}">${pre.className}<input type="hidden"
                                                                                               name="inputName"
                                                                                               value="${pre.className}"/></span>
                    </td>
                    <td>${pre.price}<input type="hidden" name="inputPrice" value="${pre.price}"/></td>
                    <td>
                        <button type="button" class="btn btn-success"
                                onclick="editItems(${pre.id},${isAllowUpdatePrice})">编辑
                        </button>
                        <button type="button" class="btn btn-default" onclick="deleteItem(${pre.id})">删除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center btn-wd-mr" style="margin-top: 20px;">
        <button type="button" class="btn btn-success" id="addPres" onclick="addFujia()">添加</button>
        <button type="button" class="btn btn-success" onclick="addItems()">新增项目</button>
        <button id="btnClose" type="button" class="btn btn-default">关闭</button>
    </div>
</div>
</body>
</html>
