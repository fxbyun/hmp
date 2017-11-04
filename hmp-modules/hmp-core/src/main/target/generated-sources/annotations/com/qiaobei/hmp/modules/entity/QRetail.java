package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRetail is a Querydsl query type for Retail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRetail extends EntityPathBase<Retail> {

    private static final long serialVersionUID = 322219689L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRetail retail = new QRetail("retail");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Double> allMedCost = createNumber("allMedCost", Double.class);

    public final EnumPath<Retail.Charge_Status> chargeStatus = createEnum("chargeStatus", Retail.Charge_Status.class);

    public final DateTimePath<java.util.Date> chargeTime = createDateTime("chargeTime", java.util.Date.class);

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final BooleanPath ifBinding = createBoolean("ifBinding");

    public final StringPath orderId = createString("orderId");

    public final QPatient patient;

    public final QPharmacist pharmacist;

    public final NumberPath<Double> realCost = createNumber("realCost", Double.class);

    public final ListPath<RetailMedicine, QRetailMedicine> retailMedicineList = this.<RetailMedicine, QRetailMedicine>createList("retailMedicineList", RetailMedicine.class, QRetailMedicine.class, PathInits.DIRECT2);

    public final EnumPath<Retail.Retail_Status> retailStatus = createEnum("retailStatus", Retail.Retail_Status.class);

    public QRetail(String variable) {
        this(Retail.class, forVariable(variable), INITS);
    }

    public QRetail(Path<? extends Retail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRetail(PathMetadata metadata, PathInits inits) {
        this(Retail.class, metadata, inits);
    }

    public QRetail(Class<? extends Retail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
        this.patient = inits.isInitialized("patient") ? new QPatient(forProperty("patient")) : null;
        this.pharmacist = inits.isInitialized("pharmacist") ? new QPharmacist(forProperty("pharmacist")) : null;
    }

}

