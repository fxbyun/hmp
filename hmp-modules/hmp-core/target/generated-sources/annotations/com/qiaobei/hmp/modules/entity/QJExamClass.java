package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJExamClass is a Querydsl query type for JExamClass
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJExamClass extends EntityPathBase<JExamClass> {

    private static final long serialVersionUID = 1877650933L;

    public static final QJExamClass jExamClass = new QJExamClass("jExamClass");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath className = createString("className");

    public final StringPath createBy = createString("createBy");

    public final DatePath<java.sql.Date> createDate = createDate("createDate", java.sql.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<CodeStatus.StatusStr> isRemove = createEnum("isRemove", CodeStatus.StatusStr.class);

    public final ListPath<JExamSubclassDict, QJExamSubclassDict> jExamSubclassDictList = this.<JExamSubclassDict, QJExamSubclassDict>createList("jExamSubclassDictList", JExamSubclassDict.class, QJExamSubclassDict.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public QJExamClass(String variable) {
        super(JExamClass.class, forVariable(variable));
    }

    public QJExamClass(Path<? extends JExamClass> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJExamClass(PathMetadata metadata) {
        super(JExamClass.class, metadata);
    }

}

