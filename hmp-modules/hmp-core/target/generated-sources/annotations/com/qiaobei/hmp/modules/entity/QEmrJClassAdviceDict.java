package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmrJClassAdviceDict is a Querydsl query type for EmrJClassAdviceDict
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmrJClassAdviceDict extends EntityPathBase<EmrJClassAdviceDict> {

    private static final long serialVersionUID = -32473184L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmrJClassAdviceDict emrJClassAdviceDict = new QEmrJClassAdviceDict("emrJClassAdviceDict");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath adviceName = createString("adviceName");

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final QEmr emr;

    public final ListPath<ExamLabFile, QExamLabFile> examLabFileList = this.<ExamLabFile, QExamLabFile>createList("examLabFileList", ExamLabFile.class, QExamLabFile.class, PathInits.DIRECT2);

    public final StringPath examLabName = createString("examLabName");

    public final NumberPath<Long> exp1 = createNumber("exp1", Long.class);

    public final NumberPath<Long> exp2 = createNumber("exp2", Long.class);

    public final NumberPath<Long> exp3 = createNumber("exp3", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath info = createString("info");

    public final QPatient patient;

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final StringPath resultInfo = createString("resultInfo");

    public final EnumPath<StatusEntity.Status> status = createEnum("status", StatusEntity.Status.class);

    public final StringPath tmpFileNameId = createString("tmpFileNameId");

    public final EnumPath<JClassAdviceDict.Advice_Type> type = createEnum("type", JClassAdviceDict.Advice_Type.class);

    public final DateTimePath<java.util.Date> updateOn = createDateTime("updateOn", java.util.Date.class);

    public QEmrJClassAdviceDict(String variable) {
        this(EmrJClassAdviceDict.class, forVariable(variable), INITS);
    }

    public QEmrJClassAdviceDict(Path<? extends EmrJClassAdviceDict> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmrJClassAdviceDict(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmrJClassAdviceDict(PathMetadata metadata, PathInits inits) {
        this(EmrJClassAdviceDict.class, metadata, inits);
    }

    public QEmrJClassAdviceDict(Class<? extends EmrJClassAdviceDict> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emr = inits.isInitialized("emr") ? new QEmr(forProperty("emr"), inits.get("emr")) : null;
        this.patient = inits.isInitialized("patient") ? new QPatient(forProperty("patient")) : null;
    }

}

