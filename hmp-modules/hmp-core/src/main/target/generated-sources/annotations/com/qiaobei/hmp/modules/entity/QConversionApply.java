package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConversionApply is a Querydsl query type for ConversionApply
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QConversionApply extends EntityPathBase<ConversionApply> {

    private static final long serialVersionUID = 1535789330L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConversionApply conversionApply = new QConversionApply("conversionApply");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final StringPath applyBy = createString("applyBy");

    public final NumberPath<Long> applyById = createNumber("applyById", Long.class);

    public final DateTimePath<java.util.Date> applyOn = createDateTime("applyOn", java.util.Date.class);

    public final QConversion conversion;

    public final EnumPath<Medicine.Unit> fromUnit = createEnum("fromUnit", Medicine.Unit.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMedicine medicine;

    public final StringPath medicineName = createString("medicineName");

    public final NumberPath<Integer> rate = createNumber("rate", Integer.class);

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final EnumPath<Medicine.Unit> toUnit = createEnum("toUnit", Medicine.Unit.class);

    public final StringPath verifyBy = createString("verifyBy");

    public final NumberPath<Long> verifyById = createNumber("verifyById", Long.class);

    public final DateTimePath<java.util.Date> verifyOn = createDateTime("verifyOn", java.util.Date.class);

    public QConversionApply(String variable) {
        this(ConversionApply.class, forVariable(variable), INITS);
    }

    public QConversionApply(Path<? extends ConversionApply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConversionApply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConversionApply(PathMetadata metadata, PathInits inits) {
        this(ConversionApply.class, metadata, inits);
    }

    public QConversionApply(Class<? extends ConversionApply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.conversion = inits.isInitialized("conversion") ? new QConversion(forProperty("conversion"), inits.get("conversion")) : null;
        this.medicine = inits.isInitialized("medicine") ? new QMedicine(forProperty("medicine"), inits.get("medicine")) : null;
    }

}

