package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMsgMeassage is a Querydsl query type for MsgMeassage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMsgMeassage extends EntityPathBase<MsgMeassage> {

    private static final long serialVersionUID = -946687183L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMsgMeassage msgMeassage = new QMsgMeassage("msgMeassage");

    public final QIdEntity _super = new QIdEntity(this);

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath info = createString("info");

    public QMsgMeassage(String variable) {
        this(MsgMeassage.class, forVariable(variable), INITS);
    }

    public QMsgMeassage(Path<? extends MsgMeassage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMsgMeassage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMsgMeassage(PathMetadata metadata, PathInits inits) {
        this(MsgMeassage.class, metadata, inits);
    }

    public QMsgMeassage(Class<? extends MsgMeassage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
    }

}

