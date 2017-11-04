package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSymptomTag is a Querydsl query type for SymptomTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSymptomTag extends EntityPathBase<SymptomTag> {

    private static final long serialVersionUID = -711657641L;

    public static final QSymptomTag symptomTag = new QSymptomTag("symptomTag");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    public final NumberPath<Long> frequency = createNumber("frequency", Long.class);

    public final StringPath helpCode = createString("helpCode");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QSymptomTag(String variable) {
        super(SymptomTag.class, forVariable(variable));
    }

    public QSymptomTag(Path<? extends SymptomTag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSymptomTag(PathMetadata metadata) {
        super(SymptomTag.class, metadata);
    }

}

