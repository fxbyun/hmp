package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVitalSign is a Querydsl query type for VitalSign
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVitalSign extends EntityPathBase<VitalSign> {

    private static final long serialVersionUID = -261598077L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVitalSign vitalSign = new QVitalSign("vitalSign");

    public final QIdEntity _super = new QIdEntity(this);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final QEmr emr;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath patientName = createString("patientName");

    public final StringPath patientUid = createString("patientUid");

    public final EnumPath<VitalSign.Type> type = createEnum("type", VitalSign.Type.class);

    public final StringPath value = createString("value");

    public QVitalSign(String variable) {
        this(VitalSign.class, forVariable(variable), INITS);
    }

    public QVitalSign(Path<? extends VitalSign> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVitalSign(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVitalSign(PathMetadata metadata, PathInits inits) {
        this(VitalSign.class, metadata, inits);
    }

    public QVitalSign(Class<? extends VitalSign> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emr = inits.isInitialized("emr") ? new QEmr(forProperty("emr"), inits.get("emr")) : null;
    }

}

