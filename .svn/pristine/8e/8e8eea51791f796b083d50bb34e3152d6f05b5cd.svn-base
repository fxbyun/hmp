$(function () {

    $("#nav-manage").addClass("active");

    var index = parent.layer.getFrameIndex(window.name);
    $("#btnClose").click(function () {
        parent.layer.close(index);
    });

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

    /* 选择药厂 */
    $('#btnSelectCompany').click(function () {
        layer.open({
            type: 2,
            title: '选择添加药厂',
            area: ['650px', '320px'],
            scrollbar: false,
            content: '/fragment/companies'
        });
    });
    var iaiIntoId = $("#iaiIntoId").val();

    //药品加载
    loadMedInfoList("",1,iaiIntoId);



});
/**
 * 加载药品list
 * @param name
 * @param page
 */
function loadMedInfoList(name, page,iaiIntoId) {

    if (!name) {
        name = "";
    }
    if (!page) {
        page = 1;
    }
    var url;
    if(iaiIntoId==undefined){
        url = "/validityManage/bombBox/getMedInfo?name={0}&page={1}".format(name, page);
    }else {
        url = "/validityManage/bombBox/getMedInfo?name={0}&page={1}&iaiIntoId={2}".format(name, page,iaiIntoId);
    }

    $("#medListDiv").load(url);
}





function searchMed(iaiIntoId) {
    loadMedInfoList($("#medNameInput").val(),0,iaiIntoId);
}

/**
 *  选择药厂以后为 输入框填充值
 * @param data
 */
function fn_AddCompanies(data) {
    $("#medAddress").val(data[0].name);
    $("#medAddressId").val(data[0].id);
}

/* 表格的删除操作  */
function deleteValListTr(even,detailId) {
    $.postJSON("/validityManage/delDetail",{"detailId":detailId},function (data) {
        if(data.success==true){
            layer.msg("药品已从入库单中删除！")
        }else {
            layer.msg("药品删除失败！");
        }

    })


    $(even).parent().parent().remove();

    event.stopPropagation();//阻止事件冒泡
}

function deleteIaiInto(even,iaiIntoId) {
    event.stopPropagation();//阻止事件冒泡
    //$(even).attr("onclick","");
    layer.confirm("你确定要删除该入库订单？",function () {
        $.postJSON("/validityManage/delIaiInto",{"iaiIntoId":iaiIntoId},function (data) {
            if(data.success==true){
                layer.msg("入库单已成功删除！")
            }else {
                layer.msg("入库单删除失败！");
            }

        })
        $(even).parent().parent().remove();

    })
}

function deleteIaiLoss(even,lossId) {
    event.stopPropagation();//阻止事件冒泡
    $(even).attr("onclick","");
    layer.confirm("你确定要删除该损耗单？",function () {
        $.postJSON("/validityManage/delIaiLoss",{"lossId":lossId},function (data) {
            if(data.success==true){
                layer.msg("损耗单已成功删除！")
            }else {
                layer.msg("损耗单删除失败！");
            }

        })
        $(even).parent().parent().remove();

    })
}



function deleteIaiIntoRep(even,iaiIntoId) {
    event.stopPropagation();//阻止事件冒泡
    $(even).attr("onclick","");
    layer.confirm("你确定要删除该智能补货订单？",function () {
        $.postJSON("/validityManage/delIaiInto",{"iaiIntoId":iaiIntoId},function (data) {
            if(data.success==true){
                layer.msg("入库单已成功删除！")
            }else {
                layer.msg("入库单删除失败！");
            }

        })
        $(even).parent().parent().remove();

    })
}




/* 点击药品  弹出编辑药品弹框  */

function saveMedicine(medId,iaiIntoId,iaiDetailId,flag) {
    var url;
    if(iaiDetailId==undefined||iaiDetailId==""){
        url = "/validityManage/bombBox/saveMedicine?medId={0}&iaiIntoId={1}&fromHtml={2}".format(medId,iaiIntoId,flag);
    }else {
        url = '/validityManage/bombBox/saveMedicine?medId={0}&iaiIntoId={1}&iaiDetailId={2}&fromHtml={3}'.format(medId,iaiIntoId,iaiDetailId,flag);
    }

    layer.open({
        type: 2,
        maxmin: false,
        title: '编辑药品',
        area: ['700px', '450px'],
        scrollbar: false,
        content: url
    });
}

/*弹出智能补货的药品弹框*/
function openReplenishMedicine(medId, iaiIntoId, iaiDetailId, flag) {
    var url;
    if (iaiDetailId == undefined || iaiDetailId == "") {
        url = "/validityManage/bombBox/saveMedicine?medId={0}&iaiIntoId={1}&fromHtml={2}".format(medId, iaiIntoId, flag);
    } else {
        url = '/validityManage/bombBox/saveMedicine?medId={0}&iaiIntoId={1}&iaiDetailId={2}&fromHtml={3}'.format(medId, iaiIntoId, iaiDetailId, flag);
    }
    layer.open({
        type: 2,
        maxmin: false,
        title: '编辑药品',
        area: ['700px', '350px'],
        scrollbar: false,
        content: url
    });
}



/*弹出入库药品的编辑框*/
function openIncomeWindow(medId,iaiIntoId,iaiDetailId,flag) {
    var url;
    if(iaiDetailId==undefined||iaiDetailId==""){
        url = "/validityManage/bombBox/saveMedicine?medId={0}&iaiIntoId={1}&fromHtml={2}".format(medId,iaiIntoId,flag);
    }else {
        url = '/validityManage/bombBox/saveMedicine?medId={0}&iaiIntoId={1}&iaiDetailId={2}&fromHtml={3}'.format(medId,iaiIntoId,iaiDetailId,flag);
    }
    layer.open({
        type: 2,
        maxmin: false,
        title: '编辑药品',
        area: ['700px', '450px'],
        scrollbar: false,
        content: url
    });
}




/*  显示订单详情  */

function showOredrDetail(even) {
    window.location.href = "/validityManage/replenishment";
}

/* 添加药品 */
function addMedicine() {
    layer.confirm('请问您要添加什么类型的药品？', {
        btn: ['西药及中成药', '中药', '取消'] //按钮
    }, function () {
        fn_AddMedicine('Western', '西药及中成药房', 'iaiType');
    }, function () {
        fn_AddMedicine('Chinese', '中草药房', 'iaiType');
    }, function () {
        layer.close();
    });
}

/* 订单详细弹框 */
function orderDetails() {
    layer.open({
        type: 2,
        maxmin: false,
        title: '补货订单详情',
        area: ['400px', '360px'],
        scrollbar: false,
        content: '/validityManage/bombBox/showReplenish'
    });
}

/* 供应商管理 弹框 */
function supplierManage(obj) {
    /*var url = "/validityManage/bombBox/manageSupplier?supplier={0}".format(supplierId);*/
    var url = "/validityManage/bombBox/manageSupplier";
    layer.open({
        type: 2,
        maxmin: false,
        title: '供应商管理',
        area: ['580px', '420px'],
        scrollbar: false,
        content: '/validityManage/bombBox/manageSupplier'
    });
}

/* 编辑供应商 */
function fn_EditSupplier(supplierId) {
    layer.open({
        type: 2,
        title: '修改供应商',
        area: ['400px', '180px'],
        scrollbar: false,
        content: '/validityManage/bombBox/editSupplier?supplierId='+supplierId,
        end:supplierReload
    });
}

/* 添加供应商 */
function fn_AddSupplier() {
    layer.open({
        type: 2,
        title: '添加供应商',
        area: ['400px', '180px'],
        scrollbar: false,
        content: '/validityManage/bombBox/addSupplier',
        end:supplierReload
    });
}

/* 删除供应商 */
function delSupplier(ele,supplierId) {
    layer.confirm("你确定要删除该供应商吗？", function () {
        var url = "/validityManage/bombBox/delSupplier";
        $.postJSON(url,{"supplierId":supplierId},function (data) {
            if(data.success==true){
                layer.msg(data.msg);
                $(ele).parent().parent().remove();
            }else{
                layer.msg(data.msg);
            }
        })
    });


}

function saveSupplier(supplierId) {
    var supplierName = $("#supplierName").val();
    var url = "/validityManage/bombBox/saveSupplier";
    if(supplierId==null||supplierId==undefined){
        $.postJSON(url,{"name":supplierName},function (data) {
            if(data.success==true){
                //#FangXB 这里还在调
                layer.msg("添加成功！");
                /*var index = parent.layer.getFrameIndex(window.name);
                 parent.layer.closeAll();*/

            }else {
                layer.msg("添加失败！");
            }
        })
    }else {
        $.postJSON(url,{"id":supplierId,"name":supplierName},function (data) {
            if(data.success==true){
                layer.msg("编辑成功！");
                /*var index = parent.layer.getFrameIndex(window.name);
                 parent.layer.closeAll();*/

            }else {
                layer.msg("编辑失败！");
            }
        })
    }

}

function supplierReload() {
    window.location.reload();
}


/* 点击tr 弹出编辑药品弹框  */

function editMedicine() {
    layer.open({
        type: 2,
        maxmin: false,
        title: '编辑药品',
        area: ['700px', '450px'],
        scrollbar: false,
        content: '/validityManage/bombBox/editMedicine'
    });
}

function fn_LoadIaiIntoDetailPage(iaiIntoId,page) {
    $("#iaiDetail").load("/fragment/validityManage/showIaiDetails?iaiIntoId={0}&page={1}".format(iaiIntoId,page));
}





