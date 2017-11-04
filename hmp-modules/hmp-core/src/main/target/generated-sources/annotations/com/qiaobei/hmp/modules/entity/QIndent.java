package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIndent is a Querydsl query type for Indent
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIndent extends EntityPathBase<Indent> {

    private static final long serialVersionUID = 72396370L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIndent indent = new QIndent("indent");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath company = createString("company");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMedicine medicine;

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final StringPath quantity = createString("quantity");

    public final QDoctor user;

    public QIndent(String variable) {
        this(Indent.class, forVariable(variable), INITS);
    }

    public QIndent(Path<? extends Indent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIndent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIndent(PathMetadata metadata, PathInits inits) {
        this(Indent.class, metadata, inits);
    }

    public QIndent(Class<? extends Indent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.medicine = inits.isInitialized("medicine") ? new QMedicine(forProperty("medicine"), inits.get("medicine")) : null;
        this.user = inits.isInitialized("user") ? new QDoctor(forProperty("user")) : null;
    }

}

