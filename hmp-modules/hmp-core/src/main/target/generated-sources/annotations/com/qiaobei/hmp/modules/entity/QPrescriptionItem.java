package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrescriptionItem is a Querydsl query type for PrescriptionItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrescriptionItem extends EntityPathBase<PrescriptionItem> {

    private static final long serialVersionUID = -1673962381L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrescriptionItem prescriptionItem = new QPrescriptionItem("prescriptionItem");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Long> companyId = createNumber("companyId", Long.class);

    public final StringPath companyName = createString("companyName");

    public final NumberPath<Double> copies = createNumber("copies", Double.class);

    public final StringPath groupIndex = createString("groupIndex");

    public final BooleanPath hasUsage = createBoolean("hasUsage");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> medicineId = createNumber("medicineId", Long.class);

    public final StringPath medicineName = createString("medicineName");

    public final EnumPath<Medicine.Type> medicineType = createEnum("medicineType", Medicine.Type.class);

    public final StringPath multiplexTag = createString("multiplexTag");

    public final QPrescription prescription;

    public final NumberPath<Double> qty = createNumber("qty", Double.class);

    public final NumberPath<Double> rate = createNumber("rate", Double.class);

    public final StringPath specialInstructions = createString("specialInstructions");

    public final StringPath standard = createString("standard");

    public final EnumPath<Medicine.Unit> unit = createEnum("unit", Medicine.Unit.class);

    public final StringPath useMode = createString("useMode");

    public final StringPath useQty = createString("useQty");

    public final StringPath useTimes = createString("useTimes");

    public final StringPath useUnit = createString("useUnit");

    public final StringPath usingTime = createString("usingTime");

    public QPrescriptionItem(String variable) {
        this(PrescriptionItem.class, forVariable(variable), INITS);
    }

    public QPrescriptionItem(Path<? extends PrescriptionItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrescriptionItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrescriptionItem(PathMetadata metadata, PathInits inits) {
        this(PrescriptionItem.class, metadata, inits);
    }

    public QPrescriptionItem(Class<? extends PrescriptionItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.prescription = inits.isInitialized("prescription") ? new QPrescription(forProperty("prescription")) : null;
    }

}

