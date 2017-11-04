package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMsgSendHistoryDetail is a Querydsl query type for MsgSendHistoryDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMsgSendHistoryDetail extends EntityPathBase<MsgSendHistoryDetail> {

    private static final long serialVersionUID = 1880773890L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMsgSendHistoryDetail msgSendHistoryDetail = new QMsgSendHistoryDetail("msgSendHistoryDetail");

    public final QIdEntity _super = new QIdEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QMsgSendHistory msgSendHistory;

    public final QPatient patient;

    public final EnumPath<MsgSendHistoryDetail.SendStatus> sendStatus = createEnum("sendStatus", MsgSendHistoryDetail.SendStatus.class);

    public QMsgSendHistoryDetail(String variable) {
        this(MsgSendHistoryDetail.class, forVariable(variable), INITS);
    }

    public QMsgSendHistoryDetail(Path<? extends MsgSendHistoryDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMsgSendHistoryDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMsgSendHistoryDetail(PathMetadata metadata, PathInits inits) {
        this(MsgSendHistoryDetail.class, metadata, inits);
    }

    public QMsgSendHistoryDetail(Class<? extends MsgSendHistoryDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.msgSendHistory = inits.isInitialized("msgSendHistory") ? new QMsgSendHistory(forProperty("msgSendHistory"), inits.get("msgSendHistory")) : null;
        this.patient = inits.isInitialized("patient") ? new QPatient(forProperty("patient")) : null;
    }

}

