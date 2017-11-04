package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMedicineCount is a Querydsl query type for MedicineCount
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMedicineCount extends EntityPathBase<MedicineCount> {

    private static final long serialVersionUID = -1345283441L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMedicineCount medicineCount = new QMedicineCount("medicineCount");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Integer> countSize = createNumber("countSize", Integer.class);

    public final StringPath diagosisName = createString("diagosisName");

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMedicine medicine;

    public QMedicineCount(String variable) {
        this(MedicineCount.class, forVariable(variable), INITS);
    }

    public QMedicineCount(Path<? extends MedicineCount> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMedicineCount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMedicineCount(PathMetadata metadata, PathInits inits) {
        this(MedicineCount.class, metadata, inits);
    }

    public QMedicineCount(Class<? extends MedicineCount> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
        this.medicine = inits.isInitialized("medicine") ? new QMedicine(forProperty("medicine"), inits.get("medicine")) : null;
    }

}

