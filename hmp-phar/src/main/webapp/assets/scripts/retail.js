/*药品选择滚轮动画*/
function animateMegTag() {
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
}

/*选择药厂弹框*/
function selectCompanyWin() {
    /* 选择药厂 */
    layer.open({
        type: 2,
        title: '选择添加药厂',
        area: ['650px', '320px'],
        scrollbar: false,
        content: '/adv/fragment/companies'
    });
}


function loadMedInfoRetail(name, page, retailId, barCode) {
    if (!name) {
        name = "";
    }
    if (!barCode || barCode == undefined) {
        barCode = "";
    }
    if (undefined == page) {
        page = 0;
    }
    var url = ctx + "/retail/getMedTag?name={0}&page={1}&retailId={2}&barCode={3}".format(name, page, retailId, barCode);
    $("#retailIntoMed").load(url);
}