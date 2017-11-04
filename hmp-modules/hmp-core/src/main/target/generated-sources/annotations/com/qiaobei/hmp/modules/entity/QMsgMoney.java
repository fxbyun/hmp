package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMsgMoney is a Querydsl query type for MsgMoney
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMsgMoney extends EntityPathBase<MsgMoney> {

    private static final long serialVersionUID = -558957083L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMsgMoney msgMoney = new QMsgMoney("msgMoney");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Double> deposit = createNumber("deposit", Double.class);

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QMsgMoney(String variable) {
        this(MsgMoney.class, forVariable(variable), INITS);
    }

    public QMsgMoney(Path<? extends MsgMoney> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMsgMoney(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMsgMoney(PathMetadata metadata, PathInits inits) {
        this(MsgMoney.class, metadata, inits);
    }

    public QMsgMoney(Class<? extends MsgMoney> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
    }

}

