﻿var CreatedOKLodop7766 = null;

//====判断是否需要安装CLodop云打印服务器:====
function needCLodop() {
    try {
        var ua = navigator.userAgent;
        if (ua.match(/Windows\sPhone/i) != null) return true;
        if (ua.match(/iPhone|iPod/i) != null) return true;
        if (ua.match(/Android/i) != null) return true;
        if (ua.match(/Edge\D?\d+/i) != null) return true;
        if (ua.match(/QQBrowser/i) != null) return false;
        var verTrident = ua.match(/Trident\D?\d+/i);
        var verIE = ua.match(/MSIE\D?\d+/i);
        var verOPR = ua.match(/OPR\D?\d+/i);
        var verFF = ua.match(/Firefox\D?\d+/i);
        var x64 = ua.match(/x64/i);
        if ((verTrident == null) && (verIE == null) && (x64 !== null))
            return true; else if (verFF !== null) {
            verFF = verFF[0].match(/\d+/);
            if (verFF[0] >= 42) return true;
        } else if (verOPR !== null) {
            verOPR = verOPR[0].match(/\d+/);
            if (verOPR[0] >= 32) return true;
        } else if ((verTrident == null) && (verIE == null)) {
            var verChrome = ua.match(/Chrome\D?\d+/i);
            if (verChrome !== null) {
                verChrome = verChrome[0].match(/\d+/);
                if (verChrome[0] >= 42) return true;
            }
        }
        return false;
    } catch (err) {
        return true;
    }
    ;
};

//====页面引用CLodop云打印必须的JS文件：====
if (needCLodop()) {
    var head = document.head || document.getElementsByTagName("head")[0] || document.documentElement;
    var oscript = document.createElement("script");
    oscript.src = "http://localhost:8000/CLodopfuncs.js?priority=1";
    try {

    } catch (e) {
        head.insertBefore(oscript, head.firstChild);
    }

    //本机云打印的后补端口8001：
    // oscript = document.createElement("script");
    // oscript.src = "http://localhost:8001/CLodopfuncs.js?priority=2";
    // head.insertBefore(oscript, head.firstChild);
}
;

//====获取LODOP对象的主过程：====
function getLodop(oOBJECT, oEMBED) {
    Alert.success("", "打印任务进行中,请勿重复点击!");
    var strHtmInstall = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='/assets/print.lodop/CLodop_Setup_for_Win32NT.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtmUpdate = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='/assets/print.lodop/CLodop_Setup_for_Win32NT.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtm64_Install = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='/assets/print.lodop/CLodop_Setup_for_Win64NT.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtm64_Update = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='/assets/print.lodop/CLodop_Setup_for_Win64NT.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtmFireFox = "<br><br><font color='#FF00FF'>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</font>";
    var strHtmChrome = "<br><br><font color='#FF00FF'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</font>";
    var strCLodopInstall = "<br><font color='#FF00FF'>CLodop云打印服务(localhost本地)未安装启动!点击这里<a href='/assets/print.lodop/CLodop_Setup_for_Win32NT.exe' target='_self'>执行安装</a>,安装后请刷新页面。</font>";
    var strCLodopUpdate = "<br><font color='#FF00FF'>CLodop云打印服务需升级!点击这里<a href='/assets/print.lodop/CLodop_Setup_for_Win32NT.exe' target='_self'>执行升级</a>,升级后请刷新页面。</font>";
    var LODOP;
    try {
        var isIE = (navigator.userAgent.indexOf('MSIE') >= 0) || (navigator.userAgent.indexOf('Trident') >= 0);
        if (needCLodop()) {
            try {
                LODOP = getCLodop();
            } catch (err) {
            }
            ;
            if (!LODOP && document.readyState !== "complete") {
                // alert("C-Lodop没准备好，请稍后再试！");
                Alert.info("请稍后再试!", "打印插件还没准备好!");
                return;
            }
            ;
            if (!LODOP) {
                if (isIE) {
                    Alert.info("请先安装打印插件!", "打印插件需要安装!");
                    showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win32NT.exe");
                }
                else {
                    Alert.info("请先安装打印插件!", "打印插件需要安装!");
                    showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win32NT.exe");
                }

                return;
            } else {

                if (CLODOP.CVERSION < "2.0.6.2") {
                    if (isIE) {
                        Alert.info("请选升级您的打印插件!", "打印插件需要升级!");
                        showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win32NT.exe");
                    }
                    else {
                        Alert.info("请选升级您的打印插件!", "打印插件需要升级!");
                        showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win32NT.exe");
                    }
                }
                ;
                if (oEMBED && oEMBED.parentNode) oEMBED.parentNode.removeChild(oEMBED);
                if (oOBJECT && oOBJECT.parentNode) oOBJECT.parentNode.removeChild(oOBJECT);
            }
            ;
        } else {
            var is64IE = isIE && (navigator.userAgent.indexOf('x64') >= 0);
            //=====如果页面有Lodop就直接使用，没有则新建:==========
            if (oOBJECT != undefined || oEMBED != undefined) {
                if (isIE) LODOP = oOBJECT; else  LODOP = oEMBED;
            } else if (CreatedOKLodop7766 == null) {
                LODOP = document.createElement("object");
                LODOP.setAttribute("width", 0);
                LODOP.setAttribute("height", 0);
                LODOP.setAttribute("style", "position:absolute;left:0px;top:-100px;width:0px;height:0px;");
                if (isIE) LODOP.setAttribute("classid", "clsid:2105C259-1E0C-4534-8141-A753534CB4CA");
                else LODOP.setAttribute("type", "application/x-print-lodop");
                document.documentElement.appendChild(LODOP);
                CreatedOKLodop7766 = LODOP;
            } else LODOP = CreatedOKLodop7766;
            //=====Lodop插件未安装时提示下载地址:==========
            if ((LODOP == null) || (typeof(LODOP.VERSION) == "undefined")) {
                if (navigator.userAgent.indexOf('Chrome') >= 0)
                    Alert.info("如果此前正常，仅因浏览器升级或重安装而出问题，请重新安装打印插件!", "打印插件异常!");
                // document.documentElement.innerHTML = strHtmChrome + document.documentElement.innerHTML;
                if (navigator.userAgent.indexOf('Firefox') >= 0)
                    Alert.info("如果此前正常，仅因浏览器升级或重安装而出问题，请重新安装打印插件!", "打印插件异常!");
                // document.documentElement.innerHTML = strHtmFireFox + document.documentElement.innerHTML;
                Alert.error("打印插件未安装,请先安装打印插件!", "打印插件未安装!");
                if (is64IE)
                    showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win64NT.exe");
                else if (isIE)
                    showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win32NT.exe");
                else
                // document.documentElement.innerHTML = strHtmInstall + document.documentElement.innerHTML;
                    showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win32NT.exe");
                return LODOP;
            }
            ;
        }
        ;
        if (LODOP.VERSION < "6.2.0.5") {
            Alert.info("请选升级您的打印插件!", "打印插件需要升级!");
            if (needCLodop())
                showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win32NT.exe");
            // document.documentElement.innerHTML = strCLodopUpdate + document.documentElement.innerHTML;
            else if (is64IE)
            // document.write(strHtm64_Update);
                showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win64NT.exe");
            else if (isIE)
            // document.write(strHtmUpdate);
                showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win32NT.exe");
            else
            // document.documentElement.innerHTML = strHtmUpdate + document.documentElement.innerHTML;
                showInstallWindows("/assets/print.lodop/CLodop_Setup_for_Win32NT.exe");
            toastr.options["timeOut"] = tmp;
            return LODOP;
        }
        //===如下空白位置适合调用统一功能(如注册语句、语言选择等):===

        return LODOP;
    } catch (err) {
        Alert.error("getLodop出错:" + err);
    }
    ;
};

function showInstallWindows(url) {
    try {
        layer.open({
            type: 2,
            title: false,
            closeBtn: 2, //不显示关闭按钮
            shade: [0],
            area: ['340px', '215px'],
            offset: 'auto', //右下角弹出
            time: 0, //2秒后自动关闭
            shift: 2,
            content: ['/pub/showLodopDownPage?url=' + url, 'no'], //iframe的url，no代表不显示滚动条
            end: function () { //此处用于演示
            }
        });
    } catch (e) {
    }
}