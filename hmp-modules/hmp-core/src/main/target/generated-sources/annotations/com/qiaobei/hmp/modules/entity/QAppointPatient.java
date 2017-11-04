package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppointPatient is a Querydsl query type for AppointPatient
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppointPatient extends EntityPathBase<AppointPatient> {

    private static final long serialVersionUID = -1206665046L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppointPatient appointPatient = new QAppointPatient("appointPatient");

    public final QIdEntity _super = new QIdEntity(this);

    public final QAppointList appointList;

    public final EnumPath<AppointPatient.Status> appointStatus = createEnum("appointStatus", AppointPatient.Status.class);

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final EnumPath<AppointPatient.SendMessage> hasSendMessage = createEnum("hasSendMessage", AppointPatient.SendMessage.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QPatient patient;

    public QAppointPatient(String variable) {
        this(AppointPatient.class, forVariable(variable), INITS);
    }

    public QAppointPatient(Path<? extends AppointPatient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppointPatient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppointPatient(PathMetadata metadata, PathInits inits) {
        this(AppointPatient.class, metadata, inits);
    }

    public QAppointPatient(Class<? extends AppointPatient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.appointList = inits.isInitialized("appointList") ? new QAppointList(forProperty("appointList"), inits.get("appointList")) : null;
        this.patient = inits.isInitialized("patient") ? new QPatient(forProperty("patient")) : null;
    }

}

