package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppointTimeConfig is a Querydsl query type for AppointTimeConfig
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppointTimeConfig extends EntityPathBase<AppointTimeConfig> {

    private static final long serialVersionUID = -785268566L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppointTimeConfig appointTimeConfig = new QAppointTimeConfig("appointTimeConfig");

    public final QIdEntity _super = new QIdEntity(this);

    public final QAppointWeekConfig appointWeekConfig;

    public final TimePath<java.util.Date> endTime = createTime("endTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final TimePath<java.util.Date> startTime = createTime("startTime", java.util.Date.class);

    public QAppointTimeConfig(String variable) {
        this(AppointTimeConfig.class, forVariable(variable), INITS);
    }

    public QAppointTimeConfig(Path<? extends AppointTimeConfig> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppointTimeConfig(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppointTimeConfig(PathMetadata metadata, PathInits inits) {
        this(AppointTimeConfig.class, metadata, inits);
    }

    public QAppointTimeConfig(Class<? extends AppointTimeConfig> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.appointWeekConfig = inits.isInitialized("appointWeekConfig") ? new QAppointWeekConfig(forProperty("appointWeekConfig"), inits.get("appointWeekConfig")) : null;
    }

}

