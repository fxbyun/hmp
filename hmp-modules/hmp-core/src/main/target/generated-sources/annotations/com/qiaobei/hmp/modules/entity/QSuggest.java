package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSuggest is a Querydsl query type for Suggest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSuggest extends EntityPathBase<Suggest> {

    private static final long serialVersionUID = -1562351970L;

    public static final QSuggest suggest = new QSuggest("suggest");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath content = createString("content");

    public final StringPath createBy = createString("createBy");

    public final NumberPath<Long> createById = createNumber("createById", Long.class);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> tagId = createNumber("tagId", Long.class);

    public final StringPath tagName = createString("tagName");

    public QSuggest(String variable) {
        super(Suggest.class, forVariable(variable));
    }

    public QSuggest(Path<? extends Suggest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSuggest(PathMetadata metadata) {
        super(Suggest.class, metadata);
    }

}

