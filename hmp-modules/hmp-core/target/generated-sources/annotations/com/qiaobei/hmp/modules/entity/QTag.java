package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTag is a Querydsl query type for Tag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTag extends EntityPathBase<Tag> {

    private static final long serialVersionUID = 1089360820L;

    public static final QTag tag = new QTag("tag");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Long> departmentId = createNumber("departmentId", Long.class);

    public final StringPath departmentName = createString("departmentName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final EnumPath<Tag.TagType> type = createEnum("type", Tag.TagType.class);

    public QTag(String variable) {
        super(Tag.class, forVariable(variable));
    }

    public QTag(Path<? extends Tag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTag(PathMetadata metadata) {
        super(Tag.class, metadata);
    }

}

