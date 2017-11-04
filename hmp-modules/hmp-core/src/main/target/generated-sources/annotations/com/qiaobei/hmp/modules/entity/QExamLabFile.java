package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExamLabFile is a Querydsl query type for ExamLabFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExamLabFile extends EntityPathBase<ExamLabFile> {

    private static final long serialVersionUID = 1021814180L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExamLabFile examLabFile = new QExamLabFile("examLabFile");

    public final QIdEntity _super = new QIdEntity(this);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final QEmrJClassAdviceDict emrJClassAdviceDict;

    public final ArrayPath<byte[], Byte> fileData = createArray("fileData", byte[].class);

    public final StringPath fileName = createString("fileName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<ExamLabFile.Exam_Lab_File_Type> types = createEnum("types", ExamLabFile.Exam_Lab_File_Type.class);

    public QExamLabFile(String variable) {
        this(ExamLabFile.class, forVariable(variable), INITS);
    }

    public QExamLabFile(Path<? extends ExamLabFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExamLabFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExamLabFile(PathMetadata metadata, PathInits inits) {
        this(ExamLabFile.class, metadata, inits);
    }

    public QExamLabFile(Class<? extends ExamLabFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emrJClassAdviceDict = inits.isInitialized("emrJClassAdviceDict") ? new QEmrJClassAdviceDict(forProperty("emrJClassAdviceDict"), inits.get("emrJClassAdviceDict")) : null;
    }

}

