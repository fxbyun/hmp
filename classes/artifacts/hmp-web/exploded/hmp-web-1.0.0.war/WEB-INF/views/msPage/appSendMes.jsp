<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/29
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<html>
<head>
    <title>发送短信</title>
    <script>
        $(function () {
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnClose').click(function () {
                parent.layer.close(index);
            });


            //先选出 textarea 和 统计字数 dom 节点
            var wordCount = $("#mes-div"),
                    textArea = wordCount.find("textarea"),
                    word = wordCount.find(".word");
            //调用
            statInputNum(textArea, word);

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

    <div class="title"><span id="mes-zs">--华佗个体门诊。退订回复TD</span></div>
    <div class="wordwrap"><span><var class="word">181</var>/201</span></div>
</div>
<p class="text-center" style="margin-top: 20px;">
    <button class="btn btn-success">发送短信</button>
    <button id="btnClose" class="btn btn-default" style="width: 80px; margin-left: 10px;">返回</button>
</p>
</body>
</html>
