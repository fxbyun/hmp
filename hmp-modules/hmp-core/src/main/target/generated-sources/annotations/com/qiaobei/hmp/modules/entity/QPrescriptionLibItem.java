package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrescriptionLibItem is a Querydsl query type for PrescriptionLibItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrescriptionLibItem extends EntityPathBase<PrescriptionLibItem> {

    private static final long serialVersionUID = -2099123752L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrescriptionLibItem prescriptionLibItem = new QPrescriptionLibItem("prescriptionLibItem");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Long> companyId = createNumber("companyId", Long.class);

    public final StringPath companyName = createString("companyName");

    public final NumberPath<Double> copies = createNumber("copies", Double.class);

    public final BooleanPath hasUsage = createBoolean("hasUsage");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> medicineId = createNumber("medicineId", Long.class);

    public final StringPath medicineName = createString("medicineName");

    public final EnumPath<Medicine.Type> medicineType = createEnum("medicineType", Medicine.Type.class);

    public final QPrescriptionLib prescriptionLib;

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final EnumPath<Medicine.Unit> unit = createEnum("unit", Medicine.Unit.class);

    public final StringPath useMode = createString("useMode");

    public QPrescriptionLibItem(String variable) {
        this(PrescriptionLibItem.class, forVariable(variable), INITS);
    }

    public QPrescriptionLibItem(Path<? extends PrescriptionLibItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrescriptionLibItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrescriptionLibItem(PathMetadata metadata, PathInits inits) {
        this(PrescriptionLibItem.class, metadata, inits);
    }

    public QPrescriptionLibItem(Class<? extends PrescriptionLibItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prescriptionLib = inits.isInitialized("prescriptionLib") ? new QPrescriptionLib(forProperty("prescriptionLib")) : null;
    }

}

