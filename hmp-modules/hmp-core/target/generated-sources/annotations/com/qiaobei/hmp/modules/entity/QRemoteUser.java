package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRemoteUser is a Querydsl query type for RemoteUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRemoteUser extends EntityPathBase<RemoteUser> {

    private static final long serialVersionUID = 376782807L;

    public static final QRemoteUser remoteUser = new QRemoteUser("remoteUser");

    public final QIdEntity _super = new QIdEntity(this);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mobile = createString("mobile");

    public final StringPath openId = createString("openId");

    public final StringPath password = createString("password");

    public final StringPath realName = createString("realName");

    public final ListPath<RemoteRole, QRemoteRole> roleList = this.<RemoteRole, QRemoteRole>createList("roleList", RemoteRole.class, QRemoteRole.class, PathInits.DIRECT2);

    public final StringPath salt = createString("salt");

    public final EnumPath<RemoteUser.Status> status = createEnum("status", RemoteUser.Status.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final StringPath userName = createString("userName");

    public QRemoteUser(String variable) {
        super(RemoteUser.class, forVariable(variable));
    }

    public QRemoteUser(Path<? extends RemoteUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRemoteUser(PathMetadata metadata) {
        super(RemoteUser.class, metadata);
    }

}

