package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmrFile is a Querydsl query type for EmrFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmrFile extends EntityPathBase<EmrFile> {

    private static final long serialVersionUID = -1322355584L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmrFile emrFile = new QEmrFile("emrFile");

    public final QIdEntity _super = new QIdEntity(this);

    public final ArrayPath<byte[], Byte> content = createArray("content", byte[].class);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    public final QDoctor doctor;

    public final QEmr emr;

    public final StringPath fileName = createString("fileName");

    public final EnumPath<EmrFile.EmrFileType> fileType = createEnum("fileType", EmrFile.EmrFileType.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QEmrFile(String variable) {
        this(EmrFile.class, forVariable(variable), INITS);
    }

    public QEmrFile(Path<? extends EmrFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmrFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmrFile(PathMetadata metadata, PathInits inits) {
        this(EmrFile.class, metadata, inits);
    }

    public QEmrFile(Class<? extends EmrFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
        this.emr = inits.isInitialized("emr") ? new QEmr(forProperty("emr"), inits.get("emr")) : null;
    }

}

