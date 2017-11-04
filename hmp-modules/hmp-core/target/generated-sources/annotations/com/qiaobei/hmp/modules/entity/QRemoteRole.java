package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRemoteRole is a Querydsl query type for RemoteRole
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRemoteRole extends EntityPathBase<RemoteRole> {

    private static final long serialVersionUID = 376689794L;

    public static final QRemoteRole remoteRole = new QRemoteRole("remoteRole");

    public final QIdEntity _super = new QIdEntity(this);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final ListPath<RemotePermission, QRemotePermission> permissionList = this.<RemotePermission, QRemotePermission>createList("permissionList", RemotePermission.class, QRemotePermission.class, PathInits.DIRECT2);

    public final EnumPath<RemoteRole.Status> status = createEnum("status", RemoteRole.Status.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final ListPath<RemoteUser, QRemoteUser> userList = this.<RemoteUser, QRemoteUser>createList("userList", RemoteUser.class, QRemoteUser.class, PathInits.DIRECT2);

    public QRemoteRole(String variable) {
        super(RemoteRole.class, forVariable(variable));
    }

    public QRemoteRole(Path<? extends RemoteRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRemoteRole(PathMetadata metadata) {
        super(RemoteRole.class, metadata);
    }

}

