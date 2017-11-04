package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJDoctorExtCost is a Querydsl query type for JDoctorExtCost
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJDoctorExtCost extends EntityPathBase<JDoctorExtCost> {

    private static final long serialVersionUID = -857444661L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJDoctorExtCost jDoctorExtCost = new QJDoctorExtCost("jDoctorExtCost");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath className = createString("className");

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public QJDoctorExtCost(String variable) {
        this(JDoctorExtCost.class, forVariable(variable), INITS);
    }

    public QJDoctorExtCost(Path<? extends JDoctorExtCost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJDoctorExtCost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJDoctorExtCost(PathMetadata metadata, PathInits inits) {
        this(JDoctorExtCost.class, metadata, inits);
    }

    public QJDoctorExtCost(Class<? extends JDoctorExtCost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor"), inits.get("doctor")) : null;
    }

}

