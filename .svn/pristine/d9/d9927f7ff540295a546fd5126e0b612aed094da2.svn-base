<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/13 0013
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<script type="application/javascript">
    /*  显示药品标签  滚动鼠标  */
    var i = 2;  //已知显示的<li>元素的个数
    var m = 2;  //用于计算的变量
    function showKey() {
        var $content = $(".scroll_demo");

        var count = $content.find("li").length;
        if (event.wheelDelta) {
            event.preventDefault();//阻止父元素滚动

            // 正120为前滚 负120为后滚
            // var textNode = document.createTextNode(event.wheelDelta);
            var textNode = event.wheelDelta;

            if (textNode == 120) {
//                $(".prev").trigger("click", function () {
                if (!$content.is(":animated")) {
                    if (m > i) { //判断 i 是否小于总的个数
                        m--;
                        $content.animate({left: "+=202px"}, 300);
                    }
                }
//                });
            } else {
//                $(".next").trigger("click", function () {
                if (!$content.is(":animated")) {  //判断元素是否正处于动画，如果不处于动画状态，则追加动画。
                    if (m < count) {  //判断 i 是否小于总的个数
                        m++;
                        $content.animate({left: "-=202px"}, 300);
                    }

                }
//                });
            }

        }
    }
</script>
<div class="scroll_demo" onmousewheel="showKey()">
    <c:forEach items="${list.content}" var="medPrivate">
        <li>
            <a href="javascript:" onclick="expireMedicine(${medPrivate.id},${loss.id})">
                <span class="tag tag-i span-tag">${medPrivate.name}</span>
            </a>
        </li>
    </c:forEach>

</div>
<script>
    $(function () {

    })

    function scrollPage(flag) {
        var medName = $("#medNameInput").val();
        var iaiLossId = $("#iaiLossId").val();
        var page =
        ${list.number}
        var totalNum =
        ${list.totalPages}
        if (flag == "left") {
            if (page - 1 < 0) {
                layer.msg("这是已经是第一页了！")
                return false
            }
            selectExpireMed(medName, page - 1, iaiLossId);
        } else {
            if (page + 1 > totalNum) {
                layer.msg("这是已经是最后一页了！")
                return false;
            }
            selectExpireMed(medName, page + 1, iaiLossId);
        }
    }

    function expireMedicine(medPrivateId, iaiLossId) {
        var url = "${ctx}/adv/IaiLossManage/bombBox/selectMedTag?medPrivateId={0}&iaiLossId={1}".format(medPrivateId, iaiLossId);
        layer.open({
            type: 2,
            maxmin: false,
            title: '编辑药品',
            area: ['700px', '300px'],
            scrollbar: false,
            content: url
        });
    }
</script>