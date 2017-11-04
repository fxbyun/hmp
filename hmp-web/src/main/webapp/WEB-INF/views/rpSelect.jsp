<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/default.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <hmp:HmpLoadFile ctx="${ctx}" url="/assets/styles/green.css" type="css" needChengStyle="true"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${ctx}/assets/html5shiv/html5shiv.js"></script>
    <script type="text/javascript" src="${ctx}/assets/respond/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/assets/laypage/laypage.js"></script>
    <script type="text/javascript">
        function fn_LoadRpList(page) {
            var categoryId = $('input[name="categoryId"]:checked').val();
            $('#divRps').load("/fragment/rp?page={0}&medicineType=${medicineType}&categoryId={1}".format(page, categoryId));
        }
        $(function () {
            $('input[name="categoryId"]').change(function () {
                fn_LoadRpList(0);
            });
            var index = parent.layer.getFrameIndex(window.name);
            $('#btnSubmitRp').click(function () {
                var rpId = $('input[name="rpId"]:checked').val();
                if (rpId) {
                    $.getJSON("/rp/{0}/items".format(rpId), function (res) {
                        $.each(res, function (i, n) {

                            n.openType="add";
                            if(n.medicineType==undefined){
                                n.medicineType=${medicineType}
                            }
                            n.medType = n.medicineType;

                            if(n.groupIndex==null|| n.groupIndex==undefined|| n.groupIndex==""){
                                n.groupIndex="10";
                            }

                            n.tagNum=0;

                            parent.fn_SelectMedicine(n, n.medicineType);
                        });

                        parent.layer.close(index);
                    })
                } else {
                    layer.alert("未选择药单");
                }
            });
            $('#btnCloseRp').click(function () {
                parent.layer.close(index);
            });
            <%--$('#tabsCategory a[data-toggle="tab"]').on('shown.bs.tab', function (e) {--%>
            <%--$(e.relatedTarget).children(':radio').prop("checked", false);--%>
            <%--$(e.target).children(':radio').prop("checked", true);--%>
            <%--var categoryId = $("#tabsCategory").find('input:radio:checked').val();--%>
            <%--fn_LoadRpList(0, '${medicineType}', categoryId);--%>
            <%--return false;--%>
            <%--});--%>
        });
    </script>
    <style>
        .btn.btn-success{
            background-color: #218a3f;
            border-color: #218a3f;
        }
    </style>
</head>
<body>
<div style="margin: 20px;">
    <div class="category-box">
        <c:forEach varStatus="status" var="cate" items="${categories}">
            <label class="btn btn-success">
                <input type="radio" name="categoryId" value="${cate.id}" <c:if test="${status.first}">checked="checked"</c:if>/> ${cate.name}
            </label>
            <%--<li <c:if test="${status.first}">class="active"</c:if>><a href="#" data-toggle="tab">--%>
            <%--<input id="categoryId${status.count}" class="hidden" name="categoryId" type="radio" value="${cate.id}"--%>
            <%--<c:if test="${status.first}">checked="checked"</c:if>/>${cate.name}--%>
            <%--</a></li>--%>
        </c:forEach>
        <div id="divRps">
            <c:import url="fragment/rpPage.jsp"/>
        </div>
    </div>
    <div class="text-center">
        <button id="btnSubmitRp" type="button" class="btn btn-success"><i class="fa fa-check"></i> 确定</button>
        <button id="btnCloseRp" type="button" class="btn btn-default"><i class="fa fa-times"></i> 取消</button>
    </div>
</div>
<script type="text/javascript" src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<hmp:HmpLoadFile ctx="${ctx}" url="/assets/scripts/hmp.js" type="js"/>
</body>
</html>