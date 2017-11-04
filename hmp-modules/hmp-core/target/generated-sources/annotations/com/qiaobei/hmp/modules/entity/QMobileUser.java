package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMobileUser is a Querydsl query type for MobileUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMobileUser extends EntityPathBase<MobileUser> {

    private static final long serialVersionUID = 1509509683L;

    public static final QMobileUser mobileUser = new QMobileUser("mobileUser");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath address = createString("address");

    public final StringPath area = createString("area");

    public final NumberPath<Integer> areaNo = createNumber("areaNo", Integer.class);

    public final DatePath<java.util.Date> birthday = createDate("birthday", java.util.Date.class);

    public final StringPath city = createString("city");

    public final NumberPath<Integer> cityNo = createNumber("cityNo", Integer.class);

    public final StringPath email = createString("email");

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final StringPath helpCode = createString("helpCode");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath locationPlace = createString("locationPlace");

    public final StringPath mobile = createString("mobile");

    public final StringPath name = createString("name");

    public final StringPath openId = createString("openId");

    public final ListPath<PatientTag, QPatientTag> patientTagList = this.<PatientTag, QPatientTag>createList("patientTagList", PatientTag.class, QPatientTag.class, PathInits.DIRECT2);

    public final StringPath province = createString("province");

    public final NumberPath<Integer> provinceNo = createNumber("provinceNo", Integer.class);

    public final StringPath salt = createString("salt");

    public final StringPath sfId = createString("sfId");

    public final EnumPath<com.qiaobei.hmp.modules.support.UserStaticEnum> status = createEnum("status", com.qiaobei.hmp.modules.support.UserStaticEnum.class);

    public final StringPath udid = createString("udid");

    public final StringPath uid = createString("uid");

    public final StringPath wxId = createString("wxId");

    public QMobileUser(String variable) {
        super(MobileUser.class, forVariable(variable));
    }

    public QMobileUser(Path<? extends MobileUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMobileUser(PathMetadata metadata) {
        super(MobileUser.class, metadata);
    }

}

