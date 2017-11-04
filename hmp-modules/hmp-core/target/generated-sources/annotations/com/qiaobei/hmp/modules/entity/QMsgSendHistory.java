package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMsgSendHistory is a Querydsl query type for MsgSendHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMsgSendHistory extends EntityPathBase<MsgSendHistory> {

    private static final long serialVersionUID = -1561112239L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMsgSendHistory msgSendHistory = new QMsgSendHistory("msgSendHistory");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath context = createString("context");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final QDoctor doctor;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<MsgSendHistoryDetail, QMsgSendHistoryDetail> msgSendHistoryDetail = this.<MsgSendHistoryDetail, QMsgSendHistoryDetail>createList("msgSendHistoryDetail", MsgSendHistoryDetail.class, QMsgSendHistoryDetail.class, PathInits.DIRECT2);

    public final EnumPath<MsgSendHistory.SendType> msgType = createEnum("msgType", MsgSendHistory.SendType.class);

    public final NumberPath<Integer> sendSize = createNumber("sendSize", Integer.class);

    public final NumberPath<Integer> successSize = createNumber("successSize", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Double> useMoney = createNumber("useMoney", Double.class);

    public QMsgSendHistory(String variable) {
        this(MsgSendHistory.class, forVariable(variable), INITS);
    }

    public QMsgSendHistory(Path<? extends MsgSendHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMsgSendHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMsgSendHistory(PathMetadata metadata, PathInits inits) {
        this(MsgSendHistory.class, metadata, inits);
    }

    public QMsgSendHistory(Class<? extends MsgSendHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor"), inits.get("doctor")) : null;
    }

}

