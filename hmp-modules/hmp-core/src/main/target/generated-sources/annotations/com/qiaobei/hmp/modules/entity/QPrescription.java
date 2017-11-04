package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrescription is a Querydsl query type for Prescription
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrescription extends EntityPathBase<Prescription> {

    private static final long serialVersionUID = 1759448384L;

    public static final QPrescription prescription = new QPrescription("prescription");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final StringPath categoryName = createString("categoryName");

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final StringPath diagnosis = createString("diagnosis");

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<Medicine.Type> medicineType = createEnum("medicineType", Medicine.Type.class);

    public final StringPath name = createString("name");

    public final ListPath<PrescriptionItem, QPrescriptionItem> prescriptionItemList = this.<PrescriptionItem, QPrescriptionItem>createList("prescriptionItemList", PrescriptionItem.class, QPrescriptionItem.class, PathInits.DIRECT2);

    public final StringPath remark = createString("remark");

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public QPrescription(String variable) {
        super(Prescription.class, forVariable(variable));
    }

    public QPrescription(Path<? extends Prescription> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrescription(PathMetadata metadata) {
        super(Prescription.class, metadata);
    }

}

