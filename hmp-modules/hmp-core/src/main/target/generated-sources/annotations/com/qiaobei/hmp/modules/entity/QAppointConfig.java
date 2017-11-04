package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppointConfig is a Querydsl query type for AppointConfig
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppointConfig extends EntityPathBase<AppointConfig> {

    private static final long serialVersionUID = 294380797L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppointConfig appointConfig = new QAppointConfig("appointConfig");

    public final QIdEntity _super = new QIdEntity(this);

    public final QDoctor doctor;

    public final DateTimePath<java.util.Date> flagChangeDate = createDateTime("flagChangeDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<AppointConfig.Static> openStatic = createEnum("openStatic", AppointConfig.Static.class);

    public final NumberPath<Integer> perMin = createNumber("perMin", Integer.class);

    public final NumberPath<Integer> personNum = createNumber("personNum", Integer.class);

    public QAppointConfig(String variable) {
        this(AppointConfig.class, forVariable(variable), INITS);
    }

    public QAppointConfig(Path<? extends AppointConfig> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppointConfig(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppointConfig(PathMetadata metadata, PathInits inits) {
        this(AppointConfig.class, metadata, inits);
    }

    public QAppointConfig(Class<? extends AppointConfig> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
    }

}

