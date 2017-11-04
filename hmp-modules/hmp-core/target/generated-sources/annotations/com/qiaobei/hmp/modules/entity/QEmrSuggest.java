package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmrSuggest is a Querydsl query type for EmrSuggest
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmrSuggest extends EntityPathBase<EmrSuggest> {

    private static final long serialVersionUID = -1863425536L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmrSuggest emrSuggest = new QEmrSuggest("emrSuggest");

    public final QIdEntity _super = new QIdEntity(this);

    public final QEmr emr;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath suggestContent = createString("suggestContent");

    public final NumberPath<Long> suggestId = createNumber("suggestId", Long.class);

    public QEmrSuggest(String variable) {
        this(EmrSuggest.class, forVariable(variable), INITS);
    }

    public QEmrSuggest(Path<? extends EmrSuggest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmrSuggest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmrSuggest(PathMetadata metadata, PathInits inits) {
        this(EmrSuggest.class, metadata, inits);
    }

    public QEmrSuggest(Class<? extends EmrSuggest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.emr = inits.isInitialized("emr") ? new QEmr(forProperty("emr"), inits.get("emr")) : null;
    }

}

