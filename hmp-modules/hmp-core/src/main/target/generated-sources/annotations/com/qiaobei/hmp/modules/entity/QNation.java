package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNation is a Querydsl query type for Nation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNation extends EntityPathBase<Nation> {

    private static final long serialVersionUID = 204016877L;

    public static final QNation nation = new QNation("nation");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath areaCode = createString("areaCode");

    public final NumberPath<Integer> areaLevel = createNumber("areaLevel", Integer.class);

    public final StringPath areaName = createString("areaName");

    public final NumberPath<Integer> areaNo = createNumber("areaNo", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> parentNo = createNumber("parentNo", Integer.class);

    public final StringPath typeName = createString("typeName");

    public QNation(String variable) {
        super(Nation.class, forVariable(variable));
    }

    public QNation(Path<? extends Nation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNation(PathMetadata metadata) {
        super(Nation.class, metadata);
    }

}

