package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOldPatient is a Querydsl query type for OldPatient
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOldPatient extends EntityPathBase<OldPatient> {

    private static final long serialVersionUID = 421291396L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOldPatient oldPatient = new QOldPatient("oldPatient");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath address = createString("address");

    public final DatePath<java.util.Date> birthday = createDate("birthday", java.util.Date.class);

    public final StringPath cfid = createString("cfid");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDoctor doctor;

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final ListPath<OldPatientBingli, QOldPatientBingli> oldPatientBinglis = this.<OldPatientBingli, QOldPatientBingli>createList("oldPatientBinglis", OldPatientBingli.class, QOldPatientBingli.class, PathInits.DIRECT2);

    public final QPatient patient;

    public final EnumPath<OldPatient.Status> status = createEnum("status", OldPatient.Status.class);

    public final StringPath zhenduan = createString("zhenduan");

    public QOldPatient(String variable) {
        this(OldPatient.class, forVariable(variable), INITS);
    }

    public QOldPatient(Path<? extends OldPatient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOldPatient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOldPatient(PathMetadata metadata, PathInits inits) {
        this(OldPatient.class, metadata, inits);
    }

    public QOldPatient(Class<? extends OldPatient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
        this.patient = inits.isInitialized("patient") ? new QPatient(forProperty("patient")) : null;
    }

}

