<%--@elvariable id="tmpId" type="java.lang.String"--%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/19
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/advInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<html>
<head>
    <title>拍摄照片</title>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <style type="text/css">
        .btn-style button {
            width: 80%;
            height: 40px;
            margin: 5px 0;
            font-size: 16px;
        }
    </style>

    <Script language="javascript">
        $(function () {

            $('#btnClose').click(function () {
                exitCam();
            });
        });
        var nFileCount = 0;
        var strFile;
        var index = 0;

        function exitCam() {
            try {
                CmCaptureOcx.Destory();
            } catch (e) {

            }
            var index = parent.layer.getFrameIndex(window.name);
            setTimeout(function () {
                parent.layer.close(index);
            }, 1000);
        }

        //开始拍照
        function Capture() {
            strFile = "d:\\test\\" + nFileCount + ".png";
            CmCaptureOcx.CaptureImage(strFile);
            // nFileCount++;
            showImages();

        }
        /**
         *  初始化拍照
         * @constructor
         */
        function StartVideo() {
            var initRet = CmCaptureOcx.Initial();
            if (initRet == -2) {
                alert("初始化失败,设备未插入!");
            }
            var callBack = CmCaptureOcx.StartRun(index);
            if (callBack != 0) {
                $("#sxVideoBut").removeAttr("disabled");

                $("#captureBut").removeAttr("disabled");
            }
            setTimeout(function () {
                CmCaptureOcx.AutoCrop(1);//开启自动裁切功能
                CmCaptureOcx.SetResolution(5);//设置分别率为1280*960

                setTimeout(function () {
                    CmCaptureOcx.SetFileType(3);//设置图片类型为png
                    CmCaptureOcx.SetImageColorMode(1);//设置打印模式为灰度
//                    CmCaptureOcx.RotateVideo(1);//旋转
                }, 2000);
            }, 1000);
        }
        /**
         * 上传文件
         */
        function uploadFile() {
            var url = "http://127.0.0.1:8086/pub/upLoadEmrFile?doctorId={0}&tmpId={1}&id={2}";
            url = url.format("${doctorId}", "${tmpId}", "${id}");
            var call = CmCaptureOcx.UpdataFile(url, strFile, 0);//上传图片
            if (call == 1) {
                $.postJSON("${ctx}/pub/imagesLoadStatus", {
                    fileName: "${tmpId}",
                    doctorId: "${doctorId}"
                }, function (ret) {
                    if (ret.success) {
                        $("#captureBut").attr("disabled", "true");
                        $("#uploadBut").attr("disabled", "true");
                        parent.Alert.success("图片上传完毕!");
                        parent.$("form").submit();
                        exitCam();
                    } else {
                        alert("图片上传失败!");
                    }
                })
            }
        }
        /**
         * 图片预览
         */
        function showImages() {
            CmCaptureOcxLsu.setImage(index, strFile);
            $("#uploadBut").removeAttr("disabled");
        }
    </script>
    <SCRIPT>
        $(function () {

        });
        setTimeout(function () {
            new StartVideo();
        }, 100);
    </SCRIPT>
</head>
<body style="background-color: #fff;">
<div class="container">
    <div class="row" style="margin-top: 10px;">
        <div class="col-xs-8 col-sm-8"<%-- style="width: 305px"--%>>
            <div class="form-group" <%--style="width: 300px"--%>>
                <object id="CmCaptureOcx"
                        TYPE="application/xhanhan-activex"
                        BORDER="0"
                        clsid="{3CA842C5-9B56-4329-A7CA-35CA77C7128D}"
                        style="height: 93%; width: 100%; border:1px solid #ccc;">


                </object>
            </div>
        </div>
        <div class="col-xs-4 col-sm-4" style="padding-left: 0;">
            <div class="form-group">
                <object
                        id="CmCaptureOcxLsu"
                        TYPE="application/xhanhan-activex"
                        BORDER="0"
                        clsid="{A3DAB43A-EA04-4E95-8054-692B40659F65}"
                        style="width:100%; height: 40%; border: 1px solid #ccc;">

                </object>

            </div>
            <div class="form-group btn-style text-center">
                <button type="button"
                        class="btn btn-success"
                        id="sxVideoBut"
                        readonly="true"
                        disabled
                        onclick="StartVideo()">
                    刷新视频
                </button>
                <button type="button"
                        class="btn btn-success"
                        id="captureBut"
                        readonly="true"
                        disabled
                        onclick="Capture()">拍摄照片
                </button>
                <button type="button"
                        class="btn btn-success"
                        id="uploadBut"
                        onclick="uploadFile()"
                        disabled>确定上传
                </button>
                <button type="button"
                        class="btn btn-default"
                        id="btnClose">关闭
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
