<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/30
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<html>
<head>
    <title>编辑回访短信</title>

    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });


            var date = new Date();
            var day = date.getDate();
            if (day < 10) {
                day = "0" + day;
            }

            //先选出 textarea 和 统计字数 dom 节点
            var wordCount = $("#mes-div"),
                    textArea = wordCount.find("textarea"),
                    word = wordCount.find(".word");
            //调用
            statInputNum(textArea, word);
            $("#butSendMsgInfo").click(function () {
                var contentMsg = $("#mes-text").val();
                var appPatientId = ${appointPatient.id}
                var url = "/appoint/sendMsgAppoint";
                $.postJSON(url,{"contentMsg":contentMsg,"appPatientId":appPatientId},function (data) {
                    if(data.success){
                        parent.layer.close(index);
                        parent.layer.msg("短信发送成功！")
                    }else{
                        parent.layer.alert(data.msg)
                    }
                })



            })
        });

        /*
         * 剩余字数统计
         * 注意 最大字数只需要在放数字的节点哪里直接写好即可 如：<var class="word">200</var>
         */
        function statInputNum(textArea, numItem) {
            var max = numItem.text(),
                    curLength;
            textArea[0].setAttribute("maxlength", max);
            curLength = textArea.val().length;
            numItem.text(max - curLength);
            textArea.on('input propertychange', function () {
                numItem.text(max - $(this).val().length);
            });
        }
    </script>
</head>
<body style="background-color: #fff;">
<%--<div class="app-send">
    <textarea class="form-control app-send-textarea" autocomplete="off"></textarea>
</div>--%>
<div class="app-send" id="mes-div">
    <textarea id="mes-text" class="form-control app-send-textarea" style="text-indent: 50px;"
              maxlength="181"></textarea>
    <span id="mes-span">[易佳诊]</span>

    <div class="title"><span id="mes-zs">退订回复TD</span></div>
    <div class="wordwrap">
        <span style="font-size: 10px; padding-right: 10px;">
            <%--(资费0.09元/条)--%>
        </span>

        <span><var class="word">191</var>/201</span>
    </div>
</div>
<p class="text-center" style="margin-top: 20px;">
    <button class="btn btn-success" style="width: 80px;" id="butSendMsgInfo">发送</button>
    <button id="btnClose" class="btn btn-default" style="width: 80px; margin-left: 10px;">关闭</button>
</p>

</body>
</html>
