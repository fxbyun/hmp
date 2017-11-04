package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMsgRechargeDetail is a Querydsl query type for MsgRechargeDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMsgRechargeDetail extends EntityPathBase<MsgRechargeDetail> {

    private static final long serialVersionUID = -1515796909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMsgRechargeDetail msgRechargeDetail = new QMsgRechargeDetail("msgRechargeDetail");

    public final QIdEntity _super = new QIdEntity(this);

    public final NumberPath<Double> addMoney = createNumber("addMoney", Double.class);

    public final NumberPath<Double> afterMoney = createNumber("afterMoney", Double.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final QDoctor doctor;

    public final EnumPath<PayType> havePay = createEnum("havePay", PayType.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Double> money = createNumber("money", Double.class);

    public final StringPath rechargeNum = createString("rechargeNum");

    public final StringPath rechargeWay = createString("rechargeWay");

    public final StringPath reMark = createString("reMark");

    public final DateTimePath<java.util.Date> updateDate = createDateTime("updateDate", java.util.Date.class);

    public final StringPath wxInfoBack = createString("wxInfoBack");

    public final StringPath wxInfoSend = createString("wxInfoSend");

    public QMsgRechargeDetail(String variable) {
        this(MsgRechargeDetail.class, forVariable(variable), INITS);
    }

    public QMsgRechargeDetail(Path<? extends MsgRechargeDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMsgRechargeDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMsgRechargeDetail(PathMetadata metadata, PathInits inits) {
        this(MsgRechargeDetail.class, metadata, inits);
    }

    public QMsgRechargeDetail(Class<? extends MsgRechargeDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor")) : null;
    }

}

