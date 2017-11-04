<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<div class="row talk-tab">
    <div class="tab-box-list">
        <c:forEach var="tag" items="${symptomPage.content}">
            <span class="tag" onclick="fn_SelectSymptomOtherTag('${tag}')">${tag}</span>
        </c:forEach>
    </div>

    <div class="keyboard">
        <div class="row text-center">
            <button type="button" onclick="fn_SelectQtyUnitTag('1')" class="btn btn-default" value="1">1</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('2')" class="btn btn-default" value="2">2</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('3')" class="btn btn-default" value="3">3</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('4')" class="btn btn-default" value="4">4</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('5')" class="btn btn-default" value="5">5</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('6')" class="btn btn-default" value="6">6</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('7')" class="btn btn-default" value="7">7</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('8')" class="btn btn-default" value="8">8</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('9')" class="btn btn-default" value="9">9</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('0')" class="btn btn-default" value="0">0</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('.')" class="btn btn-default " value="."
                    style="width: 109px;">.
            </button>

        </div>
        <div class="row text-center">
            <button type="button" onclick="fn_SelectQtyUnitTag(',')" class="btn btn-default" value=",">,</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('~')" class="btn btn-default" value="~">~</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('小时')" class="btn btn-default double " value="小时">小时
            </button>
            <button type="button" onclick="fn_SelectQtyUnitTag('天')" class="btn btn-default double " value="天">天
            </button>
            <button type="button" onclick="fn_SelectQtyUnitTag('周')" class="btn btn-default  double" value="周">周
            </button>
            <button type="button" onclick="fn_SelectQtyUnitTag('月')" class="btn btn-default" value="月">月</button>
            <button type="button" onclick="fn_SelectQtyUnitTag('年')" class="btn btn-default" value="年">年</button>
            <button type="button" onclick="fn_BackspaceSymptomTag()" class="btn btn-default " style="width: 109px;"><i
                    class="fa fa-arrow-left"></i></button>
        </div>
    </div>
</div>

<div class="form-group btn-boxs">

    <button type="button" onclick="loadSymptoms(0)" class="btn btn-default"> 常用症状</button>
    <label class="btn btn-default">症状库</label>

    <div class="up-dpwn-bt">

        <c:if test="${symptomPage.number > 0}">
            <button type="button" onclick="loadSymptomOthers(${symptomPage.number - 1})" class="btn btn-info"> 上一批</button>
        </c:if>
        <c:if test="${symptomPage.number + 1 < symptomPage.totalPages}">
            <button type="button" onclick="loadSymptomOthers(${symptomPage.number + 1})" class="btn btn-info">下一批 </button>
        </c:if>
        <div class="pull-right">

            <div class="input-group" style="display: inline-block">
                <input id="txtSymptomTag" value="${name}" type="text" class="form-control" style="width: 135px;" placeholder="症状">
                <span class="input-group-btn">
                    <button onclick="fn_LoadSymptomOtherTag()" class="btn btn-primary" type="button"><i class="fa fa-times"></i></button>
                    <button onclick="loadSymptomOthers(0)" style="border-left: 1px solid #fff;" class="btn btn-primary" type="button"><i class="fa fa-search"></i></button>
                </span>
            </div>
            <button onclick="addSymptomTag();" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加症状</button>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#txtSymptomTag').keydown(function (event) {
        if (event.keyCode == 13) {
            loadSymptomOthers(0);
            return false;
        }
    });
    if ($('#txtSymptomTag').val()) {
        $('#txtSymptomTag').focus();
        $('#txtSymptomTag').select();
    }
    function loadSymptoms(page) {
        var name = $('#txtSymptomTag').val();
        fn_LoadSymptomTag(page, name);
    }
    function loadSymptomOthers(page) {
        var name = $('#txtSymptomTag').val();
        fn_LoadSymptomOtherTag(page, name);
    }
    function addSymptomTag() {
        layer.prompt({title: '添加症状', maxlength: 200}, function (value, index, elem) {
            $.postJSON("/tag/addSymptomTag", {"symptomTag": value,
                "helpCode": pinyin.getCamelChars(value)}, function (res) {
                if (res && res.success) {
                    if (res.msg)layer.msg(res.msg);
                    loadSymptoms(0);
                    var arr = $.map($('#divSymptom span input[name="mainSuits"]'), function (n, i) {
                        return $(n).val();
                    });
                    if ($.inArray(value, arr) == -1) {
                        fn_SelectSymptomTag(res.data.id, res.data.name);
                    }
                }
                layer.close(index)
            })
        });
    }
</script>