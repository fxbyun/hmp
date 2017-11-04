<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/10 0010
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="application/javascript">
    var oneMedInfo = [{}
        <c:set var="westernMedicinesByUseMode" value="${emr.westernMedicinesByUseMode}" />
        <c:if test="${not empty westernMedicinesByUseMode}">
        <c:forEach var="entry" items="${westernMedicinesByUseMode}">
        <c:forEach varStatus="status" var="em" items="${entry.value}">
        ,
        {
            'medicineId': "${em.medicine.id}${em.multiplexTag}",
            'medicineName': '${em.medicine.name}',
            'unit': "${em.unit}",
            'unitLabel': "${medicineUnits[em.unit]}",
            'qty': "${em.qty}",
            'qtyTmp': "${em.qty}",
            'copies': "${em.copies}",
            'useMode': "${em.useMode}",
            'hasUsage': "${em.hasUsage}",
            'useTimes': "${em.useTimes}",
            'medType': "${em.medicineType}",
            'usingTime': "${em.usingTime}",
            'useQty': "${em.useQty}",
            "useUnit": "${em.useUnit}",
            "groupIndex": "${em.groupIndex}",
            "specialInstructions": "${em.specialInstructions}",
            "standard": "${em.standard}",
            "openType": "add",
            "companyId": "${em.companyId}",
            "rate": "${em.rate}",
            "price": "${em.price}",
            "unitPrice": "${em.unitPrice}",
            "medicinePrivateId": "${em.medicinePrivateId}",
            "tjUnit": "${em.tjUnit}",
            "status": "${em.status}"
        }
        </c:forEach>
        </c:forEach>
        </c:if>

        <c:set var="chineseMedicinesByUseMode" value="${emr.chineseMedicinesByUseMode}" />
        <c:if test="${not empty chineseMedicinesByUseMode}">
        <c:forEach var="entry" items="${chineseMedicinesByUseMode}">
        <c:forEach varStatus="status" var="em" items="${entry.value}">
        ,
        {
            'medicineId': "${em.medicine.id}${em.multiplexTag}",
            'medicineName': '${em.medicine.name}',
            'unit': "${em.unit}",
            'unitLabel': "${medicineUnits[em.unit]}",
            'qty': "${em.qty}",
            'qtyTmp': "${em.qty * emr.chineseQty}",
            'copies': "${em.copies}",
            'useMode': "${em.useMode}",
            'hasUsage': "${em.hasUsage}",
            'useTimes': "${em.useTimes}",
            'medType': "${em.medicineType}",
            'usingTime': "${em.usingTime}",
            'useQty': "${em.useQty}",
            "useUnit": "${em.useUnit}",
            "groupIndex": "${em.groupIndex}",
            "specialInstructions": "${em.specialInstructions}",
            "standard": "${em.standard}",
            "openType": "add",
            "companyId": "${em.companyId}",
            "rate": "${em.rate}",
            "price": "${em.price}",
            "unitPrice": "${em.unitPrice}",
            "medicinePrivateId": "${em.medicinePrivateId}",
            "tjUnit": "${em.tjUnit}",
            "status": "${em.status}"
        }
        </c:forEach>
        </c:forEach>
        </c:if>
    ];

    var examLabObjArray = [
        <c:forEach items="${emr.emrJClassAdviceDicts}" var="oneExamLab" varStatus="stutsNow">
        <c:if test="${stutsNow.count!=1}">
        ,
        </c:if>
        {
            id: "${oneExamLab.exp1}",
            adviceType: "${oneExamLab.type}",
            adviceName: "${oneExamLab.adviceName}",
            examOrLabName: "${oneExamLab.examLabName}",
            info: "${oneExamLab.info}",
            price: "${oneExamLab.price}",
            status: "${oneExamLab.status}",
            tmpId: "${oneExamLab.tmpFileNameId}"
        }

        </c:forEach>

    ];

    var fuJiaCostArray = [
        <c:forEach items="${emr.emrExtCostList}" var="oneExamLab" varStatus="stutsNow">
        <c:if test="${stutsNow.count!=1}">
        ,
        </c:if>
        {
            id: "${oneExamLab.doctorCostId}",
            name: "${oneExamLab.className}",
            price: "${oneExamLab.price}",
            status: "${oneExamLab.status}"
        }

        </c:forEach>
    ];

    var therapyArray = [
        <c:forEach items="${therapyList}" var="the" varStatus="stutsNow">
        <c:if test="${stutsNow.count!=1}">
        ,
        </c:if>
        {
            id: "${the.medicinePrivateId}",
            name: "${the.medicineName}",
            price: "${the.price}",
            medType: "therapy",
            qty: "${the.useQty}",
            copies: "${the.copies}",
            unitPrice: "${the.unitPrice}",
            tjUnit: "${the.useUnit}",
            status: "${the.status}"
        }

        </c:forEach>


    ];




    $(function () {

        //中医理疗
        $.each(therapyArray, function (i, obj) {
            addTherapyToMap(obj);
        })

        $.each(examLabObjArray, function (i, obj) {
            addExamOrLab(obj);
        })

        $.each(fuJiaCostArray, function (i, obj) {
            addPresOrDiv2(obj);
        })


    })
</script>
