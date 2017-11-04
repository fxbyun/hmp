package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCard is a Querydsl query type for Card
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCard extends EntityPathBase<Card> {

    private static final long serialVersionUID = -590058954L;

    public static final QCard card = new QCard("card");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final StringPath cardNo = createString("cardNo");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final StringPath udid = createString("udid");

    public QCard(String variable) {
        super(Card.class, forVariable(variable));
    }

    public QCard(Path<? extends Card> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCard(PathMetadata metadata) {
        super(Card.class, metadata);
    }

}

