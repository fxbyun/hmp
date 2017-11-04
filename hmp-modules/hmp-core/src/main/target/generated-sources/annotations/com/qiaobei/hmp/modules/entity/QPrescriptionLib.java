package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrescriptionLib is a Querydsl query type for PrescriptionLib
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrescriptionLib extends EntityPathBase<PrescriptionLib> {

    private static final long serialVersionUID = -53996251L;

    public static final QPrescriptionLib prescriptionLib = new QPrescriptionLib("prescriptionLib");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final StringPath categoryName = createString("categoryName");

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final StringPath diagnosis = createString("diagnosis");

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<Medicine.Type> medicineType = createEnum("medicineType", Medicine.Type.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final ListPath<PrescriptionLibItem, QPrescriptionLibItem> prescriptionLibItemList = this.<PrescriptionLibItem, QPrescriptionLibItem>createList("prescriptionLibItemList", PrescriptionLibItem.class, QPrescriptionLibItem.class, PathInits.DIRECT2);

    public final StringPath remark = createString("remark");

    public QPrescriptionLib(String variable) {
        super(PrescriptionLib.class, forVariable(variable));
    }

    public QPrescriptionLib(Path<? extends PrescriptionLib> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrescriptionLib(PathMetadata metadata) {
        super(PrescriptionLib.class, metadata);
    }

}

