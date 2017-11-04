package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStatusEntity is a Querydsl query type for StatusEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QStatusEntity extends EntityPathBase<StatusEntity> {

    private static final long serialVersionUID = -1307784293L;

    public static final QStatusEntity statusEntity = new QStatusEntity("statusEntity");

    public final QIdEntity _super = new QIdEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<StatusEntity.Status> status = createEnum("status", StatusEntity.Status.class);

    public QStatusEntity(String variable) {
        super(StatusEntity.class, forVariable(variable));
    }

    public QStatusEntity(Path<? extends StatusEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatusEntity(PathMetadata metadata) {
        super(StatusEntity.class, metadata);
    }

}

