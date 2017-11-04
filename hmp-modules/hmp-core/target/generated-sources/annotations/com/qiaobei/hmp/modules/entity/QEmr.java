package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmr is a Querydsl query type for Emr
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmr extends EntityPathBase<Emr> {

    private static final long serialVersionUID = 1089346788L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmr emr = new QEmr("emr");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final StringPath autoSend = createString("autoSend");

    public final NumberPath<Long> autoSendDay = createNumber("autoSendDay", Long.class);

    public final StringPath backDays = createString("backDays");

    public final StringPath backMedRemarks = createString("backMedRemarks");

    public final StringPath backTime = createString("backTime");

    public final NumberPath<Double> beforeBackMonryRealCost = createNumber("beforeBackMonryRealCost", Double.class);

    public final StringPath cardPwd = createString("cardPwd");

    public final NumberPath<Long> cashierId = createNumber("cashierId", Long.class);

    public final StringPath cashierName = createString("cashierName");

    public final NumberPath<Integer> chineseQty = createNumber("chineseQty", Integer.class);

    public final NumberPath<Double> cost = createNumber("cost", Double.class);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final NumberPath<Long> departmentId = createNumber("departmentId", Long.class);

    public final StringPath departmentName = createString("departmentName");

    public final ListPath<Diagnosis, QDiagnosis> diagnosisList = this.<Diagnosis, QDiagnosis>createList("diagnosisList", Diagnosis.class, QDiagnosis.class, PathInits.DIRECT2);

    public final StringPath diagnosisResult = createString("diagnosisResult");

    public final QDoctor doctor;

    public final StringPath doctorName = createString("doctorName");

    public final NumberPath<Long> drugRefundId = createNumber("drugRefundId", Long.class);

    public final StringPath drugRefundName = createString("drugRefundName");

    public final ListPath<EmrExtCost, QEmrExtCost> emrExtCostList = this.<EmrExtCost, QEmrExtCost>createList("emrExtCostList", EmrExtCost.class, QEmrExtCost.class, PathInits.DIRECT2);

    public final ListPath<EmrFile, QEmrFile> emrFileList = this.<EmrFile, QEmrFile>createList("emrFileList", EmrFile.class, QEmrFile.class, PathInits.DIRECT2);

    public final ListPath<EmrJClassAdviceDict, QEmrJClassAdviceDict> emrJClassAdviceDicts = this.<EmrJClassAdviceDict, QEmrJClassAdviceDict>createList("emrJClassAdviceDicts", EmrJClassAdviceDict.class, QEmrJClassAdviceDict.class, PathInits.DIRECT2);

    public final ListPath<EmrMedicine, QEmrMedicine> emrMedicineList = this.<EmrMedicine, QEmrMedicine>createList("emrMedicineList", EmrMedicine.class, QEmrMedicine.class, PathInits.DIRECT2);

    public final ListPath<EmrSuggest, QEmrSuggest> emrSuggestList = this.<EmrSuggest, QEmrSuggest>createList("emrSuggestList", EmrSuggest.class, QEmrSuggest.class, PathInits.DIRECT2);

    public final ListPath<Evaluate, QEvaluate> evaluateList = this.<Evaluate, QEvaluate>createList("evaluateList", Evaluate.class, QEvaluate.class, PathInits.DIRECT2);

    public final EnumPath<Emr.HAVESEND> haveSend = createEnum("haveSend", Emr.HAVESEND.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mainSuit = createString("mainSuit");

    public final StringPath no = createString("no");

    public final QPatient patient;

    public final StringPath patientName = createString("patientName");

    public final StringPath patientUid = createString("patientUid");

    public final NumberPath<Double> realCost = createNumber("realCost", Double.class);

    public final StringPath remarks = createString("remarks");

    public final BooleanPath replied = createBoolean("replied");

    public final StringPath sendMsgInfo = createString("sendMsgInfo");

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final EnumPath<Emr.TYPE> type = createEnum("type", Emr.TYPE.class);

    public final DateTimePath<java.util.Date> updateOn = createDateTime("updateOn", java.util.Date.class);

    public final ListPath<VitalSign, QVitalSign> vitalSignList = this.<VitalSign, QVitalSign>createList("vitalSignList", VitalSign.class, QVitalSign.class, PathInits.DIRECT2);

    public final NumberPath<Integer> westernQty = createNumber("westernQty", Integer.class);

    public QEmr(String variable) {
        this(Emr.class, forVariable(variable), INITS);
    }

    public QEmr(Path<? extends Emr> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmr(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmr(PathMetadata metadata, PathInits inits) {
        this(Emr.class, metadata, inits);
    }

    public QEmr(Class<? extends Emr> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor"), inits.get("doctor")) : null;
        this.patient = inits.isInitialized("patient") ? new QPatient(forProperty("patient")) : null;
    }

}

