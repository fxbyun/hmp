package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJExamSubclassDict is a Querydsl query type for JExamSubclassDict
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJExamSubclassDict extends EntityPathBase<JExamSubclassDict> {

    private static final long serialVersionUID = -1332323343L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJExamSubclassDict jExamSubclassDict = new QJExamSubclassDict("jExamSubclassDict");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath className = createString("className");

    public final StringPath createBy = createString("createBy");

    public final DatePath<java.sql.Date> createDate = createDate("createDate", java.sql.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<CodeStatus.StatusStr> isRemove = createEnum("isRemove", CodeStatus.StatusStr.class);

    public final ListPath<JClassAdviceDict, QJClassAdviceDict> jClassAdviceDictList = this.<JClassAdviceDict, QJClassAdviceDict>createList("jClassAdviceDictList", JClassAdviceDict.class, QJClassAdviceDict.class, PathInits.DIRECT2);

    public final QJExamClass jExamClass;

    public QJExamSubclassDict(String variable) {
        this(JExamSubclassDict.class, forVariable(variable), INITS);
    }

    public QJExamSubclassDict(Path<? extends JExamSubclassDict> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJExamSubclassDict(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJExamSubclassDict(PathMetadata metadata, PathInits inits) {
        this(JExamSubclassDict.class, metadata, inits);
    }

    public QJExamSubclassDict(Class<? extends JExamSubclassDict> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jExamClass = inits.isInitialized("jExamClass") ? new QJExamClass(forProperty("jExamClass")) : null;
    }

}

