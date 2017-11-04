package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppointList is a Querydsl query type for AppointList
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppointList extends EntityPathBase<AppointList> {

    private static final long serialVersionUID = -602782439L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppointList appointList = new QAppointList("appointList");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Integer> configPeopleNum = createNumber("configPeopleNum", Integer.class);

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final QDoctor doctor;

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> peopleNum = createNumber("peopleNum", Integer.class);

    public final NumberPath<Integer> remainder = createNumber("remainder", Integer.class);

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public final EnumPath<AppointList.Status> status = createEnum("status", AppointList.Status.class);

    public QAppointList(String variable) {
        this(AppointList.class, forVariable(variable), INITS);
    }

    public QAppointList(Path<? extends AppointList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppointList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppointList(PathMetadata metadata, PathInits inits) {
        this(AppointList.class, metadata, inits);
    }

    public QAppointList(Class<? extends AppointList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
    }

}

