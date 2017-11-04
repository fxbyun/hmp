package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotice is a Querydsl query type for Notice
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNotice extends EntityPathBase<Notice> {

    private static final long serialVersionUID = 216945790L;

    public static final QNotice notice = new QNotice("notice");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final StringPath content = createString("content");

    public final StringPath createBy = createString("createBy");

    public final NumberPath<Long> createById = createNumber("createById", Long.class);

    public final DateTimePath<java.util.Date> createOn = createDateTime("createOn", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<NoticeItem, QNoticeItem> noticeItemList = this.<NoticeItem, QNoticeItem>createList("noticeItemList", NoticeItem.class, QNoticeItem.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final StringPath subject = createString("subject");

    public final EnumPath<Notice.Type> type = createEnum("type", Notice.Type.class);

    public QNotice(String variable) {
        super(Notice.class, forVariable(variable));
    }

    public QNotice(Path<? extends Notice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotice(PathMetadata metadata) {
        super(Notice.class, metadata);
    }

}

