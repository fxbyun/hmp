<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%--@elvariable id="doctor" type="com.qiaobei.hmp.modules.entity.Doctor"--%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>门诊管理</title>
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/assets/jquery.form/ajaxfileupload_test.js" type="text/javascript"></script>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/hmp.js" type="js"/>
    <script>
        $(function () {
            $("#nav-system").addClass("active");
            $("#doctorForm").validate({
                rules: {
                    outpatientService: "required",
                    businessAddr: "required",
                    legal: "required",
                    legalCard: {
                        required: true,
                        minlength: 18,
                        maxlength: 18
                    },
                    businessLicense: "required",
                    name: {
                        required: true,
                        minlength: 2,
                        maxlength: 4
                    },
                    email: "required email",
                    mobile: {
                        required: true,
                        minlength: 11,
                        maxlength: 11
                    },
                    card: {
                        required: true,
                        minlength: 18,
                        maxlength: 18
                    }
                }
            });

            /* 删除图片效果  */
            /*$(".img-ul .scroll_demo li a").hover(function () {
                $(this).children("span").show();
            }, function () {
                $(this).children("span").hide();
             });*/
            //delPicStyle();


            /*  显示药品标签  点击上下键  */
            var $content = $(".scroll_demo");
            var i = 2;  //已知显示的<li>元素的个数
            var m = 2;  //用于计算的变量
            var count = $content.find("li").length;//总共的<li>元素的个数

            $(".next").click(function () {
                if (!$content.is(":animated")) {  //判断元素是否正处于动画，如果不处于动画状态，则追加动画。
                    if (m < count) {  //判断 i 是否小于总的个数
                        m++;
                        $content.animate({left: "-=202px"}, 300);
                    }

                }
            });
            $(".prev").click(function () {
                if (!$content.is(":animated")) {
                    if (m > i) { //判断 i 是否小于总的个数
                        m--;
                        $content.animate({left: "+=202px"}, 300);
                    }
                }
            });
            $("#file").change(function () {
                var imageId;
                var id = pushImage();
                clickUpLoad(id);
                $.ajaxFileUpload({
                    url: '${ctx}/fragment/doctor/saveImageWall',
                    type: 'post',
                    secureuri: false, //一般设置为false
                    fileElementId: 'file', // 上传文件的id、name属性名
                    dataType: 'json', //返回值类型，一般设置为json、application/json
                    //elementIds: "test", //传递参数到服务器
                    success: function (data, status) {
                        layer.msg("图片上传成功！")
                        //修改图片的id
                        imageId=data.data;
                        //添加点击事件属性
                        $("#"+id).attr("onclick","deletePic(this,"+imageId+")");
                    },
                    error: function (data, status, e) {
                        layer.msg("图片上传失败！")
                    }
                });

            });
            /* 删除图片效果  */
            $("#imageList").on("mouseover", "li", function () {
                $(this).find("span").show();
                //$(this).children("span").hide();

            })
            /* 删除图片效果  */
            $("#imageList").on("mouseout", "li", function () {
                $(this).find("span").hide();
            })


        });
        /*  显示药品标签  滚动鼠标  */
        function showKey() {
            var $content = $(".scroll_demo");
            var i = 2;  //已知显示的<li>元素的个数
            var m = 2;  //用于计算的变量
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
                                $content.animate({left: "+=202px"}, 300);
                            }
                        }
                    });
                } else {
                    $(".next").trigger("click", function () {
                        if (!$content.is(":animated")) {  //判断元素是否正处于动画，如果不处于动画状态，则追加动画。
                            if (m < count) {  //判断 i 是否小于总的个数
                                m++;
                                $content.animate({left: "-=202px"}, 300);
                            }

                        }
                    });
                }

            }
        }
        //图片上传 预览
        function clickUpLoad(id) {

            var imgObject = document.getElementById('file');
            var getImageSrc = getFullPath(imgObject); // 本地路径
            var srcs = window.URL.createObjectURL(imgObject.files[0]);
            var pos = getImageSrc.lastIndexOf(".");
            var lastname = getImageSrc.substring(pos, getImageSrc.length) // 图片后缀]
            if (srcs != "") {
                $("#" + id).attr("src", srcs);
            } else {
                alert("请选择一张图片");
            }

        }
        function getFullPath(obj) {  //得到图片的完整路径
            if (obj) {
                if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
                    obj.select();
                    return document.selection.createRange().text;

                } else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
                    if (obj.files) {
                        return window.URL.createObjectURL(obj.files[0]);
                    }
                    return obj.value;
                }
                return obj.value;
            }
        }

        function pushImage() {

            var imageSize = $("#imageList").children().size() + 1;
            var imageId = "showImg" + imageSize;
            var htmlText = '<li><a href="javascript:;">' +
                    '<img id=' + imageId + ' src="" width="150" height="150">' +
                    '<span class="share-del" id="span_delImageWall" style="display: none">删除</span>' +
                    '</a></li>';
            $("#imageList").append(htmlText);
            return imageId;
        }


        /* 删除图片 */
        function deletePic(even,imageId) {
            layer.confirm("是否确认删除该图片？", function () {
                $(even).parent().remove();
                parent.layer.closeAll();

                //删除形象墙图片
                $.postJSON("${ctx}/fragment/doctor/delImageWall",{"imageId":imageId},function (data) {
                    if(data.success){
                        layer.msg("图片删除成功！");
                    }else{
                        layer.msg("图片删除失败！");
                    }
                })

            });
        }

    </script>

</head>
<body>
<div class="manage wealth">
    <div class="container">
        <ul class="navigation">
            <li class="active"><a href="${ctx}/infro" class="btn btn-default">信息设置</a></li>
            <c:if test="${doctor.doctorType!='Sub_Doctor'}">
            <li><a href="${ctx}/advertSet" class="btn btn-default">终端机设置</a></li>
            <li><a href="${ctx}/employee" class="btn btn-default">员工管理</a></li>
            </c:if>
        </ul>

        <div class="outp-change">
            <h4>门诊信息修改</h4>
            <c:if test="${error != null}">
                <div class="alert alert-danger text-left" style="margin-bottom: 0px">${error}</div>
            </c:if>
            <form:form modelAttribute="doctor" class="form-inline" method="post" action="${ctx}/infro/save"
                       id="doctorForm" enctype="multipart/form-data" onsubmit=" return getAlonePrintCheckBoxToString()">
            <form:hidden path="id"></form:hidden>
            <form:hidden path="province"></form:hidden>
            <form:hidden path="city"></form:hidden>
            <form:hidden path="area"></form:hidden>


            <div class="outp-change-content" style="overflow: hidden;">
                <div class="col-md-8 col-sm-8">
                    <div class="form-group">
                        <span for="outpatientService" style="margin-left: 35px; margin-right: 20px;">门诊名称 </span>
                        <input type="text" class="form-control" style="width:80%;" id="outpatientService"
                               name="outpatientService" value="<c:out value='${doctor.outpatientService}'/>"/>
                    </div>
                    <div class="form-group outp-table-select">
                        <table width="100%">
                            <tr>
                                <td width="120px"><span for="businessAddr"
                                                        style="margin-left: 35px; margin-right: 20px;">门诊地址 </span></td>
                                <td>
                                    <form:select path="provinceNo" items="${provinceList}" itemLabel="areaName"
                                                 itemValue="areaNo"></form:select> &nbsp;
                                    <form:select path="cityNo" items="${cityList}" itemLabel="areaName"
                                                 itemValue="areaNo"></form:select> &nbsp;
                                    <form:select path="areaNo" items="${areaList}" itemLabel="areaName"
                                                 itemValue="areaNo"></form:select>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td height="50px">
                                    <input type="text" class="form-control" style="width:95.2%; margin-left: 12px;"
                                           id="businessAddr"
                                           name="businessAddr" value="${doctor.businessAddr}"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div style="overflow:hidden;">
                        <div class="form-group" style="float:left; width: 50%;">
                            <span for="legal" style="margin-left: 35px; margin-right: 20px;">法人代表 </span>
                            <input type="text" class="form-control" style="width:60%;" id="legal" name="legal"
                                   value="${doctor.legal}"/>
                        </div>
                        <div class="form-group" style="float:left; width: 50%;">
                            <span for="legalCard" style="margin-left: 18px; margin-right: 20px;">法人身份证 </span>
                            <input type="text" class="form-control" style="width:60%;" id="legalCard" name="legalCard"
                                   value="${doctor.legalCard}"/>
                        </div>
                    </div>

                </div>
                <div class="col-md-4 col-sm-4">
                    <div class="form-group">
                        <div class="text-center outp-img">
                            <c:if test="${businessFileName != null}">
                                <img width="100%" height="100%" src="${ctx}/temp/${businessFileName}"/>
                            </c:if>
                            <c:if test="${empty businessFileName}">
                                <img class="ml10" style="padding: 2% 0;" width="80%" height="100%"
                                     src="/assets/styles/images/default.png"/>
                            </c:if>
                        </div>

                        <div class="text-center">
                            <a href="javascript:;" class="a-upload">
                                <input type="file" name="businessFile" id="businessFile">上传营业执照照片
                            </a>
                        </div>

                    </div>
                </div>
                <div class="col-md-12 col-sm-12">
                    <div class="form-group">
                        <span style="margin-left: 35px; margin-right: 20px; vertical-align: top;">门诊简介</span>
                        <textarea name="clinicInfo" class="form-control"
                                  style="width: 85%; height: 8em;">${doctor.clinicInfo}</textarea>
                    </div>
                    <div class="form-group">
                        <span style="margin-left: 38px; margin-right: 20px; letter-spacing: 5px; vertical-align: top;">形象墙</span>
                        <div class="img-file">
                            <a href="javascript:;" class="a-upload">
                                <input id="file" type="file" name="imageWallFiles">
                            </a>
                            <div class="img-ul clearfix">
                                <a class="prev fa fa-chevron-left"></a>
                                <div class="list-tab">
                                    <div class="scroll_demo" id="imageList" onmousewheel="showKey()">
                                        <c:if test="${not empty imageWallList}">
                                            <c:forEach items="${imageWallList}" var="image">
                                                <li>
                                                    <a href="javascript:;" onclick="deletePic(this,${image.id})"><img
                                                            src="${ctx}/temp/${image.fileName}" width="150"
                                                            height="150">
                                                        <span class="share-del" id="span_delImageWall">删除</span>
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </c:if>

                                    </div>
                                </div>
                                <a class="next fa fa-chevron-right"></a>
                            </div>

                                <%--<ul>
                                    <a class="fa fa-angle-left"></a>
                                    <li>
                                        <a href="#"><img src="" width="150" height="150"></a>
                                    </li>
                                    <li>
                                        <a href="#"><img src="" width="150" height="150"></a>
                                    </li>
                                    <li>
                                        <a href="#"><img src="" width="150" height="150"></a>
                                    </li>
                                    <li>
                                        <a href="#"><img src="" width="150" height="150"></a>
                                    </li>
                                    <a class="fa fa-angle-right"></a>
                                </ul>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="personal-change">
            <h4>个人信息修改</h4>

            <div class="pers-change-content" style="overflow:hidden;">
                <div class="col-md-3 col-sm-3">
                    <c:if test="${portraitFile == null}">
                        <img width="100%" height="200" src="${ctx}/assets/styles/images/1.png"/>
                    </c:if>
                    <c:if test="${portraitFile != null}">
                        <img width="100%" height="200" src="${ctx}/temp/${portraitFile}"/>
                    </c:if>
                    <div class="text-center">
                        <a href="javascript:;" class="a-upload">
                            <input type="file" name="portraitFile" id="portraitFile">选择头像文件
                        </a>
                    </div>
                    <div class="text-center" style="margin-top: 20px; font-size: 16px;">
                        <input type="radio" name="sex" value="" style="margin-right: 5px;" checked>男
                        <input type="radio" name="sex" value="" style="margin-left:30px; margin-right: 5px;">女
                    </div>
                </div>
                <div class="col-md-9 col-sm-9">
                    <table width="90%">
                        <tr style="height:50px">
                            <td align="left" style="width: 20%;"><span>姓名</span></td>
                            <td><input type="text" class="form-control" id="name" name="name"
                                       value="${doctor.name}" maxlength="4" minlength="2"/></td>
                            <td align="left"><span style="margin-left: 30px;">身份证号</span></td>
                            <td><input type="text" class="form-control" id="card" name="card"
                                       value="${doctor.card}"/></td>
                        </tr>
                        <tr style="height:50px">
                            <td align="left"><span>手机号码</span></td>
                            <td><input type="num" class="form-control" id="mobile" name="mobile"
                                       value="${doctor.mobile}"/></td>
                            <td align="left"><span style="margin-left: 30px;">邮箱</span></td>
                            <td><input type="text" class="form-control" id="email" name="email"
                                       value="${doctor.email}"/></td>
                        </tr>
                        <tr style="height:50px">
                            <td align="left"><span>从医年限</span></td>
                            <td><input type="text" class="form-control" id="seniority" name="seniority"
                                       value="${doctor.seniority}"/></td>
                            <td align="left"><span style="margin-left: 30px;">职业资格</span></td>
                            <td colspan="3"><input type="text" class="form-control" id="certificate" name="certificate"
                                                   value="<c:out value='${doctor.certificate}'/>"/></td>
                        </tr>
                        <tr style="height: 30px;">
                            <td colspan="4"><span>擅长领域</span></td>
                        </tr>
                        <tr style="height:50px">
                            <td colspan="4"><textarea class="form-control" id="specialty" name="specialty"
                                                      placeholder="请输你的擅长领域"
                                                      rows="1"><c:out value='${doctor.specialty}'/></textarea></td>
                        </tr>
                        <tr style="height: 30px;">
                            <td colspan="4"><span>个人简介</span></td>
                        </tr>

                        <tr style="height:50px">
                            <td colspan="4"><textarea class="form-control" id="intro" name="intro" rows="1"
                                                      placeholder="介绍一下你自己"><c:out
                                    value='${doctor.intro}'/></textarea></td>
                        </tr>
                            <%-- 2016-12-27 10:43:56 新增科室 --%>
                        <tr style="height: 30px;">
                            <td colspan="4"><span>科室名称</span></td>
                        </tr>
                        <tr style="height:50px">
                            <td colspan="4"><input class="form-control" name="deptName" value='${doctor.deptName}'
                                                   placeholder="医生处(默认是医生处，如果为空的话是医生处)"/>
                            </td>
                        </tr>
                            <%--end --%>
                        <tr style="height: 30px;">
                            <td colspan="4"><span>尾部打印信息</span></td>
                        </tr>
                        <tr style="height:50px">
                            <td colspan="4"><input type="text" class="form-control" id="printInfo" name="printInfo"
                                                   placeholder="请输就诊单尾部打印信息"
                                                   value="<c:out value='${doctor.printInfo}'/>"/></td>
                        </tr>
                        <tr style="height: 30px;">
                            <td colspan="4"><span>打印尺寸选择</span></td>
                        </tr>
                        <tr style="height:30px">
                            <td colspan="4">
                                <div class="col-md-11 col-sm-11" style="padding-left: 0;">
                                    <label for="type1" class="radio-inline">
                                        <input id="printType1" name="printType" type="radio" value="80mm热敏纸打印"
                                               checked="checked"
                                               <c:if test="${doctor.printType.equals('80mm热敏纸打印')}">checked='checked'</c:if>
                                        >80mm热敏纸打印</label>
                                    <label for="type2" class="radio-inline">
                                        <input id="printType2" name="printType" type="radio" value="A5打印"
                                               <c:if test="${doctor.printType.equals('A5打印')}">checked='checked'</c:if>
                                        >A5打印</label>
                                </div>
                            </td>
                        </tr>

                        <tr style="height: 30px;">
                            <td colspan="4"><span>打印模式设置</span></td>
                        </tr>
                        <tr style="height: 30px;">
                            <td colspan="4">
                                <div class="col-xs-10 col-sm-10" style="padding-left: 0;">
                                    <label class="radio-inline">
                                        <input id="printModel1" name="printModel" type="radio" value="快速打印"
                                               checked="checked"

                                        >快速打印</label>
                                    <label class="radio-inline">
                                        <input id="printModel2" name="printModel" type="radio" value="预览打印"
                                               <c:if test="${doctor.printModel.equals('预览打印')}">checked='checked'</c:if>
                                        >预览打印</label>
                                </div>
                            </td>
                        </tr>

                        <tr style="height:30px">
                            <td><span>单独打印模块</span></td>
                            <td colspan="3">
                                <div class="col-xs-12 col-sm-12">

                                    <c:forEach varStatus="status" var="oneSeparatePrintSelWMCate"
                                               items="${medicineNeedAlonePrintLists}">

                                        <label class="checkbox">
                                            <input id="alone${ status.index + 1}" name="${oneSeparatePrintSelWMCate}"
                                                   type="checkbox" value="true"
                                            <c:if test="${doctor.needAlonePrinTypeStrings.contains(oneSeparatePrintSelWMCate)}">
                                                   checked </c:if>
                                            ><input type="hidden" name="_used" value="on">
                                                ${oneSeparatePrintSelWMCate}
                                        </label>
                                    </c:forEach>

                                    <input type="hidden" id="needAlonePrinTypeStrings" name="needAlonePrinTypeStrings"
                                           value="${doctor.needAlonePrinTypeStrings}"/>

                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><span>发送回访短信：</span></td>

                            <td colspan="3"><span>默认发送回访短信</span>
                                <input type="radio" name="autoSend" value="是"
                                       <c:if test="${'是'.equals(doctor.autoSend)}">checked='checked'</c:if>
                                > 是
                                <input type="radio" name="autoSend" style="margin-left: 5px;" value="否"
                                       <c:if test="${!'是'.equals(doctor.autoSend)}">checked='checked'</c:if>
                                > 否
                                <span id="isNeedShow">
                                <span style="padding-left: 20px; padding-right: 5px;">就诊过后</span>
                                <input name="autoSendDay" type="number"
                                       value="<c:choose><c:when test="${not empty doctor.autoSendDay}">${doctor.autoSendDay}</c:when><c:otherwise>0</c:otherwise> </c:choose>"
                                       style="width: 60px; height: 32px; font-size: 16px; border-radius: 5px; border: 1px solid #ccc;"
                                       class="text-center">
                                <span>天发送</span>
                                <button type="button" class="btn btn-success" onclick="fn_ShowTemplate()"
                                        style="width: 80px; margin-left: 15px;">
                                    效果预览
                                </button>
                                </span>
                            </td>

                        </tr>
                        <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                            <tr>
                                <td colspan="4" style="padding-top: 10px;padding-bottom: 10px;">
                                    <span>允许子医生进行费用更改：</span>

                                    <input type="radio" name="allowSubDoctorUpdatePrice" value="true"
                                           <c:if test="${doctor.allowSubDoctorUpdatePrice}">checked='checked'</c:if>> 允许
                                    <input type="radio" name="allowSubDoctorUpdatePrice" style="margin-left: 5px;"
                                           value="false"
                                           <c:if test="${!doctor.allowSubDoctorUpdatePrice}">checked='checked'</c:if>>
                                    不允许
                                </td>
                            </tr>
                        </c:if>

                        <c:if test="${doctor.doctorType!='Sub_Doctor'}">
                            <tr>
                                <td colspan="4" style="padding: 5px 0px 10px 0;"><span>允许护士进行费用更改：</span>

                                    <input type="radio" name="allowNurseUpdatePrice" value="true"
                                           <c:if test="${doctor.allowNurseUpdatePrice}">checked='checked'</c:if>> 允许
                                    <input type="radio" name="allowNurseUpdatePrice" style="margin-left: 5px;"
                                           value="false"
                                           <c:if test="${!doctor.allowNurseUpdatePrice}">checked='checked'</c:if>> 不允许
                                </td>
                            </tr>
                        </c:if>

                        <tr>
                            <td colspan="4"><span>发送健康卡激活短信</span></td>
                        </tr>


                        <tr>
                            <td colspan="3"><span>默认发送激活短信</span>
                                <input type="radio" name="autoSendActivateMsg" value="是"
                                       <c:if test="${!'否'.equals(doctor.autoSendActivateMsg)}">checked='checked'</c:if>
                                > 是
                                <input type="radio" name="autoSendActivateMsg" style="margin-left: 5px;" value="否"
                                       <c:if test="${'否'.equals(doctor.autoSendActivateMsg)}">checked='checked'</c:if>
                                > 否
                                <span style="padding-left: 15px; color: #999;">（该短信免费）</span>
                            </td>
                        </tr>


                    </table>
                </div>

            </div>
        </div>
        <div class="change-bottom text-center" style="margin-top: 20px;">
            <button type="submit" class="btn btn-success sure-changes">确认修改</button>
            <button type="button" class="btn btn-success" style="width: 120px;" onclick="history.go(-1)">返回</button>
        </div>
        </form:form>
    </div>
</div>
<script src="${ctx}/assets/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script>
    $(function () {

        <c:if test="${doctor.doctorType=='Sub_Doctor'}">
            $(".outp-change").hide();
        </c:if>

        $("#provinceNo").change(function () {
            $("#cityNo").empty();
            $("#areaNo").empty();
            var proId = $(this).val();
            $.getJSON("${ctx}/anon/getNations/" + proId, function (result) {
                $.each(result.data, function (i, n) {
                    $("#cityNo").append("<option value='" + n.areaNo + "'>" + n.areaName + "</option>")
                });
                $("#cityNo").change();
            });
            var text = $("#provinceNo option:selected").text();
            $("#province").val(text);
        });
        $("#cityNo").change(function () {
            $("#areaNo").empty();
            var cityId = $(this).val();
            $.getJSON("${ctx}/anon/getNations/" + cityId, function (result) {
                $.each(result.data, function (i, n) {
                    $("#areaNo").append("<option value='" + n.areaNo + "'>" + n.areaName + "</option>")
                });
                $("#areaNo").change();
            });
            var text = $("#cityNo option:selected").text();
            $("#city").val(text);
        });
        $("#areaNo").change(function () {
            var text = $("#areaNo option:selected").text();
            $("#area").val(text);
        });

        /*默认发送回返短信选择*/
        $("input[name='autoSend']").change(function () {
            var choose;
            $("input[name='autoSend']").each(function () {
                if ($(this).prop("checked")) {
                    choose = $(this).prop("defaultValue");
                }
            });
            if ('是' == choose) {
                $("#isNeedShow").css("display", "-webkit-inline-box");
            } else {
                $("#isNeedShow").css("display", "none");
            }
        })

    });


    /**
     * 将页面选中的 chack box 批量传转化为String, 传递到后台
     */
    function getAlonePrintCheckBoxToString() {
        var tmpStr = "";

        $("input[type='checkbox']:checked").each(function () {
            if (tmpStr != "") {
                tmpStr += ("," + $(this).attr("name")  );
            } else {
                tmpStr += $(this).attr("name");
            }
        });

        $("#needAlonePrinTypeStrings").val(tmpStr);
        return true;
    }

    /**
     * 短信效果预览模板
     */
    var ss;
    function fn_ShowTemplate() {
        ss = layer.open({
            type: 2,
            title: '回访短信模板',
            area: ['450px', '280px'],
            scrollbar: false,
            content: '/msPage/msTemplate/'
        });
        $(".layui-layer-shade").click(function () {
            layer.closeAll();
        });
    }


</script>
</body>
</html>