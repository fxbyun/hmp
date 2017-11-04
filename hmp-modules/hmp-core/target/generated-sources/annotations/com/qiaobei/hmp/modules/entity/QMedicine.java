package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMedicine is a Querydsl query type for Medicine
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMedicine extends EntityPathBase<Medicine> {

    private static final long serialVersionUID = -159497632L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMedicine medicine = new QMedicine("medicine");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath category = createString("category");

    public final ListPath<Company, QCompany> companyList = this.<Company, QCompany>createList("companyList", Company.class, QCompany.class, PathInits.DIRECT2);

    public final QCompany defaultCompany;

    public final StringPath defaultCompanyName = createString("defaultCompanyName");

    public final ListPath<DoctorMedicine, QDoctorMedicine> doctorMedicineList = this.<DoctorMedicine, QDoctorMedicine>createList("doctorMedicineList", DoctorMedicine.class, QDoctorMedicine.class, PathInits.DIRECT2);

    public final StringPath helpCode = createString("helpCode");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<MedicineCount, QMedicineCount> medicineCountList = this.<MedicineCount, QMedicineCount>createList("medicineCountList", MedicineCount.class, QMedicineCount.class, PathInits.DIRECT2);

    public final ListPath<MedicinePrivate, QMedicinePrivate> medicinePrivateList = this.<MedicinePrivate, QMedicinePrivate>createList("medicinePrivateList", MedicinePrivate.class, QMedicinePrivate.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath rate = createString("rate");

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

    public QMedicine(String variable) {
        this(Medicine.class, forVariable(variable), INITS);
    }

    public QMedicine(Path<? extends Medicine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMedicine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMedicine(PathMetadata metadata, PathInits inits) {
        this(Medicine.class, metadata, inits);
    }

    public QMedicine(Class<? extends Medicine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.defaultCompany = inits.isInitialized("defaultCompany") ? new QCompany(forProperty("defaultCompany")) : null;
    }

}

