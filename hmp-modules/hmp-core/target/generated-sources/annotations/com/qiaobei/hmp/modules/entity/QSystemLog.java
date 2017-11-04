package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSystemLog is a Querydsl query type for SystemLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSystemLog extends EntityPathBase<SystemLog> {

    private static final long serialVersionUID = -1663275217L;

    public static final QSystemLog systemLog = new QSystemLog("systemLog");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath account = createString("account");

    public final StringPath actionTime = createString("actionTime");

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath methods = createString("methods");

    public final StringPath module = createString("module");

    public final DateTimePath<java.util.Date> operTime = createDateTime("operTime", java.util.Date.class);

    public final StringPath userIP = createString("userIP");

    public QSystemLog(String variable) {
        super(SystemLog.class, forVariable(variable));
    }

    public QSystemLog(Path<? extends SystemLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSystemLog(PathMetadata metadata) {
        super(SystemLog.class, metadata);
    }

}

