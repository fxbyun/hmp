package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiagnosis is a Querydsl query type for Diagnosis
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiagnosis extends EntityPathBase<Diagnosis> {

    private static final long serialVersionUID = -1595391093L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiagnosis diagnosis = new QDiagnosis("diagnosis");

    public final QIdEntity _super = new QIdEntity(this);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final NumberPath<Long> departmentId = createNumber("departmentId", Long.class);

    public final StringPath departmentName = createString("departmentName");

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    public final QEmr emr;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final StringPath patientName = createString("patientName");

    public final StringPath patientUid = createString("patientUid");

    public QDiagnosis(String variable) {
        this(Diagnosis.class, forVariable(variable), INITS);
    }

    public QDiagnosis(Path<? extends Diagnosis> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiagnosis(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiagnosis(PathMetadata metadata, PathInits inits) {
        this(Diagnosis.class, metadata, inits);
    }

    public QDiagnosis(Class<? extends Diagnosis> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emr = inits.isInitialized("emr") ? new QEmr(forProperty("emr"), inits.get("emr")) : null;
    }

}

