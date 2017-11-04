<%--@elvariable id="emr" type="com.qiaobei.hmp.modules.entity.Emr"--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/11
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>退费信息</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <script type="application/javascript" src="${ctx}/assets/vue/vue.js"></script>
</head>
<body style="background-color: #fff;">
<div style="margin: 20px;" id="backApp">
    <div style="overflow:hidden;">
        <div class="pull-left" style="margin-left: 30px;">
            <p>姓名：{{name}}</p>
            <p>年龄：{{age}}</p>
            <p>实收金额：{{realCost}}</p>
        </div>
        <div class="pull-right" style="margin-left: 20px;">
            <p>性别：{{gender}}</p>
            <p style="margin: 0;"><br></p>
            <p><span>实退金额：</span><input v-model="backMoneryInput" type="text" class="form-control"
                                        style="display:inline-block; width: 100px;" title="">
            </p>
        </div>

    </div>
    <p style="margin-left: 30px;">
        <span style="vertical-align: top;">备注：</span>
        <textarea class="form-control" style="display: inline-block; width: 80%;"></textarea>
    </p>
    <p class="text-center" style="margin-top: 20px;">
        <button @click="backMoneryAjax" class="btn btn-success" type="button" style="margin-right: 10px;">确认</button>
        <button id="btnClose" class="btn btn-default" type="button">取消</button>
    </p>
</div>
<script type="application/javascript">
    var backAppVue = new Vue({
        el: "#backApp",
        data: {
            name: '${emr.patient.name}',
            gender: '${emr.getMetaValue(emr.patient.gender)}',
            age: '${emr.patient.age}',
            realCost: '${emr.realCost}',
            backMoneryInput: "0"
        },
        methods: {
            backMoneryAjax: function (event) {
                var backMoneryInputTmp = this.backMoneryInput;
                if (parseFloat(backMoneryInputTmp) > parseFloat(this.realCost)) {
                    parent.Alert.warning("实退金额" + parseFloat(backMoneryInputTmp) + "不能大于实收金额!" + parseFloat(this.realCost));
                    return;
                }

                //询问框
                var indexLayer = layer.confirm('您确定要退费 ' + backMoneryInputTmp + " 元?", {
                    title: "警告",
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    console.info()
                    backMoney('${emr.id}', backMoneryInputTmp);
                    indexLayer.close();
                });

            }
        }
    })
</script>
</body>
</html>
