package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConversion is a Querydsl query type for Conversion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConversion extends EntityPathBase<Conversion> {

    private static final long serialVersionUID = 1378664380L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConversion conversion = new QConversion("conversion");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final EnumPath<Medicine.Unit> fromUnit = createEnum("fromUnit", Medicine.Unit.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMedicine medicine;

    public final StringPath medicineName = createString("medicineName");

    public final NumberPath<Integer> rate = createNumber("rate", Integer.class);

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final EnumPath<Medicine.Unit> toUnit = createEnum("toUnit", Medicine.Unit.class);

    public QConversion(String variable) {
        this(Conversion.class, forVariable(variable), INITS);
    }

    public QConversion(Path<? extends Conversion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConversion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConversion(PathMetadata metadata, PathInits inits) {
        this(Conversion.class, metadata, inits);
    }

    public QConversion(Class<? extends Conversion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.medicine = inits.isInitialized("medicine") ? new QMedicine(forProperty("medicine"), inits.get("medicine")) : null;
    }

}

