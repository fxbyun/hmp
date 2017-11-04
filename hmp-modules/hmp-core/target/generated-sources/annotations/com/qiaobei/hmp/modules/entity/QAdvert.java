package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdvert is a Querydsl query type for Advert
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdvert extends EntityPathBase<Advert> {

    private static final long serialVersionUID = -165335686L;

    public static final QAdvert advert = new QAdvert("advert");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final StringPath content = createString("content");

    public final StringPath createBy = createString("createBy");

    public final NumberPath<Long> createById = createNumber("createById", Long.class);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> indate = createNumber("indate", Integer.class);

    public final StringPath name = createString("name");

    public final EnumPath<Advert.Position> position = createEnum("position", Advert.Position.class);

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final EnumPath<Advert.Type> type = createEnum("type", Advert.Type.class);

    public final StringPath url = createString("url");

    public QAdvert(String variable) {
        super(Advert.class, forVariable(variable));
    }

    public QAdvert(Path<? extends Advert> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdvert(PathMetadata metadata) {
        super(Advert.class, metadata);
    }

}

