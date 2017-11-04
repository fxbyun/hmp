/**
 * Created by Administrator on 2016/9/8.
 */
$(function () {
    $("#nav-system").addClass("active");
    if ($("#indate").val() == "") {
        $("#indate").val("0");
    }
    if ($("#orderNo").val() == "") {
        $("#orderNo").val("0");
    }
    $("input[type='radio']").change(function () {
        var type = $("input[type='radio']:checked").val();
        if (type == 'Text') {
            $(".txt").show();
            $(".txtImg").hide();
        } else if (type == 'Pic') {
            $(".txt").hide();
            $(".txtImg").show();
        }
    });

    if ($("input[type='radio']:checked")) {
        if ($("input[type='radio']:checked").val() == 'Text') {
            $(".txt").show();
            $(".txtImg").hide();
        } else if ($("input[type='radio']:checked").val() == 'Pic') {
            $(".txt").hide();
            $(".txtImg").show();
        }
    }

    $("#file").bind("change", function () {

        clickupLoad();

        if ($("#showImg").attr("src") == null || $("#showImg").attr("src") == "") {
            $("#imgWidth").show();
        } else {
            $("#imgWidth").hide();
        }
    });

});

//图片上传 预览
function clickupLoad() {

    var imgObject = document.getElementById('file');
    var getImageSrc = getFullPath(imgObject); // 本地路径
    var srcs = window.URL.createObjectURL(imgObject.files[0]);
    var pos = getImageSrc.lastIndexOf(".");
    var lastname = getImageSrc.substring(pos, getImageSrc.length) // 图片后缀]

    if (srcs != "") {
        $("#showImg").attr("src", srcs);
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

function deleteAdving(id) {
    if (id == undefined || id == "") {
        return false;
    }
    $.postJSON("/deleteAdving", {id: id}, function (backData) {
        if (backData.success) {
            Alert.success("删除完毕!");
            setTimeout(function () {
                window.location.href = "/advertSet";
            }, 1000);
        } else {
            Alert.error("删除失败!")
        }
    })
}
function sub() {
    if ($("input[name='type']:checked").val() == "Text") {
        if ($("#content").val().length == 0) {
            Alert.warning("请输入公告内容!");
            return
        }
    } else {
        if ($("#file").val() == "" && $("#adId").val() == "") {
            Alert.warning("请选择上传图片!")
            return;
        }
    }

    if ($("#indate").val() == "") {
        Alert.warning("请输入有效期!");
        return false;
    }
    if ($("#orderNo").val() == "") {
        Alert.warning("请输入序号!");
        return false;
    }


    $("#advertForm").submit();
}
