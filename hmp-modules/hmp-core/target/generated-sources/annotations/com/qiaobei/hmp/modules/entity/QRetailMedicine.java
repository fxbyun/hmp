package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRetailMedicine is a Querydsl query type for RetailMedicine
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRetailMedicine extends EntityPathBase<RetailMedicine> {

    private static final long serialVersionUID = -771837949L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRetailMedicine retailMedicine = new QRetailMedicine("retailMedicine");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath barCode = createString("barCode");

    public final NumberPath<Double> copies = createNumber("copies", Double.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMedicinePrivate medicinePrivate;

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final QRetail retail;

    public final NumberPath<Double> retailPrice = createNumber("retailPrice", Double.class);

    public final StringPath standard = createString("standard");

    public final EnumPath<RetailMedicine.Status> status = createEnum("status", RetailMedicine.Status.class);

    public final NumberPath<Double> totalPrice = createNumber("totalPrice", Double.class);

    public final EnumPath<Medicine.Unit> unit = createEnum("unit", Medicine.Unit.class);

    public final DatePath<java.util.Date> validityDate = createDate("validityDate", java.util.Date.class);

    public QRetailMedicine(String variable) {
        this(RetailMedicine.class, forVariable(variable), INITS);
    }

    public QRetailMedicine(Path<? extends RetailMedicine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRetailMedicine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRetailMedicine(PathMetadata metadata, PathInits inits) {
        this(RetailMedicine.class, metadata, inits);
    }

    public QRetailMedicine(Class<? extends RetailMedicine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.medicinePrivate = inits.isInitialized("medicinePrivate") ? new QMedicinePrivate(forProperty("medicinePrivate"), inits.get("medicinePrivate")) : null;
        this.retail = inits.isInitialized("retail") ? new QRetail(forProperty("retail"), inits.get("retail")) : null;
    }

}

