package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPharmacist is a Querydsl query type for Pharmacist
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPharmacist extends EntityPathBase<Pharmacist> {

    private static final long serialVersionUID = -1632620310L;

    public static final QPharmacist pharmacist = new QPharmacist("pharmacist");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath account = createString("account");

    public final StringPath address = createString("address");

    public final DatePath<java.util.Date> birthday = createDate("birthday", java.util.Date.class);

    public final NumberPath<Long> doctorId = createNumber("doctorId", Long.class);

    public final StringPath email = createString("email");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<Pharmacist.IsChiefNurse> isChiefNurse = createEnum("isChiefNurse", Pharmacist.IsChiefNurse.class);

    public final StringPath mobile = createString("mobile");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<Pharmacist.PersonType> personType = createEnum("personType", Pharmacist.PersonType.class);

    public final StringPath salt = createString("salt");

    public final StringPath sfId = createString("sfId");

    public final StringPath wxId = createString("wxId");

    public QPharmacist(String variable) {
        super(Pharmacist.class, forVariable(variable));
    }

    public QPharmacist(Path<? extends Pharmacist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPharmacist(PathMetadata metadata) {
        super(Pharmacist.class, metadata);
    }

}

