package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageWall is a Querydsl query type for ImageWall
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QImageWall extends EntityPathBase<ImageWall> {

    private static final long serialVersionUID = 624501343L;

    public static final QImageWall imageWall = new QImageWall("imageWall");

    public final QIdEntity _super = new QIdEntity(this);

    public final ArrayPath<byte[], Byte> content = createArray("content", byte[].class);

    public final DateTimePath<java.util.Date> date = createDateTime("date", java.util.Date.class);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath fileName = createString("fileName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<ImageWall.ImageLevel> level = createEnum("level", ImageWall.ImageLevel.class);

    public final StringPath outpatientService = createString("outpatientService");

    public QImageWall(String variable) {
        super(ImageWall.class, forVariable(variable));
    }

    public QImageWall(Path<? extends ImageWall> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageWall(PathMetadata metadata) {
        super(ImageWall.class, metadata);
    }

}

