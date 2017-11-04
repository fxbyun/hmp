package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPatientTag is a Querydsl query type for PatientTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPatientTag extends EntityPathBase<PatientTag> {

    private static final long serialVersionUID = 1569144059L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPatientTag patientTag = new QPatientTag("patientTag");

    public final QIdEntity _super = new QIdEntity(this);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    public final NumberPath<Long> frequency = createNumber("frequency", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final QPatient patient;

    public QPatientTag(String variable) {
        this(PatientTag.class, forVariable(variable), INITS);
    }

    public QPatientTag(Path<? extends PatientTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPatientTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPatientTag(PathMetadata metadata, PathInits inits) {
        this(PatientTag.class, metadata, inits);
    }

    public QPatientTag(Class<? extends PatientTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.patient = inits.isInitialized("patient") ? new QPatient(forProperty("patient")) : null;
    }

}

