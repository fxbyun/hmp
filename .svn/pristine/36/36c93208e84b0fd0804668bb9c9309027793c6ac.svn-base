<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/19
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="ctx" type="java.lang.String"--%>
<%--@elvariable id="tmpId" type="java.lang.String"--%>
<%--@elvariable id="type" type="java.lang.String"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/commons/staticInclude.jsp" %>
<%@ taglib prefix="hmp" uri="/WEB-INF/TLD/HmpLoadStaticFile.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="beanTmp" class="com.qiaobei.hmp.support.WeixinUtil"/>
<html>
<head>
    <title>拍摄照片</title>

    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/application.js" type="js"/>
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/typeahead/typeahead.bundle.js" type="js"/>
    <style type="text/css">
        .btn-style button, .btn-style02 button {
            width: 35%;
            height: 40px;
            margin: 5px 0;
            font-size: 14px;
        }

        .btn-style02 button {
            width: 20%;
        }

        .tt-menu, .tt-hint {
            width: 100%;
        }

        .twitter-typeahead {
            display: block !important;
        }
    </style>

    <Script language="javascript">
        $(function () {

            $('#btnClose').click(function () {
                exitCam(true);
            });
            console.info(advType);
            if (advType == "JianCha" || advType == "JianYan") {
            } else {
                $("#examLabDiv").hide()
            }
        });
        var nFileCount = 0;
        var strFile;
        var index = 0;

        function exitCam(exit) {
            var index = parent.layer.getFrameIndex(window.name);
            try {
                if (exit) {
                    var J_Y_Div = parent.$("input[value='${tmpId}']").parent().parent().parent();
                    if ($(J_Y_Div).find("div").size() == 1) {
                        $(J_Y_Div).html("");
                    } else {
                        parent.$("input[value='${tmpId}']").parent().parent().remove();
                    }
                }
                CmCaptureOcx.Destory();
            } catch (e) {
                parent.layer.close(index);
            }
            setTimeout(function () {
                parent.layer.close(index);
            }, 1000);
        }

        //开始拍照
        function Capture() {
            if (advType == "JianYan" || advType == "JianCha") {
                if (!haveSelect) {
                    parent.Alert.warning("请您先选择该检查或检验所属项目!");
                    return;
                }
            }
            strFile = "d:\\test\\test" + nFileCount + ".png";
            CmCaptureOcx.CaptureImage(strFile);
            // nFileCount++;
            showImages();

        }

        var devArr = [];
        /**
         * 增加摄像头
         * */
        function AddDevice() {
            var total = CmCaptureOcx.GetDevCount();
            for (i = 0; i < total; i++) {
                var DevEle = CmCaptureOcx.GetDevFriendName(i);
                devArr.push(i);
            }
        }
        function changeCam() {
            if (devArr.indexOf(index) + 1 >= devArr.length) {
                index = 0;
            } else {
                index += 1;
            }
            StartVideo();
        }
        /**
         *  初始化拍照
         * @constructor
         */
        function StartVideo(init) {

            var initRet = 0;
            try {
                initRet = CmCaptureOcx.Initial();
                if (init) {
                    AddDevice();
                }
            } catch (e) {
                console.info(e.message)
                if (e.message.indexOf("is not a function") > 0) {
                    parent.Alert.error("本浏览器不支持使用高拍仪或未安装高拍仪驱动");
                    return false;
                }
            }
            if (initRet == -2) {
                parent.Alert.error("未检测到高拍仪,请您检查高拍仪是否已经连接!");
                return;
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
        var advType = "${type}";
        function uploadFile() {
            if (advType == "JianYan" || advType == "JianCha") {
                if (!haveSelect) {
                    parent.Alert.warning("请您先选择该检查或检验所属项目!");
                    return;
                }
            }
            var urlHttp = "http://" + window.location.host;
            <c:if test="${!beanTmp.isLocal()}">
            urlHttp = "http://www.yijiazhen.com";
            </c:if>
            var url = urlHttp + "/pub/upLoadEmrFile?doctorId={0}&tmpId={1}&fileType={2}";
            url = url.format("${doctor.id}", "${tmpId}", advType);
            var call = CmCaptureOcx.UpdataFile(url, strFile, 0);//上传图片
            if (call == 1) {
                $.postJSON("/pub/imagesLoadStatus", {
                    fileName: "${tmpId}",
                    doctorId: "${doctor.id}"
                }, function (ret) {
                    if (ret.success) {
                        $("#captureBut").attr("disabled", "true");
                        $("#uploadBut").attr("disabled", "true");
                        parent.putEmrFileId("${tmpId}", advType, "${ctx}/fileDir/${doctor.id}/${tmpId}.png");
                        parent.Alert.success("图片上传完毕!");
                        exitCam(false);
                    } else {
                        parent.Alert.error("图片上传失败!");
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

        var haveSelect = false;
        function haveSelectExamLab(jClassAdviceDictId, adviceName, tmpCJ) {
            advType = tmpCJ;
            $.postJSON("/examLab/checkHaveDoctorUse",
                {
                    jClassAdviceDictId: jClassAdviceDictId
                }, function (ret) {
                    if (ret.success) {
                        haveSelect = true;
                        parent.Alert.success("您已经选择 " + adviceName);
                        ret = ret.data;
                        parent.fn_editItems(ret.id, ret.subId, ret.subTwoId, 'camSelect', "${tmpId}", true);
                    }
                });

        }
    </script>
    <SCRIPT>
        $(function () {
            var examLab_list = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.whitespace,
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                remote: {
                    url: '/examLab/query?key=%QUERY',
                    wildcard: '%QUERY'
                }
            });

            $('#examLabId').typeahead({
                minLength: 1,
                highlight: true
            }, {
                name: 'examLab_list',
                limit: 10,
                async: true,
                display: 'adviceName',
                source: examLab_list,
                templates: {
                    'empty': '<div class="tt-suggestion">无结果</div>',
                    'suggestion': function (o) {
                        var tmpCJ = o.adviceType;
                        o.adviceType = o.adviceType == "JianCha" ? "检查" : "检验";
                        console.info(o);
                        return '<div onclick="haveSelectExamLab({2},\'{1}\',\'{3}\')">{0} - <strong>{1}</strong> </div>'.format(o.adviceType, o.adviceName, o.id, tmpCJ);
//                        return '<div >{0} - <strong>{1}</strong> – {2}</div>';
                    }
                }
            });

        });
        setTimeout(function () {
            new StartVideo("init");
        }, 100);
    </SCRIPT>
</head>
<body style="background-color: #fff;">
<div class="container">
    <div class="row" style="margin-top: 10px;">

        <%--<div style="display:
        <c:if test="${type!='JianYan'&& type!='JianCha'}">none</c:if>
        <c:if test="${type=='JianYan'||type=='JianCha'}">block</c:if>">
            <label class="col-xs-3 col-sm-3 text-right" style="padding: 0;">检查/检验名称：</label>
            <div class="col-xs-9 col-sm-9">
                <div class="form-group">
                    <input type="text" class="form-control" id="examLabId">
                </div>
            </div>
            &lt;%&ndash;<label class="col-xs-3 col-sm-3 text-right" style="padding: 0;">检查详情：</label>&ndash;%&gt;
            &lt;%&ndash;<div class="col-xs-9 col-sm-9">&ndash;%&gt;
            &lt;%&ndash;<div class="form-group">&ndash;%&gt;
            &lt;%&ndash;<textarea class="form-control"></textarea>&ndash;%&gt;
            &lt;%&ndash;</div>&ndash;%&gt;
            &lt;%&ndash;</div>&ndash;%&gt;
        </div>--%>
        <div class="col-xs-6 col-sm-6"<%-- style="width: 305px"--%>>
            <div class="form-group" <%--style="width: 300px"--%>>
                <object id="CmCaptureOcx"
                        TYPE="application/xhanhan-activex"
                        BORDER="0"
                        clsid="{3CA842C5-9B56-4329-A7CA-35CA77C7128D}"
                        style="height: 310px; width: 100%; border:1px solid #ccc;">


                </object>
            </div>


        </div>
        <div class="col-xs-6 col-sm-6">
            <div class="form-group">
                <object
                        id="CmCaptureOcxLsu"
                        TYPE="application/xhanhan-activex"
                        BORDER="0"
                        clsid="{A3DAB43A-EA04-4E95-8054-692B40659F65}"
                        style="width:100%; height: 150px; border: 1px solid #ccc;">

                </object>

            </div>
            <div class="form-group">
                <div class="row" id="examLabDiv">
                    <label class="col-xs-12 col-sm-12 text-left" style="font-size: 16px;">检查/检验名称</label>
                    <div class="col-xs-12 col-sm-12">
                        <div class="form-group">
                            <input type="text" class="form-control" id="examLabId" title="">
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-group btn-style text-center">

                <button type="button"
                        class="btn btn-success" style="margin-right: 10px;"
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
            </div>

        </div>
        <div class="col-xs-12 col-sm-12">
            <div class="form-group btn-style02 text-center">
                <button type="button"
                        class="btn btn-success"
                        id="sxVideoBut"
                        readonly="true"
                        disabled
                        onclick="StartVideo()">
                    刷新视频
                </button>
                <button type="button"
                        class="btn btn-default" style="margin: 0 20px;"
                        onclick="changeCam()"
                        id="btnQh">切换摄像头
                </button>
                <button type="button"
                        class="btn btn-default"
                        id="btnClose">取消
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
