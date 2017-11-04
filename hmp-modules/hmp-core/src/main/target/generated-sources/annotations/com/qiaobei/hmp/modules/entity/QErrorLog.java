package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QErrorLog is a Querydsl query type for ErrorLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QErrorLog extends EntityPathBase<ErrorLog> {

    private static final long serialVersionUID = 2137303106L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QErrorLog errorLog = new QErrorLog("errorLog");

    public final QIdEntity _super = new QIdEntity(this);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final QDoctor doctor;

    public final StringPath errorInfo = createString("errorInfo");

    public final StringPath errorTitle = createString("errorTitle");

    public final StringPath errorUrl = createString("errorUrl");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath type = createString("type");

    public QErrorLog(String variable) {
        this(ErrorLog.class, forVariable(variable), INITS);
    }

    public QErrorLog(Path<? extends ErrorLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QErrorLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QErrorLog(PathMetadata metadata, PathInits inits) {
        this(ErrorLog.class, metadata, inits);
    }

    public QErrorLog(Class<? extends ErrorLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
    }

}

