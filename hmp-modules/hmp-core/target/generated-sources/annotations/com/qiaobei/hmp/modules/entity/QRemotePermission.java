package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRemotePermission is a Querydsl query type for RemotePermission
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRemotePermission extends EntityPathBase<RemotePermission> {

    private static final long serialVersionUID = -685776677L;

    public static final QRemotePermission remotePermission = new QRemotePermission("remotePermission");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> parentId = createNumber("parentId", Integer.class);

    public final ListPath<RemoteRole, QRemoteRole> roleList = this.<RemoteRole, QRemoteRole>createList("roleList", RemoteRole.class, QRemoteRole.class, PathInits.DIRECT2);

    public final EnumPath<RemotePermission.Status> status = createEnum("status", RemotePermission.Status.class);

    public QRemotePermission(String variable) {
        super(RemotePermission.class, forVariable(variable));
    }

    public QRemotePermission(Path<? extends RemotePermission> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRemotePermission(PathMetadata metadata) {
        super(RemotePermission.class, metadata);
    }

}

