package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDiagnosisTag is a Querydsl query type for DiagnosisTag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiagnosisTag extends EntityPathBase<DiagnosisTag> {

    private static final long serialVersionUID = -187870193L;

    public static final QDiagnosisTag diagnosisTag = new QDiagnosisTag("diagnosisTag");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath code = createString("code");

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final NumberPath<Long> departmentId = createNumber("departmentId", Long.class);

    public final StringPath departmentName = createString("departmentName");

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    public final NumberPath<Long> frequency = createNumber("frequency", Long.class);

    public final StringPath helpCode = createString("helpCode");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public QDiagnosisTag(String variable) {
        super(DiagnosisTag.class, forVariable(variable));
    }

    public QDiagnosisTag(Path<? extends DiagnosisTag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiagnosisTag(PathMetadata metadata) {
        super(DiagnosisTag.class, metadata);
    }

}

