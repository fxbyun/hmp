package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIaiLossDetail is a Querydsl query type for IaiLossDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIaiLossDetail extends EntityPathBase<IaiLossDetail> {

    private static final long serialVersionUID = -415964641L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIaiLossDetail iaiLossDetail = new QIaiLossDetail("iaiLossDetail");

    public final QStatusEntity _super = new QStatusEntity(this);

    public final StringPath barCode = createString("barCode");

    public final NumberPath<Double> bayingPrice = createNumber("bayingPrice", Double.class);

    public final NumberPath<Long> companyId = createNumber("companyId", Long.class);

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    public final EnumPath<IaiLossDetail.DetailStatus> detailStatus = createEnum("detailStatus", IaiLossDetail.DetailStatus.class);

    public final NumberPath<Long> emrId = createNumber("emrId", Long.class);

    public final NumberPath<Long> emrMedId = createNumber("emrMedId", Long.class);

    public final NumberPath<Long> iaiIntoDetailId = createNumber("iaiIntoDetailId", Long.class);

    public final QIaiLoss iaiLoss;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<IaiLossDetail.IaiLossType> lossType = createEnum("lossType", IaiLossDetail.IaiLossType.class);

    public final QMedicinePrivate medicine;

    public final StringPath standard = createString("standard");

    //inherited
    public final EnumPath<StatusEntity.Status> status = _super.status;

    public final NumberPath<Long> subDoctorId = createNumber("subDoctorId", Long.class);

    public final NumberPath<Double> totalNumber = createNumber("totalNumber", Double.class);

    public final StringPath units = createString("units");

    public final DatePath<java.util.Date> validityDate = createDate("validityDate", java.util.Date.class);

    public final NumberPath<Long> warnLine = createNumber("warnLine", Long.class);

    public QIaiLossDetail(String variable) {
        this(IaiLossDetail.class, forVariable(variable), INITS);
    }

    public QIaiLossDetail(Path<? extends IaiLossDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIaiLossDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIaiLossDetail(PathMetadata metadata, PathInits inits) {
        this(IaiLossDetail.class, metadata, inits);
    }

    public QIaiLossDetail(Class<? extends IaiLossDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.iaiLoss = inits.isInitialized("iaiLoss") ? new QIaiLoss(forProperty("iaiLoss")) : null;
        this.medicine = inits.isInitialized("medicine") ? new QMedicinePrivate(forProperty("medicine"), inits.get("medicine")) : null;
    }

}

