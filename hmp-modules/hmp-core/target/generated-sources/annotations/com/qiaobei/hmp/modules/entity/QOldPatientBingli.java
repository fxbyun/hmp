package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOldPatientBingli is a Querydsl query type for OldPatientBingli
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOldPatientBingli extends EntityPathBase<OldPatientBingli> {

    private static final long serialVersionUID = 1724792929L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOldPatientBingli oldPatientBingli = new QOldPatientBingli("oldPatientBingli");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath addr = createString("addr");

    public final DatePath<java.util.Date> age = createDate("age", java.util.Date.class);

    public final StringPath chuFang = createString("chuFang");

    public final StringPath createDate = createString("createDate");

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath doctorName = createString("doctorName");

    public final StringPath feiBie = createString("feiBie");

    public final StringPath fuHe = createString("fuHe");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath keBie = createString("keBie");

    public final StringPath menHenO = createString("menHenO");

    public final QOldPatient oldPatient;

    public final StringPath patienName = createString("patienName");

    public final StringPath peiYao = createString("peiYao");

    public final StringPath qiTa = createString("qiTa");

    public final StringPath sex = createString("sex");

    public final StringPath shouFei = createString("shouFei");

    public final StringPath zhenDuan = createString("zhenDuan");

    public QOldPatientBingli(String variable) {
        this(OldPatientBingli.class, forVariable(variable), INITS);
    }

    public QOldPatientBingli(Path<? extends OldPatientBingli> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOldPatientBingli(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOldPatientBingli(PathMetadata metadata, PathInits inits) {
        this(OldPatientBingli.class, metadata, inits);
    }

    public QOldPatientBingli(Class<? extends OldPatientBingli> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.oldPatient = inits.isInitialized("oldPatient") ? new QOldPatient(forProperty("oldPatient"), inits.get("oldPatient")) : null;
    }

}

