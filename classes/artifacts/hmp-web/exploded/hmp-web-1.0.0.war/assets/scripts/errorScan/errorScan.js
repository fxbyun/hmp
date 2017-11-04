/**
 * Created by Administrator on 2016/9/23 0023.
 */


$(function () {
    setInterval(function () {
        utlis_reloadDivSymtomTags();
        utlis_reloadDivDiagnosisTags();
        utlis_reloadDivWesternMedicines();
        utlis_reloadDivChineseMedicines();
        utlis_reloadTherapyTags();
    }, 6000);
});

function utlis_reloadTherapyTags() {
    if ($("#divTherapy").text()) {
        if ($("#divTherapy").text().indexOf("系统发生内部错误") > 0) {
            $("#divTherapy").load("/fragment/therapy/therapyList");
        }
    }
    if ($("#divTherapy").text() == "") {
        $("#divTherapy").load("/fragment/therapy/therapyList");
    }
}

function utlis_reloadDivChineseMedicines() {
    if ($("#divChineseMedicines").text()) {
        if ($("#divChineseMedicines").text().indexOf("系统发生内部错误") > 0) {
            $("#divChineseMedicines").load("/fragment/medicines/Chinese?page=0&name=&diagnosisName=");
        }
    }
    if ($("#divChineseMedicines").text() == "") {
        $("#divChineseMedicines").load("/fragment/medicines/Chinese?page=0&name=&diagnosisName=");
    }
}
function utlis_reloadDivWesternMedicines() {
    if ($("#divWesternMedicines").text()) {
        if ($("#divWesternMedicines").text().indexOf("系统发生内部错误") > 0) {
            $("#divWesternMedicines").load("/fragment/medicines/Western?page=0&name=&diagonsisName=");
        }
    }

    if ($("#divWesternMedicines").text() == "") {
        $("#divWesternMedicines").load("/fragment/medicines/Western?page=0&name=&diagonsisName=");
    }

}


function utlis_reloadDivDiagnosisTags() {
    if ($("#divDiagnosisTags").text()) {
        if ($("#divDiagnosisTags").text().indexOf("系统发生内部错误") > 0) {
            $("#divDiagnosisTags").load('/fragment/diagnosisTags?page=0&departmentId=1&name=');
        }
    }
    if ($("#divDiagnosisTags").text() == "") {
        $("#divDiagnosisTags").load('/fragment/diagnosisTags?page=0&departmentId=1&name=');
    }
}

function utlis_reloadDivSymtomTags() {
    if ($("#divSymptomTags").text()) {
        if ($("#divSymptomTags").text().indexOf("系统发生内部错误") > 0) {
            $("#divSymptomTags").load('/fragment/symptomTags?page=0&name=');
        }
    }

    if ($("#divSymptomTags").text() == "") {
        $("#divSymptomTags").load('/fragment/symptomTags?page=0&name=');
    }
}