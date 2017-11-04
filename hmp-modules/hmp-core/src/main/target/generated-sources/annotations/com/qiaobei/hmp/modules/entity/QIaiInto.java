package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIaiInto is a Querydsl query type for IaiInto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIaiInto extends EntityPathBase<IaiInto> {

    private static final long serialVersionUID = 1875892075L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIaiInto iaiInto = new QIaiInto("iaiInto");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Long> createBy = createNumber("createBy", Long.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final StringPath goodsNo = createString("goodsNo");

    public final EnumPath<IaiInto.IaiIntoType> iaiIntoType = createEnum("iaiIntoType", IaiInto.IaiIntoType.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<IaiIntoDetail, QIaiIntoDetail> intoDetailList = this.<IaiIntoDetail, QIaiIntoDetail>createList("intoDetailList", IaiIntoDetail.class, QIaiIntoDetail.class, PathInits.DIRECT2);

    public final EnumPath<IaiInto.IaiIntoStatus> status = createEnum("status", IaiInto.IaiIntoStatus.class);

    public final QSupplier supplier;

    public final NumberPath<Double> totalMoney = createNumber("totalMoney", Double.class);

    public final StringPath uuid = createString("uuid");

    public final StringPath whoCreate = createString("whoCreate");

    public QIaiInto(String variable) {
        this(IaiInto.class, forVariable(variable), INITS);
    }

    public QIaiInto(Path<? extends IaiInto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIaiInto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIaiInto(PathMetadata metadata, PathInits inits) {
        this(IaiInto.class, metadata, inits);
    }

    public QIaiInto(Class<? extends IaiInto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.supplier = inits.isInitialized("supplier") ? new QSupplier(forProperty("supplier"), inits.get("supplier")) : null;
    }

}

