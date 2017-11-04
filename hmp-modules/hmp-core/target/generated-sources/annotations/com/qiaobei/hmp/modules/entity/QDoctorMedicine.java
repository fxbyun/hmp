package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDoctorMedicine is a Querydsl query type for DoctorMedicine
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDoctorMedicine extends EntityPathBase<DoctorMedicine> {

    private static final long serialVersionUID = -2038664161L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDoctorMedicine doctorMedicine = new QDoctorMedicine("doctorMedicine");

    public final QIdEntity _super = new QIdEntity(this);

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMedicine medicine;

    public QDoctorMedicine(String variable) {
        this(DoctorMedicine.class, forVariable(variable), INITS);
    }

    public QDoctorMedicine(Path<? extends DoctorMedicine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDoctorMedicine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDoctorMedicine(PathMetadata metadata, PathInits inits) {
        this(DoctorMedicine.class, metadata, inits);
    }

    public QDoctorMedicine(Class<? extends DoctorMedicine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor"), inits.get("doctor")) : null;
        this.medicine = inits.isInitialized("medicine") ? new QMedicine(forProperty("medicine"), inits.get("medicine")) : null;
    }

}

