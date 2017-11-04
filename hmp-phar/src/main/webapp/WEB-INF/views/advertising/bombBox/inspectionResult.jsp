<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/20
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%--@elvariable id="emrJClass" type="com.qiaobei.hmp.modules.entity.EmrJClassAdviceDict"--%>
<%--@elvariable id="msg" type="java.lang.String"--%>
<%--@elvariable id="doctor" type="com.qiaobei.hmp.modules.entity.Doctor"--%>
<html>
<head>
    <title>检查结果</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/advertising/scripts/advertisingIndex.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/toastr/toastr.css" type="css"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/toastr/toastr.ext.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/toastr/toastr.js" type="js"/>
    <script type="application/javascript">
        /**
         * 检查文件类型和大小
         */
        function checkFileType() {
            var files = document.getElementById("files").files;
            for (var i = 0; i < files.length; i++) {
                var fName = files[i].name;
                if (fName.indexOf(".jpg") < 0 && fName.indexOf(".png") < 0 && fName.indexOf(".pdf") < 0) {
                    layer.msg("仅支持上传jpg/png/pdf 类型的文件,请重新选择上传!")
                    var file = $("#files");
                    file.after(file.clone().val(""));
                    file.remove();
                    break;
                }
                var file_size = files[i].size;
                var size = file_size / 1024;
                if (size > 1024) {
                    layer.msg("上传的图片大小不能超过1M");
                    var file = $("#files");
                    file.after(file.clone().val(""));
                    file.remove();
                    break;
                }
            }
        }

        /**
         *  文件 地址 和文件类型
         * @param url
         * @param type
         */
        function openFileWindow(url, type) {
            console.info(url + "-----" + type);
            window.open(url);
        }
    </script>
</head>
<body style="background-color: #fff;">
<form action="${ctx}/adv/bombBox/updateInspectionResult" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${emrJClass.id}">
    <div class="charge-content">
        <div class="form-group">
            <span>患者姓名</span><input type="text" class="form-control adv-txt" value="${emrJClass.emr.patient.name}"
                                    disabled title="">
        </div>
        <div class="form-group">
            <span>检查项目</span><input type="text" class="form-control adv-txt" value="${emrJClass.adviceName}" disabled
                                    title="">
        </div>
        <div class="form-group">
            <span style="vertical-align: top;">检查详情</span><textarea class="form-control"
                                                                    style="display: inline-block;
                                                                    width: 80%;
                                                                    margin-left: 15px;
                                                                    height: 100px;"
                                                                    name="resultInfo"
                                                                    <c:if test="${not empty msg}">disabled</c:if>
                                                                    title="">${emrJClass.resultInfo}</textarea>
        </div>
        <div class="form-group" style="min-height: 100px;">


            <c:choose>

                <c:when test="${ empty msg}">
                    <span>上传附件</span>
                    <input type="file" id="files" name="files"
                           onchange="checkFileType()" multiple
                           style="display: inline-block; margin-left: 15px;">
                </c:when>
                <c:otherwise>
                    <span>查看附件</span>
                    <c:forEach items="${emrJClass.examLabFileList}" var="oneJClass">
                        <c:if test="${not empty oneJClass.fileName}">
                            <input type="button"
                                   onclick="openFileWindow('${ctx}/fileDir/${doctor.id}/${oneJClass.fileName}','${oneJClass.types}')"
                                <%--onclick="fn_ImageShow()"--%>
                                   value="${oneJClass.fileName}"
                                   class="btn btn-warning"
                                   style="display: inline-block; margin-left: 15px;">
                        </c:if>
                    </c:forEach>

                </c:otherwise>

            </c:choose>


            <c:if test="${ msg=='OK'}">
                <span style="color:red;">上传成功</span>
            </c:if>


            <%--  此处是查看报告时，点击文件预览样式  --%>
            <%--<a href="#" title="xxxxx" style="margin-left: 15px; color: #218e3f;">xxxxxx.jpg</a>--%>

        </div>
        <div class="text-center" style="margin-top: 28px;">
            <c:choose>
                <c:when test="${ empty msg}">
                    <button type="button" class="btn btn-success"
                            onclick="takePhotosBox('${doctor.id}','${emrJClass.id}')">拍摄照片
                    </button>
                    <button type="submit" class="btn btn-success">确认提交
                    </button>
                    <button id="btnClose" type="button" class="btn btn-default" style="width: 80px; margin-left: 15px;">
                        取消
                    </button>
                </c:when>
                <c:otherwise>
                    <button id="btnClose" type="button" class="btn btn-default" style="width: 80px;">关闭
                    </button>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</form>
</body>
</html>
