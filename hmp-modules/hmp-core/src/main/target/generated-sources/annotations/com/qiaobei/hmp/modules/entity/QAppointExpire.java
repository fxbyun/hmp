package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppointExpire is a Querydsl query type for AppointExpire
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppointExpire extends EntityPathBase<AppointExpire> {

    private static final long serialVersionUID = 360013530L;

    public static final QAppointExpire appointExpire = new QAppointExpire("appointExpire");

    public final QIdEntity _super = new QIdEntity(this);

    public final DateTimePath<java.util.Date> appointDate = createDateTime("appointDate", java.util.Date.class);

    public final EnumPath<AppointPatient.Status> appointStatus = createEnum("appointStatus", AppointPatient.Status.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    public final EnumPath<AppointPatient.SendMessage> hasSendMessage = createEnum("hasSendMessage", AppointPatient.SendMessage.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath message = createString("message");

    public final StringPath mobileNum = createString("mobileNum");

    public final NumberPath<Long> mobileUserId = createNumber("mobileUserId", Long.class);

    public final NumberPath<Double> money = createNumber("money", Double.class);

    public final StringPath orderId = createString("orderId");

    public final NumberPath<Long> patientId = createNumber("patientId", Long.class);

    public final StringPath patientName = createString("patientName");

    public final DateTimePath<java.util.Date> payDate = createDateTime("payDate", java.util.Date.class);

    public final EnumPath<AppointReward.RewardStatus> rewardStatus = createEnum("rewardStatus", AppointReward.RewardStatus.class);

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public QAppointExpire(String variable) {
        super(AppointExpire.class, forVariable(variable));
    }

    public QAppointExpire(Path<? extends AppointExpire> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppointExpire(PathMetadata metadata) {
        super(AppointExpire.class, metadata);
    }

}

