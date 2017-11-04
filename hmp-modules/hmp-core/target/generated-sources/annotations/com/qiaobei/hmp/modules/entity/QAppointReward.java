package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppointReward is a Querydsl query type for AppointReward
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppointReward extends EntityPathBase<AppointReward> {

    private static final long serialVersionUID = 714846442L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppointReward appointReward = new QAppointReward("appointReward");

    public final QIdEntity _super = new QIdEntity(this);

    public final DateTimePath<java.util.Date> appointDate = createDateTime("appointDate", java.util.Date.class);

    public final QAppointPatient appointPatient;

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath message = createString("message");

    public final StringPath mobile = createString("mobile");

    public final QMobileUser mobileUser;

    public final NumberPath<Double> money = createNumber("money", Double.class);

    public final StringPath orderId = createString("orderId");

    public final NumberPath<Long> patientId = createNumber("patientId", Long.class);

    public final StringPath patientName = createString("patientName");

    public final DateTimePath<java.util.Date> payDate = createDateTime("payDate", java.util.Date.class);

    public final EnumPath<AppointReward.RewardStatus> status = createEnum("status", AppointReward.RewardStatus.class);

    public QAppointReward(String variable) {
        this(AppointReward.class, forVariable(variable), INITS);
    }

    public QAppointReward(Path<? extends AppointReward> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppointReward(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppointReward(PathMetadata metadata, PathInits inits) {
        this(AppointReward.class, metadata, inits);
    }

    public QAppointReward(Class<? extends AppointReward> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.appointPatient = inits.isInitialized("appointPatient") ? new QAppointPatient(forProperty("appointPatient"), inits.get("appointPatient")) : null;
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor"), inits.get("doctor")) : null;
        this.mobileUser = inits.isInitialized("mobileUser") ? new QMobileUser(forProperty("mobileUser")) : null;
    }

}

