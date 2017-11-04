package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdverting is a Querydsl query type for Adverting
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdverting extends EntityPathBase<Adverting> {

    private static final long serialVersionUID = 812171304L;

    public static final QAdverting adverting = new QAdverting("adverting");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final StringPath content = createString("content");

    public final StringPath createBy = createString("createBy");

    public final NumberPath<Long> createById = createNumber("createById", Long.class);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> indate = createNumber("indate", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> orderNo = createNumber("orderNo", Long.class);

    public final EnumPath<Adverting.Position> position = createEnum("position", Adverting.Position.class);

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final EnumPath<Adverting.Type> type = createEnum("type", Adverting.Type.class);

    public final StringPath url = createString("url");

    public QAdverting(String variable) {
        super(Adverting.class, forVariable(variable));
    }

    public QAdverting(Path<? extends Adverting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdverting(PathMetadata metadata) {
        super(Adverting.class, metadata);
    }

}

