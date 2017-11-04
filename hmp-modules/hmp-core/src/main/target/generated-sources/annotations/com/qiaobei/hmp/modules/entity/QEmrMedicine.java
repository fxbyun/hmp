package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmrMedicine is a Querydsl query type for EmrMedicine
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmrMedicine extends EntityPathBase<EmrMedicine> {

    private static final long serialVersionUID = -902843586L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmrMedicine emrMedicine = new QEmrMedicine("emrMedicine");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final QCompany company;

    public final StringPath companyName = createString("companyName");

    public final NumberPath<Double> copies = createNumber("copies", Double.class);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    public final QEmr emr;

    public final StringPath groupIndex = createString("groupIndex");

    public final BooleanPath hasUsage = createBoolean("hasUsage");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMedicine medicine;

    public final StringPath medicineName = createString("medicineName");

    public final NumberPath<Long> medicinePrivateId = createNumber("medicinePrivateId", Long.class);

    public final EnumPath<Medicine.Type> medicineType = createEnum("medicineType", Medicine.Type.class);

    public final StringPath multiplexTag = createString("multiplexTag");

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final NumberPath<Double> realQty = createNumber("realQty", Double.class);

    public final EnumPath<Medicine.Unit> realUnit = createEnum("realUnit", Medicine.Unit.class);

    public final StringPath specialInstructions = createString("specialInstructions");

    public final StringPath standard = createString("standard");

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final EnumPath<Medicine.Unit> tjUnit = createEnum("tjUnit", Medicine.Unit.class);

    public final EnumPath<Medicine.Unit> unit = createEnum("unit", Medicine.Unit.class);

    public final NumberPath<Double> unitPrice = createNumber("unitPrice", Double.class);

    public final StringPath useMode = createString("useMode");

    public final StringPath useQty = createString("useQty");

    public final StringPath useTimes = createString("useTimes");

    public final StringPath useUnit = createString("useUnit");

    public final StringPath usingTime = createString("usingTime");

    public QEmrMedicine(String variable) {
        this(EmrMedicine.class, forVariable(variable), INITS);
    }

    public QEmrMedicine(Path<? extends EmrMedicine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmrMedicine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmrMedicine(PathMetadata metadata, PathInits inits) {
        this(EmrMedicine.class, metadata, inits);
    }

    public QEmrMedicine(Class<? extends EmrMedicine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
        this.emr = inits.isInitialized("emr") ? new QEmr(forProperty("emr"), inits.get("emr")) : null;
        this.medicine = inits.isInitialized("medicine") ? new QMedicine(forProperty("medicine"), inits.get("medicine")) : null;
    }

}

