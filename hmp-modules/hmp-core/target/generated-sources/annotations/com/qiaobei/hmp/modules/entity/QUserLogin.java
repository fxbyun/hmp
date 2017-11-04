package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLogin is a Querydsl query type for UserLogin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserLogin extends EntityPathBase<UserLogin> {

    private static final long serialVersionUID = 1824412408L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLogin userLogin = new QUserLogin("userLogin");

    public final QIdEntity _super = new QIdEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath loginIP = createString("loginIP");

    public final DateTimePath<java.util.Date> loginTime = createDateTime("loginTime", java.util.Date.class);

    public final QDoctor user;

    public QUserLogin(String variable) {
        this(UserLogin.class, forVariable(variable), INITS);
    }

    public QUserLogin(Path<? extends UserLogin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLogin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLogin(PathMetadata metadata, PathInits inits) {
        this(UserLogin.class, metadata, inits);
    }

    public QUserLogin(Class<? extends UserLogin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QDoctor(forProperty("user"), inits.get("user")) : null;
    }

}

