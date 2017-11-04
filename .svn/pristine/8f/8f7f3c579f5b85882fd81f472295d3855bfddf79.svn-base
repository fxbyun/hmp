<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<c:forEach var="tag" items="${symptomPage.content}">
    <span class="tag span-tag" onclick="fn_SelectSymptomTag('${tag.id}','${tag.name}')">${tag.name}</span>
</c:forEach>


<div class="form-group text-right" style="position: absolute; bottom: 10px; right: 0;">
    <%--<div class="col-sm-3 col-xs-3 col-lg-3 text-left">
        &lt;%&ndash;<label class="btn btn-warning">常用症状</label>&ndash;%&gt;
        &lt;%&ndash;<button type="button" onclick="loadSymptomOthers(0)" class="btn btn-default"><i class="fa fa-external-link"></i> 症状库</button>&ndash;%&gt;
    </div>--%>
        <div class="col-sm-4 col-xs-4 col-lg-4 up-dpwn-bt-02">
            <c:if test="${symptomPage.number > 0}">
                <button type="button" onclick="loadSymptoms(${symptomPage.number - 1})" class="btn btn-info">上一批
                </button>
            </c:if>
            <c:if test="${symptomPage.number + 1 < symptomPage.totalPages}">
                <button type="button" onclick="loadSymptoms(${symptomPage.number + 1})" class="btn btn-info">下一批
                </button>
            </c:if>
        </div>
        <div class="col-sm-5 col-xs-5 col-lg-5">
            <div class="input-group">
                <input id="txtSymptomTag" value="${name}" type="text" class="form-control" placeholder="搜索症状名">
            <span class="input-group-btn">
                <%--<button onclick="fn_LoadSymptomTag()" class="btn btn-primary" type="button"><i class="fa fa-times"></i>
                </button>--%>
                <button onclick="loadSymptoms()" class="btn btn-primary" type="button"><i class="fa fa-search"></i>
                </button>
            </span>
            </div>
        </div>
        <div class="col-sm-3 col-xs-3 col-lg-3 pull-right">
            <button onclick="addSymptomTag();" type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加症状
            </button>
        </div>

</div>
<script type="text/javascript">
    $('#txtSymptomTag').keydown(function (event) {
        if (event.keyCode == 13) {
            loadSymptoms();
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

    function addSymptomTag() {
        layer.prompt({title: '添加症状', maxlength: 200}, function (value, index, elem) {
            $.postJSON("/tag/addSymptomTag", {"symptomTag": value,
                "helpCode": pinyin.getCamelChars(value)}, function (res) {
                if (res && res.success) {
                    if (res.msg)layer.msg(res.msg);
                    loadSymptoms();
                }
                layer.close(index)
            })
        });
    }
</script>