<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/commons/include.jsp" %>
    <div class="diagnose">
        <p>患者主诉</p>

        <div id="divSymptom" class="label-box">
            <c:forEach var="tag" items="${emr.mainSuitList}">
                <span class="tag"><input name="mainSuits" value="${tag}" type="hidden">${tag}</span>
            </c:forEach>
        </div>
        <div id="divSymptomTags" class="patient-label-box">
            <c:forEach var="tag" items="${symptomTags.content}">
                <span class="tag" onclick="fn_SelectSymptomTag(this)">${tag.name}</span>
            </c:forEach>
            <div class="text-center">
                <button type="button" class="btn btn-info"><i class="fa fa-chevron-left"></i> 上一批</button>
                <button type="button" class="btn btn-info"><i class="fa fa-chevron-right"></i> 下一批</button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" placeholder="添加症状">
                <button type="button" class="btn btn-success"><i class="fa fa-plus"></i> 添加症状</button>
            </div>

        </div>

        <div id="page4" style="text-align:center;"></div>
    </div>
    <div class="diagnose">
        <p>诊断结果</p>

        <div id="divDiagnosis" class="label-box">
            <c:forEach var="tag" items="${emr.diagnosisList}">
                <span class="tag"><input name="diagnosisResult" value="${tag.name}" type="hidden">${tag.name}</span>
            </c:forEach>
        </div>
        <div id="divDiagnosisTags" class="diagnosis-label-box slide-down slide-height">
            <c:forEach var="tag" items="${diagnosisTags.content}">
                <span class="tag" onclick="fn_SelectDiagnosisTag(this)">${tag.name}</span>
            </c:forEach>
            <button class="more-label btn btn-info ">更多</button>
            <button class="slide-label btn btn-info">收起</button>
            <button class="pre-label btn btn-info">上一批</button>
            <button class="next-label btn btn-info">下一批</button>
            <%--<form>--%>
            <input type="text" placeholder="添加症状">
            <a href="javascript:void(0)" class="btn btn-info add-label">添加</a>
            <%--</form>--%>
        </div>

    </div>
    <div class="diagnose">
        <p>处方---西药房</p>

        <div class="label-box">
            <div id="divWesternMedicine">
                <c:forEach var="item" items="${emr.westernItems}">
                    <span class="tag">${item.medicineName}&nbsp;${item.qty}${item.unit}
                        <input name="medicineIds" value="${item.medicine.id}" type="hidden">
                        <input name="medicineQty" value="${item.qty}" type="hidden">
                        <input name="medicineUnit" value="${item.unit}" type="hidden"></span>
                </c:forEach>
            </div>
            <div class="diagnose-number">
                <span></span>
                <input type="number" name="westernQty" class="form-control" id="westernQty" value="${emr.westernQty}" placeholder="1"/>
                <p>副</p>
            </div>
        </div>
        <div class="medicine-label-box slide-down slide-height">
            <c:forEach var="med" items="${westernMedicines.content}">
                <a href="javascript:" onclick="fn_ShowSelectMedicine('/fragment/medicine/select/${med.id}')"><span class="tag">${med.name}</span></a>
            </c:forEach>
            <button class="more-label btn btn-info">更多</button>
            <button class="slide-label btn btn-info">收起</button>
            <button class="pre-label btn btn-info">上一批</button>
            <button class="next-label btn btn-info">下一批</button>
            <%--<form>--%>
            <input type="text" placeholder="添加药品">
            <a href="javascript:void(0)" class="btn btn-info add-label">添加</a>
            <%--</form>--%>
        </div>
    </div>
    <div class="diagnose">
        <p>处方---中药房</p>

        <div class="label-box">
            <div id="divChineseMedicine">
                <c:forEach var="item" items="${emr.chineseItems}">
                    <span class="tag">${item.medicineName}&nbsp;${item.qty}${item.unit}
                        <input name="medicineIds" value="${item.medicine.id}" type="hidden">
                        <input name="medicineQty" value="${item.qty}" type="hidden">
                        <input name="medicineUnit" value="${item.unit}" type="hidden"></span>
                </c:forEach>
            </div>
            <div class="diagnose-number">
                <span></span>
                <input type="number" name="chineseQty" class="form-control" id="chineseQty" value="${emr.chineseQty}" placeholder="1"/>
                <p>副</p>
            </div>
        </div>
        <div class="medicine-label-box slide-down slide-height">
            <c:forEach var="med" items="${chineseMedicines.content}">
                <a href="javascript:" onclick="fn_ShowSelectMedicine('/fragment/medicine/select/${med.id}')"><span class="tag">${med.name}</span></a>
            </c:forEach>
            <button class="more-label btn btn-info">更多</button>
            <button class="slide-label btn btn-info">收起</button>
            <button class="pre-label btn btn-info">上一批</button>
            <button class="next-label btn btn-info">下一批</button>
            <%--<form>--%>
            <input type="text" placeholder="添加药品">
            <a href="javascript:void(0)" class="btn btn-info add-label">添加</a>
            <%--</form>--%>
        </div>
    </div>