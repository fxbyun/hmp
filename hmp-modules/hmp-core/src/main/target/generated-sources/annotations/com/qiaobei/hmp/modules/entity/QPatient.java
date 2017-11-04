package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPatient is a Querydsl query type for Patient
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPatient extends EntityPathBase<Patient> {

    private static final long serialVersionUID = -490413537L;

    public static final QPatient patient = new QPatient("patient");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final StringPath address = createString("address");

    public final StringPath area = createString("area");

    public final NumberPath<Integer> areaNo = createNumber("areaNo", Integer.class);

    public final DatePath<java.util.Date> birthday = createDate("birthday", java.util.Date.class);

    public final StringPath city = createString("city");

    public final NumberPath<Integer> cityNo = createNumber("cityNo", Integer.class);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final StringPath email = createString("email");

    public final ListPath<EmrJClassAdviceDict, QEmrJClassAdviceDict> emrJClassAdviceDicts = this.<EmrJClassAdviceDict, QEmrJClassAdviceDict>createList("emrJClassAdviceDicts", EmrJClassAdviceDict.class, QEmrJClassAdviceDict.class, PathInits.DIRECT2);

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final StringPath helpCode = createString("helpCode");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mobile = createString("mobile");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final ListPath<PatientTag, QPatientTag> patientTagList = this.<PatientTag, QPatientTag>createList("patientTagList", PatientTag.class, QPatientTag.class, PathInits.DIRECT2);

    public final StringPath province = createString("province");

    public final NumberPath<Integer> provinceNo = createNumber("provinceNo", Integer.class);

    public final StringPath salt = createString("salt");

    public final StringPath sfId = createString("sfId");

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final StringPath udid = createString("udid");

    public final StringPath uid = createString("uid");

    public final StringPath wxId = createString("wxId");

    public QPatient(String variable) {
        super(Patient.class, forVariable(variable));
    }

    public QPatient(Path<? extends Patient> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPatient(PathMetadata metadata) {
        super(Patient.class, metadata);
    }

}

