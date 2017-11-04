<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>易佳诊健康管理平台</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/assets/styles/style.css">
    <link rel="stylesheet" href="${ctx}/assets/star/css/star-rating.min.css" type="text/css" />
    <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        var useragent = navigator.userAgent;
//        if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
//            // 这里警告框会阻塞当前页面继续加载
//            alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
//            // 以下代码是用javascript强行关闭当前页面
//            var opened = window.open('about:blank', '_self');
//            opened.opener = null;
//            opened.close();
//        }
        $(function(){
            $("#save").click(function(){
                if($("#grade").length > 0 && $("#grade").val() == 0){
                    layer.alert("请给医生评星！");
                    return false;
                }
                if($("#content").val() == ""){
                    layer.alert("请输入内容！");
                    return false;
                }
                $("#evalForm").submit();
            });
        })
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="text-left tcb"><h4>就诊详情</h4></div>
            <div class="well well-sm emr">
                <h4>门诊：${emr.doctor.outpatientService}</h4>
                <h4>医生：${emr.doctor.name}</h4>
                <h4>患者：${emr.patientName}</h4>
                <h4>年龄：${emr.patient.age}</h4>
                <h4>电话：${emr.patient.mobile}</h4>
                <h4>主诉：</h4>
                <p>${emr.mainSuit}</p>
                <h4>生命体征：</h4>
                <p>${emr.vitalSignText}</p>
                <h4>诊断结果：</h4>
                <p>${emr.diagnosisResult}</p>
                <h4>医嘱：</h4>
                <c:set var="doctorAdviceList" value="${emr.doctorAdviceList}"/>
                <c:if test="${empty doctorAdviceList}"><p>无</p></c:if>
                <c:if test="${not empty doctorAdviceList}">
                    <c:forEach var="entry" items="${doctorAdviceList}">
                        <p class="title">${entry.key}</p>
                        <%--<ol>--%>
                            <%--<c:forEach var="ad" items="${entry.value}">--%>
                                <%--<li class="advice">${ad}</li>--%>
                            <%--</c:forEach>--%>
                        <%--</ol>--%>
                        <c:forEach varStatus="status" var="ad" items="${entry.value}">
                            <p class="advice">${status.count}、${ad}</p>
                            <%--每次${em.medicine.useQty}${medicineUnits[em.medicine.useUnit]}&nbsp;&nbsp;--%>
                        </c:forEach>
                    </c:forEach>
                </c:if>
                <h4>诊后建议：</h4>
                <c:set var="emrSuggestList" value="${emr.emrSuggestList}"/>
                <c:if test="${empty emrSuggestList}"><p>无</p></c:if>
                <c:if test="${not empty emrSuggestList}">
                    <c:forEach varStatus="status" var="s" items="${emrSuggestList}">
                        <p>${status.count}、${s.suggestContent}</p>
                    </c:forEach>
                </c:if>
            </div>
            <div class="text-left tcb"><h4>评价咨询</h4></div>
            <div class="well well-sm">
                <c:forEach items="${evaluateList}" var="eva">
                    <c:choose>
                        <c:when test="${eva.type eq 'ELE'}">
                            <p>星级：${eva.grade}星</p>
                            <p>评价咨询：<c:out value='${eva.content}'/></p>
                        </c:when>
                        <c:when test="${eva.type eq 'DTO'}">
                            <p><span class="tcb">${eva.doctorName}</span>回复<span class="tcb">我</span>：<c:out value='${eva.content}'/></p>
                        </c:when>
                        <c:when test="${eva.type eq 'OTD'}">
                            <p><span class="tcb">我</span>回复<span class="tcb">${eva.doctorName}</span>：<c:out value='${eva.content}'/></p>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <form modelAttribute="eval" class="form-inline" action="${ctx}/eval" id="evalForm" method="post">
                    <input type="hidden" name="emr.id" value="${emr.id}"/>
                    <input type="hidden" name="patientUid" value="${emr.patientUid}"/>
                    <input type="hidden" name="patientName" value="${emr.patientName}"/>
                    <input type="hidden" name="doctorId" value="${emr.doctor.id}"/>
                    <input type="hidden" name="doctorName" value="${emr.doctorName}"/>
                    <c:choose>
                        <c:when test="${empty evaluateList}">
                            <input type="hidden" name="type" value="ELE"/>
                            <input id="grade" name="grade" value="0" type="number" class="rating" min=0 max=5 step=1 data-size="xs" >
                            <input type="text" class="form-control" name="content" id="content" style="width: 100%" placeholder="评价"/>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="type" value="OTD"/>
                            <input type="text" class="form-control" name="content" id="content" style="width: 100%" placeholder="咨询/回复"/>
                        </c:otherwise>
                    </c:choose>
                    <div class="text-center">
                        <button type="button" id="save" class="btn btn-primary" style="margin-top: 10px">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/star/js/star-rating.min.js" type="text/javascript"></script>
<script src="${ctx}/assets/layer/layer.js" type="text/javascript"></script>
</body>
</html>
