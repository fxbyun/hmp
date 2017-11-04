package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRegistration is a Querydsl query type for Registration
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRegistration extends EntityPathBase<Registration> {

    private static final long serialVersionUID = -51162657L;

    public static final QRegistration registration = new QRegistration("registration");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final NumberPath<Long> appointPatientId = createNumber("appointPatientId", Long.class);

    public final EnumPath<Registration.CallStatus> callStatus = createEnum("callStatus", Registration.CallStatus.class);

    public final DateTimePath<java.util.Date> completeOn = createDateTime("completeOn", java.util.Date.class);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final StringPath doctorDeptName = createString("doctorDeptName");

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    public final EnumPath<Registration.RegistrationTypeEnum> haveBindingCard = createEnum("haveBindingCard", Registration.RegistrationTypeEnum.class);

    public final EnumPath<Registration.RegistrationTypeEnum> haveSingIn = createEnum("haveSingIn", Registration.RegistrationTypeEnum.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath noNumber = createString("noNumber");

    public final NumberPath<Long> patientId = createNumber("patientId", Long.class);

    public final StringPath patientName = createString("patientName");

    public final StringPath patientUid = createString("patientUid");

    public final EnumPath<Registration.QueueStatus> queueStatus = createEnum("queueStatus", Registration.QueueStatus.class);

    public final EnumPath<Registration.RegistrationTypeEnum> registrationType = createEnum("registrationType", Registration.RegistrationTypeEnum.class);

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public QRegistration(String variable) {
        super(Registration.class, forVariable(variable));
    }

    public QRegistration(Path<? extends Registration> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegistration(PathMetadata metadata) {
        super(Registration.class, metadata);
    }

}

