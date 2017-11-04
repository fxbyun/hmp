package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataFile is a Querydsl query type for DataFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataFile extends EntityPathBase<DataFile> {

    private static final long serialVersionUID = -1765121940L;

    public static final QDataFile dataFile = new QDataFile("dataFile");

    public final QIdEntity _super = new QIdEntity(this);

    public final ArrayPath<byte[], Byte> content = createArray("content", byte[].class);

    public final StringPath fileName = createString("fileName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> logicId = createNumber("logicId", Long.class);

    public final EnumPath<DataFile.Type> type = createEnum("type", DataFile.Type.class);

    public QDataFile(String variable) {
        super(DataFile.class, forVariable(variable));
    }

    public QDataFile(Path<? extends DataFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataFile(PathMetadata metadata) {
        super(DataFile.class, metadata);
    }

}

