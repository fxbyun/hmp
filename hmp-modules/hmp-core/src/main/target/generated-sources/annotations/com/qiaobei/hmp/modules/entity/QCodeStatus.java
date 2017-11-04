package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCodeStatus is a Querydsl query type for CodeStatus
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QCodeStatus extends EntityPathBase<CodeStatus> {

    private static final long serialVersionUID = -1586233627L;

    public static final QCodeStatus codeStatus = new QCodeStatus("codeStatus");

    public final QIdEntity _super = new QIdEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<CodeStatus.StatusStr> status = createEnum("status", CodeStatus.StatusStr.class);

    public QCodeStatus(String variable) {
        super(CodeStatus.class, forVariable(variable));
    }

    public QCodeStatus(Path<? extends CodeStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodeStatus(PathMetadata metadata) {
        super(CodeStatus.class, metadata);
    }

}

