<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>

<style>
    .reg-view li {
        padding-right: 0.2em;
    }

    .reg-view span.tag, .span-tag {
        border-radius: 5px;
        min-width: 95px;
        font-size: 16px;
        padding-left: 5px;
    }

    .patient-label .labels-box .tag-i {
        padding-right: 10px;
        padding-left: 10px;
        margin-left: 5px;
    }

    .reg-view {
        width: 88%;
        height: 68px;
        float: left;
        overflow: hidden;
    }

    .layui-layer-dialog .layui-layer-content {
        padding: 15px;
    }

    .layui-layer-btn {
        text-align: center;
        vertical-align: middle;
        padding: 0 10px 32px;
    }

    .layui-layer-btn a {
        color: #fff;
    }

    a.fa-chevron-left {
        color: #000;
    }

    a.fa-chevron-left:hover, a.fa-chevron-left:link {
        text-decoration: none;
    }

    a.fa-chevron-right {
        color: #000;
    }

    a.fa-chevron-right:hover, a.fa-chevron-right:link {
        text-decoration: none;
    }

    .per-relative {
        overflow: hidden;
        position: relative;
        visibility: visible;
        float: left;
        height: 68px;
        width: 97%;
    }

    .scroll_demo {
        position: absolute;
        width: 200000em;
        left: 7px;
    }


</style>
<script type="text/javascript">
    function fn_RemoveRegistration(target, tag, patientUid, registrationType, haveBindingCard, wxPatienId) {
        layer.confirm('', {
                btn: ['取消挂号', '开始接诊'],
                title: "请选择操作",
//                closeBtn: 0
            }, function () {
                if (registrationType == 'WECHAT') {
                    layer.alert("该挂号为微信预约，如需取消，请到预约列表进行操作！");
                } else {
                    $.postJSON("/fragment/registration/delete", {"id": tag}, function (res) {
                        if (res.success) {
                            $(target).parent().remove();
                            layer.msg('已取消挂号。');
                        } else {
                            layer.alert(res.msg);
                        }
                    });
                }


            }, function () {
                if (haveBindingCard == "NO") {
                    var tmpUdid = "";
                    layer.alert('该患者未绑卡，请先进行绑卡操作。', function () {
                        var indexL = layer.prompt({
                            title: "请刷卡"
                        }, function (udid) {
                            $.postJSON("/patient/login4Udid", {"udid": udid, "wxPatienId": wxPatienId}, function (result) {
                                debugger
                                if (result.status === 'NewCard') {
                                    $('#divPatientInfo').load("/fragment/patient/" + result.data);
                                    var tmp = $("#divRegistration").find("span[patientuid='" + result.data + "']").attr("ids");
                                    if (tmp != undefined && tmp != "") {
                                        $("#witeListId").val(tmp);
                                    }
                                    fn_LoadPatientOverviewWx(result.data, wxPatienId);
                                    $("#btnSubmit").attr("disabled", false);
                                } else if (result.status === 'Invalid') {
                                    layer.alert("无效的卡号或密码");
                                } else if (result.status === 'Passed' || result.status === 'Used') {
                                    layer.msg("该卡已绑定其他患者！");
                                }
                            });
                        });
                        setTimeout(function () {
                            $(".layui-layer-input").keydown(function (event) {
                                if (event.keyCode == 13) {
                                    $(".layui-layer-btn0").trigger("click");
                                }
                            });
                        }, 2000);

                    });


                } else {
                    window.location.href = "${ctx}/begeenSee/" + patientUid + "?id=" + tag;
                }

            }
        );

    }
    function fn_Registration() {
        layer.open({
            type: 2,
            title: '挂号',
            area: ['400px', '250px'],
            scrollbar: false,
            content: '/fragment/registration/add'
        });
    }

    $(function () {
        var $content = $(".scroll_demo");
        var i = 5;  //已知显示的<li>元素的个数
        var m = 5;  //用于计算的变量
        var count = $content.find("li").length;//总共的<li>元素的个数

        $(".next").click(function () {
            if (!$content.is(":animated")) {  //判断元素是否正处于动画，如果不处于动画状态，则追加动画。
                if (m < count) {  //判断 i 是否小于总的个数
                    m++;
                    $content.animate({left: "-=175px"}, 300);
                }

            }
        });
        $(".prev").click(function () {
            if (!$content.is(":animated")) {
                if (m > i) { //判断 i 是否小于总的个数
                    m--;
                    $content.animate({left: "+=175px"}, 300);
                }
            }
        });

    });

    function showKey() {
        var $content = $(".scroll_demo");
        var i = 5;  //已知显示的<li>元素的个数
        var m = 5;  //用于计算的变量
        var count = $content.find("li").length;
        if (event.wheelDelta) {
            event.preventDefault();//阻止父元素滚动
            // 正120为前滚 负120为后滚
            // var textNode = document.createTextNode(event.wheelDelta);
            var textNode = event.wheelDelta;

            if (textNode == 120) {
                $(".prev").trigger("click", function () {
                    if (!$content.is(":animated")) {
                        if (m > i) { //判断 i 是否小于总的个数
                            m--;
                            $content.animate({left: "+=108px"}, 300);
                        }
                    }
                });
            } else {
                $(".next").trigger("click", function () {
                    if (!$content.is(":animated")) {  //判断元素是否正处于动画，如果不处于动画状态，则追加动画。
                        if (m < count) {  //判断 i 是否小于总的个数
                            m++;
                            $content.animate({left: "-=108px"}, 300);
                        }

                    }
                });
            }

        }
    }

</script>
<div class="row patient-label registrationView" style="padding: 10px 5px 5px 0;">

    <div class="labels-box" style="padding-left: 5px; overflow:hidden;">
        <div class="reg-view">
            <a class="prev fa fa-chevron-left" href="#" style="float:left; margin-top: 10px; line-height: 3;"></a>
            <div class="per-relative">
                <div class="scroll_demo" onmousewheel="showKey()">
                    <c:forEach var="tag" items="${registration}">
                        <li>
                            <a href="javascript:"
                               onclick="fn_RemoveRegistration(this,${tag.id},'${tag.patientUid}','${tag.registrationType}','${tag.haveBindingCard}','${tag.patientId}');">
                                <span class="tag tag-i span-tag"
                                      <c:if test='${tag.haveSingIn != "YES" && tag.registrationType=="WECHAT"}'>style="background-color: #ff7124;color: white;"</c:if>
                                      patientUid="${tag.patientUid}"
                                      ids="${tag.id}">${tag.patientName}<br>
                                    <span>
                                          <%--pattern="yyyy/MM/dd HH:mm"--%>
                                        <c:if test="${tag.registrationType=='WECHAT'}">
                                            <fmt:formatDate value="${tag.createOn}" type="date"
                                                            pattern="HH:mm"
                                            />--
                                            <fmt:formatDate value="${tag.completeOn}" type="date" pattern="HH:mm"/>
                                        </c:if>
                                         <c:if test="${tag.registrationType!='WECHAT'}">
                                             <fmt:formatDate value="${tag.createOn}" type="date"
                                                             pattern="HH:mm"/>
                                         </c:if>

                                    </span>
                                </span>
                            </a>
                        </li>
                    </c:forEach>
                </div>
            </div>
            <a class="next fa fa-chevron-right" href="#" style="float:right; margin-top: 10px; line-height: 3;"></a>

        </div>
        <div class="form-group" style="float:right;margin-top: 15px;">
            <button onclick="fn_Registration();" type="button" class="btn btn-success">
                <i class="fa fa-plus-square" style="padding-right: 5px;"></i>挂号
            </button>
            <button onclick="fn_Call();" type="button" class="btn btn-success">叫号</button>
        </div>
    </div>
    <script type="application/javascript">
        function fn_Call() {
            if ($("#paperName").val() == undefined || $("#paperName").val() == "") {
                parent.Alert.warning("请先接诊病人,才能进行叫号!");
                return;
            }
            if ($("#witeListId").val() == "" || $("#witeListId").val() == undefined) {
                parent.Alert.warning("该病人不在排队列表中,无法叫号!");
                return;
            }
            $.postJSON("${ctx}/fragment/callWebSocket", {witeListId: $("#witeListId").val()}, function (re) {
                if (re.success)
                    parent.Alert.success("叫号成功!", "成功");
                else {
                    parent.Alert.error("叫号失败!", "错误");
                }
            })
        }


    </script>

</div>

