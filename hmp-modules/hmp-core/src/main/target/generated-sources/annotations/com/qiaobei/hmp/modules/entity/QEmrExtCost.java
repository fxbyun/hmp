package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmrExtCost is a Querydsl query type for EmrExtCost
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmrExtCost extends EntityPathBase<EmrExtCost> {

    private static final long serialVersionUID = -1306744822L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmrExtCost emrExtCost = new QEmrExtCost("emrExtCost");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final StringPath className = createString("className");

    public final NumberPath<Long> doctorCostId = createNumber("doctorCostId", Long.class);

    public final QEmr emr;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Double> price = createNumber("price", Double.class);

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public QEmrExtCost(String variable) {
        this(EmrExtCost.class, forVariable(variable), INITS);
    }

    public QEmrExtCost(Path<? extends EmrExtCost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmrExtCost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmrExtCost(PathMetadata metadata, PathInits inits) {
        this(EmrExtCost.class, metadata, inits);
    }

    public QEmrExtCost(Class<? extends EmrExtCost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emr = inits.isInitialized("emr") ? new QEmr(forProperty("emr"), inits.get("emr")) : null;
    }

}

