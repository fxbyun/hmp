package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvaluate is a Querydsl query type for Evaluate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEvaluate extends EntityPathBase<Evaluate> {

    private static final long serialVersionUID = 902994111L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEvaluate evaluate = new QEvaluate("evaluate");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath content = createString("content");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    public final QEmr emr;

    public final StringPath grade = createString("grade");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath patientName = createString("patientName");

    public final StringPath patientUid = createString("patientUid");

    public final BooleanPath readFlag = createBoolean("readFlag");

    public final EnumPath<Evaluate.Type> type = createEnum("type", Evaluate.Type.class);

    public QEvaluate(String variable) {
        this(Evaluate.class, forVariable(variable), INITS);
    }

    public QEvaluate(Path<? extends Evaluate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEvaluate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEvaluate(PathMetadata metadata, PathInits inits) {
        this(Evaluate.class, metadata, inits);
    }

    public QEvaluate(Class<? extends Evaluate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emr = inits.isInitialized("emr") ? new QEmr(forProperty("emr"), inits.get("emr")) : null;
    }

}

