package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNoticeItem is a Querydsl query type for NoticeItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNoticeItem extends EntityPathBase<NoticeItem> {

    private static final long serialVersionUID = -1934175055L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNoticeItem noticeItem = new QNoticeItem("noticeItem");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath doctorName = createString("doctorName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QNotice notice;

    public final DateTimePath<java.util.Date> readOn = createDateTime("readOn", java.util.Date.class);

    public final StringPath sendBy = createString("sendBy");

    public final NumberPath<Long> sendById = createNumber("sendById", Long.class);

    public final DateTimePath<java.util.Date> sendOn = createDateTime("sendOn", java.util.Date.class);

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public QNoticeItem(String variable) {
        this(NoticeItem.class, forVariable(variable), INITS);
    }

    public QNoticeItem(Path<? extends NoticeItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNoticeItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNoticeItem(PathMetadata metadata, PathInits inits) {
        this(NoticeItem.class, metadata, inits);
    }

    public QNoticeItem(Class<? extends NoticeItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.notice = inits.isInitialized("notice") ? new QNotice(forProperty("notice")) : null;
    }

}

