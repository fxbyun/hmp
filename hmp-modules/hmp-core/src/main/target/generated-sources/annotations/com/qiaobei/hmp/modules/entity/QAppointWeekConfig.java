package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppointWeekConfig is a Querydsl query type for AppointWeekConfig
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppointWeekConfig extends EntityPathBase<AppointWeekConfig> {

    private static final long serialVersionUID = 1532515473L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppointWeekConfig appointWeekConfig = new QAppointWeekConfig("appointWeekConfig");

    public final QIdEntity _super = new QIdEntity(this);

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<AppointWeekConfig.OpenStatic> openStatic = createEnum("openStatic", AppointWeekConfig.OpenStatic.class);

    public final EnumPath<AppointWeekConfig.ProductStatic> productStatic = createEnum("productStatic", AppointWeekConfig.ProductStatic.class);

    public final EnumPath<AppointWeekConfig.Weekday> weekday = createEnum("weekday", AppointWeekConfig.Weekday.class);

    public QAppointWeekConfig(String variable) {
        this(AppointWeekConfig.class, forVariable(variable), INITS);
    }

    public QAppointWeekConfig(Path<? extends AppointWeekConfig> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppointWeekConfig(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppointWeekConfig(PathMetadata metadata, PathInits inits) {
        this(AppointWeekConfig.class, metadata, inits);
    }

    public QAppointWeekConfig(Class<? extends AppointWeekConfig> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
    }

}

