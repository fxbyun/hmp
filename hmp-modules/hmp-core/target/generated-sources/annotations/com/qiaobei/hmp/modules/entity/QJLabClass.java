package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJLabClass is a Querydsl query type for JLabClass
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJLabClass extends EntityPathBase<JLabClass> {

    private static final long serialVersionUID = 1259928335L;

    public static final QJLabClass jLabClass = new QJLabClass("jLabClass");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath className = createString("className");

    public final StringPath createBy = createString("createBy");

    public final DatePath<java.sql.Date> createDate = createDate("createDate", java.sql.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<CodeStatus.StatusStr> isRemove = createEnum("isRemove", CodeStatus.StatusStr.class);

    public final ListPath<JClassAdviceDict, QJClassAdviceDict> jClassAdviceDictList = this.<JClassAdviceDict, QJClassAdviceDict>createList("jClassAdviceDictList", JClassAdviceDict.class, QJClassAdviceDict.class, PathInits.DIRECT2);

    public QJLabClass(String variable) {
        super(JLabClass.class, forVariable(variable));
    }

    public QJLabClass(Path<? extends JLabClass> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJLabClass(PathMetadata metadata) {
        super(JLabClass.class, metadata);
    }

}

