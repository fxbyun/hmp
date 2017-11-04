package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSupplier is a Querydsl query type for Supplier
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSupplier extends EntityPathBase<Supplier> {

    private static final long serialVersionUID = -922098190L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSupplier supplier = new QSupplier("supplier");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath address = createString("address");

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mobile = createString("mobile");

    public final StringPath name = createString("name");

    public QSupplier(String variable) {
        this(Supplier.class, forVariable(variable), INITS);
    }

    public QSupplier(Path<? extends Supplier> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSupplier(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSupplier(PathMetadata metadata, PathInits inits) {
        this(Supplier.class, metadata, inits);
    }

    public QSupplier(Class<? extends Supplier> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor"), inits.get("doctor")) : null;
    }

}

