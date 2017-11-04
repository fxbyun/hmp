package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrescriptionCategory is a Querydsl query type for PrescriptionCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrescriptionCategory extends EntityPathBase<PrescriptionCategory> {

    private static final long serialVersionUID = -1418006946L;

    public static final QPrescriptionCategory prescriptionCategory = new QPrescriptionCategory("prescriptionCategory");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final StringPath remark = createString("remark");

    public QPrescriptionCategory(String variable) {
        super(PrescriptionCategory.class, forVariable(variable));
    }

    public QPrescriptionCategory(Path<? extends PrescriptionCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrescriptionCategory(PathMetadata metadata) {
        super(PrescriptionCategory.class, metadata);
    }

}

