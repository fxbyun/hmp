package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -589505807L;

    public static final QUser user = new QUser("user");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath email = createString("email");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mobile = createString("mobile");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final ListPath<Role, QRole> roleList = this.<Role, QRole>createList("roleList", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath salt = createString("salt");

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final StringPath tel = createString("tel");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

