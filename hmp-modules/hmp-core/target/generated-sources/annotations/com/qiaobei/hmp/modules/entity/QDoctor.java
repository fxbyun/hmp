package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDoctor is a Querydsl query type for Doctor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDoctor extends EntityPathBase<Doctor> {

    private static final long serialVersionUID = -69841211L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDoctor doctor = new QDoctor("doctor");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final BooleanPath allowNurseUpdatePrice = createBoolean("allowNurseUpdatePrice");

    public final BooleanPath allowSubDoctorUpdatePrice = createBoolean("allowSubDoctorUpdatePrice");

    public final EnumPath<Doctor.DoctorAppointStatus> appointStatus = createEnum("appointStatus", Doctor.DoctorAppointStatus.class);

    public final StringPath area = createString("area");

    public final NumberPath<Integer> areaNo = createNumber("areaNo", Integer.class);

    public final StringPath autoSend = createString("autoSend");

    public final StringPath autoSendActivateMsg = createString("autoSendActivateMsg");

    public final NumberPath<Long> autoSendDay = createNumber("autoSendDay", Long.class);

    public final StringPath businessAddr = createString("businessAddr");

    public final StringPath businessLicense = createString("businessLicense");

    public final StringPath card = createString("card");

    public final StringPath certificate = createString("certificate");

    public final StringPath city = createString("city");

    public final NumberPath<Integer> cityNo = createNumber("cityNo", Integer.class);

    public final StringPath clinicInfo = createString("clinicInfo");

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final StringPath deptName = createString("deptName");

    public final EnumPath<Doctor.Doctor_Type> doctorType = createEnum("doctorType", Doctor.Doctor_Type.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> evaluationCount = createNumber("evaluationCount", Integer.class);

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> integral = createNumber("integral", Integer.class);

    public final NumberPath<Integer> integration = createNumber("integration", Integer.class);

    public final StringPath intro = createString("intro");

    public final StringPath legal = createString("legal");

    public final StringPath legalCard = createString("legalCard");

    public final StringPath mobile = createString("mobile");

    public final QMsgMoney msgMoney;

    public final StringPath name = createString("name");

    public final StringPath needAlonePrinTypeStrings = createString("needAlonePrinTypeStrings");

    public final StringPath outpatientService = createString("outpatientService");

    public final StringPath password = createString("password");

    public final NumberPath<Long> primaryDoctorId = createNumber("primaryDoctorId", Long.class);

    public final StringPath printInfo = createString("printInfo");

    public final StringPath printModel = createString("printModel");

    public final StringPath printType = createString("printType");

    public final StringPath province = createString("province");

    public final NumberPath<Integer> provinceNo = createNumber("provinceNo", Integer.class);

    public final StringPath recommender = createString("recommender");

    public final StringPath recommendMobile = createString("recommendMobile");

    public final StringPath salt = createString("salt");

    public final NumberPath<Integer> seniority = createNumber("seniority", Integer.class);

    public final StringPath specialty = createString("specialty");

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final StringPath tel = createString("tel");

    public final StringPath verifyBy = createString("verifyBy");

    public final NumberPath<Long> verifyById = createNumber("verifyById", Long.class);

    public final DateTimePath<java.util.Date> verifyOn = createDateTime("verifyOn", java.util.Date.class);

    public final StringPath wealth = createString("wealth");

    public final StringPath wxId = createString("wxId");

    public QDoctor(String variable) {
        this(Doctor.class, forVariable(variable), INITS);
    }

    public QDoctor(Path<? extends Doctor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDoctor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDoctor(PathMetadata metadata, PathInits inits) {
        this(Doctor.class, metadata, inits);
    }

    public QDoctor(Class<? extends Doctor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.msgMoney = inits.isInitialized("msgMoney") ? new QMsgMoney(forProperty("msgMoney"), inits.get("msgMoney")) : null;
    }

}

