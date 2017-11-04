package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIaiLoss is a Querydsl query type for IaiLoss
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIaiLoss extends EntityPathBase<IaiLoss> {

    private static final long serialVersionUID = 1875982382L;

    public static final QIaiLoss iaiLoss = new QIaiLoss("iaiLoss");

    public final QIdEntity _super = new QIdEntity(this);

    public final DatePath<java.util.Date> createDate = createDate("createDate", java.util.Date.class);

    public final NumberPath<Long> createId = createNumber("createId", Long.class);

    public final ListPath<IaiLossDetail, QIaiLossDetail> iaiLossDetailList = this.<IaiLossDetail, QIaiLossDetail>createList("iaiLossDetailList", IaiLossDetail.class, QIaiLossDetail.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath lossNo = createString("lossNo");

    public final StringPath remark = createString("remark");

    public final EnumPath<IaiLoss.LossStatus> status = createEnum("status", IaiLoss.LossStatus.class);

    public final NumberPath<Double> totalMoney = createNumber("totalMoney", Double.class);

    public final StringPath uuid = createString("uuid");

    public final StringPath whoCreate = createString("whoCreate");

    public QIaiLoss(String variable) {
        super(IaiLoss.class, forVariable(variable));
    }

    public QIaiLoss(Path<? extends IaiLoss> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIaiLoss(PathMetadata metadata) {
        super(IaiLoss.class, metadata);
    }

}

