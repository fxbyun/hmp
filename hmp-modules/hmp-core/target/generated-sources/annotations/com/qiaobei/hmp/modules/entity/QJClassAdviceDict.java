package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJClassAdviceDict is a Querydsl query type for JClassAdviceDict
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJClassAdviceDict extends EntityPathBase<JClassAdviceDict> {

    private static final long serialVersionUID = -411644734L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJClassAdviceDict jClassAdviceDict = new QJClassAdviceDict("jClassAdviceDict");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath adviceName = createString("adviceName");

    public final EnumPath<JClassAdviceDict.Advice_Type> adviceType = createEnum("adviceType", JClassAdviceDict.Advice_Type.class);

    public final StringPath createBy = createString("createBy");

    public final DatePath<java.util.Date> createDate = createDate("createDate", java.util.Date.class);

    public final StringPath helpCode = createString("helpCode");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<CodeStatus.StatusStr> isRemove = createEnum("isRemove", CodeStatus.StatusStr.class);

    public final QJExamSubclassDict jExamSubclassDict;

    public final QJLabClass jLabClass;

    public QJClassAdviceDict(String variable) {
        this(JClassAdviceDict.class, forVariable(variable), INITS);
    }

    public QJClassAdviceDict(Path<? extends JClassAdviceDict> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJClassAdviceDict(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJClassAdviceDict(PathMetadata metadata, PathInits inits) {
        this(JClassAdviceDict.class, metadata, inits);
    }

    public QJClassAdviceDict(Class<? extends JClassAdviceDict> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.jExamSubclassDict = inits.isInitialized("jExamSubclassDict") ? new QJExamSubclassDict(forProperty("jExamSubclassDict"), inits.get("jExamSubclassDict")) : null;
        this.jLabClass = inits.isInitialized("jLabClass") ? new QJLabClass(forProperty("jLabClass")) : null;
    }

}

