package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIaiIntoDetail is a Querydsl query type for IaiIntoDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QIaiIntoDetail extends EntityPathBase<IaiIntoDetail> {

    private static final long serialVersionUID = 173825948L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIaiIntoDetail iaiIntoDetail = new QIaiIntoDetail("iaiIntoDetail");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath barCode = createString("barCode");

    public final NumberPath<Double> bayingPrice = createNumber("bayingPrice", Double.class);

    public final NumberPath<Long> companyId = createNumber("companyId", Long.class);

    public final EnumPath<IaiIntoDetail.DetailType> detailType = createEnum("detailType", IaiIntoDetail.DetailType.class);

    public final QIaiInto iaiInto;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Double> incomeQuantity = createNumber("incomeQuantity", Double.class);

    public final QMedicinePrivate medicine;

    public final EnumPath<IaiIntoDetail.DetailStatus> status = createEnum("status", IaiIntoDetail.DetailStatus.class);

    public final NumberPath<Double> totalNumber = createNumber("totalNumber", Double.class);

    public final DateTimePath<java.util.Date> validityDate = createDateTime("validityDate", java.util.Date.class);

    public final NumberPath<Long> warnLine = createNumber("warnLine", Long.class);

    public QIaiIntoDetail(String variable) {
        this(IaiIntoDetail.class, forVariable(variable), INITS);
    }

    public QIaiIntoDetail(Path<? extends IaiIntoDetail> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIaiIntoDetail(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIaiIntoDetail(PathMetadata metadata, PathInits inits) {
        this(IaiIntoDetail.class, metadata, inits);
    }

    public QIaiIntoDetail(Class<? extends IaiIntoDetail> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.iaiInto = inits.isInitialized("iaiInto") ? new QIaiInto(forProperty("iaiInto"), inits.get("iaiInto")) : null;
        this.medicine = inits.isInitialized("medicine") ? new QMedicinePrivate(forProperty("medicine"), inits.get("medicine")) : null;
    }

}

