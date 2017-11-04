package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMedicinePrivate is a Querydsl query type for MedicinePrivate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMedicinePrivate extends EntityPathBase<MedicinePrivate> {

    private static final long serialVersionUID = -1304555773L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMedicinePrivate medicinePrivate = new QMedicinePrivate("medicinePrivate");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath barCode = createString("barCode");

    public final StringPath category = createString("category");

    public final QCompany defaultCompany;

    public final StringPath defaultCompanyName = createString("defaultCompanyName");

    public final QDoctor doctor;

    public final EnumPath<MedicinePrivate.HaveManager> haveManager = createEnum("haveManager", MedicinePrivate.HaveManager.class);

    public final StringPath helpCode = createString("helpCode");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath info = createString("info");

    public final QMedicine medicine;

    public final StringPath name = createString("name");

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final StringPath realQty = createString("realQty");

    public final EnumPath<Medicine.Unit> realUnit = createEnum("realUnit", Medicine.Unit.class);

    public final StringPath sn = createString("sn");

    public final StringPath specification = createString("specification");

    public final StringPath standard = createString("standard");

    public final EnumPath<Medicine.Type> type = createEnum("type", Medicine.Type.class);

    public final EnumPath<Medicine.Unit> unit = createEnum("unit", Medicine.Unit.class);

    public final StringPath usage = createString("usage");

    public final BooleanPath usageFlag = createBoolean("usageFlag");

    public final StringPath useMode = createString("useMode");

    public final StringPath useQty = createString("useQty");

    public final StringPath useTimes = createString("useTimes");

    public final EnumPath<Medicine.Unit> useUnit = createEnum("useUnit", Medicine.Unit.class);

    public final StringPath usingTime = createString("usingTime");

    public QMedicinePrivate(String variable) {
        this(MedicinePrivate.class, forVariable(variable), INITS);
    }

    public QMedicinePrivate(Path<? extends MedicinePrivate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMedicinePrivate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMedicinePrivate(PathMetadata metadata, PathInits inits) {
        this(MedicinePrivate.class, metadata, inits);
    }

    public QMedicinePrivate(Class<? extends MedicinePrivate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.defaultCompany = inits.isInitialized("defaultCompany") ? new QCompany(forProperty("defaultCompany")) : null;
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor"), inits.get("doctor")) : null;
        this.medicine = inits.isInitialized("medicine") ? new QMedicine(forProperty("medicine"), inits.get("medicine")) : null;
    }

}

