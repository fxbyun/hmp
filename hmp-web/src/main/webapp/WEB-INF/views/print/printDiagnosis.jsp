<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <title>打印就诊单</title>
    <style>
        strong.title {
            font-size: 13px;
            letter-spacing:6px;
            text-align: center;
            display: block;
            border-bottom: double 3px #000000;
            padding-bottom: 10px;
        }

        .subtitle {
            text-align: center;
            letter-spacing:2px;
            display: block;
            border-top: dashed 1px #000000;
            font-size: 12px;
            padding-top: 10px;
        }

        .ad {
            padding-top: 4px;
            border-top: dashed 1px #000000;
        }

        p.title {
            font-weight: bold;
            margin: 0px;
            padding: 0px;
        }

        p.item {
            margin: 0px;
            padding: 0px;
            height: 18px;
            line-height: 18px;
        }

        p.row {
            margin: 0px;
            padding: 0px;
            text-indent: 20px;
            height: 18px;
            line-height: 18px;
        }

        p.advice {
            margin: 10px 20px;
            padding: 0px;
            border-bottom: solid 1px #000000;
            height: 60px;
            line-height: 60px;
        }
        .suggest {
            font-size: 10px;
            width: 90%;
            line-height: 16px
        }
    </style>
</head>
<body>
<div style="padding:2px 0;font-size: 12px;width:75mm;">
    <p class="text-center"><strong class="title">就诊单</strong></p>
    <p class="item"><label>门诊名称：</label>${emr.doctor.outpatientService}</p>
    <p class="item"><label>主治医生：</label>${emr.doctorName}</p>
    <p class="item"><label>就诊时间：</label><fmt:formatDate value="${emr.createOn}" pattern="yyyy/MM/dd HH:mm"/></p>

    <p><strong class="subtitle">患者信息</strong></p>

    <p class="item"><label>姓名：</label>${emr.patientName}</p>
    <p class="item"><label>性别：</label>${genderMap[emr.patient.gender]}</p>
    <p class="item"><label>年龄：</label>${emr.patient.age}</p>
    <p class="item"><label>电话：</label>${emr.patient.mobile}</p>

    <p><strong class="subtitle">诊断结果</strong></p>
    <c:if test="${empty emr.diagnosisList}"><p>无</p></c:if>
    <c:if test="${not empty emr.diagnosisList}">
        <c:forEach varStatus="status" var="d" items="${emr.diagnosisList}">
            <p class="row">${status.count}、${d.name}</p>
        </c:forEach>
    </c:if>

    <p><strong class="subtitle">服药医嘱</strong></p>
    <c:set var="doctorAdviceList" value="${emr.doctorAdviceList}"/>
    <c:if test="${empty doctorAdviceList}"><p>无</p></c:if>
    <c:if test="${not empty doctorAdviceList}">
        <c:forEach var="entry" items="${doctorAdviceList}">
            <p class="title">${entry.key}</p>
            <c:forEach varStatus="status" var="ad" items="${entry.value}">
                <p class="advice">${status.count}、${ad}</p>
                <%--每次${em.medicine.useQty}${medicineUnits[em.medicine.useUnit]}&nbsp;&nbsp;--%>
            </c:forEach>
        </c:forEach>
    </c:if>

    <p><strong class="subtitle">诊后建议</strong></p>
    <c:set var="emrSuggestList" value="${emr.emrSuggestList}"/>
    <c:if test="${empty emrSuggestList}"><p>无</p></c:if>
    <c:if test="${not empty emrSuggestList}">
        <c:forEach varStatus="status" var="s" items="${emrSuggestList}">
            <div class="suggest">${status.count}、${s.suggestContent}</div>
        </c:forEach>
    </c:if>

    <br/>
    <c:if test="${not empty emr.doctor.printInfo}"><p>${emr.doctor.printInfo}</p></c:if>
    <c:choose>
        <c:when test="${not empty ad}">
            <p class="ad">${ad.content}</p>
        </c:when>
        <c:otherwise>
            <p class="ad">易佳诊，链接健康每一刻！</p>
        </c:otherwise>
    </c:choose>
    <div>
        <img style="float: left;" width="100px" height="100px" src="${ctx}/assets/images/qrcode.jpg">
        <div style="float: left;">
            <br/>
            扫描二维码，关注“易佳诊健康管理”!
            <br/>
            将您的健康卡绑定到微信，
            <br />
            享受更全面的健康服务！
        </div>
        <p style="clear: both;"></p>
    </div>
</div>
</body>
</html>