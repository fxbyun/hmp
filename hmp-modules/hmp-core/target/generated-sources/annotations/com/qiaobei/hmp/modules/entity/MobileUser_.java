package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.support.UserStaticEnum;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MobileUser.class)
public abstract class MobileUser_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<MobileUser, Date> birthday;
	public static volatile SingularAttribute<MobileUser, String> area;
	public static volatile SingularAttribute<MobileUser, String> locationPlace;
	public static volatile SingularAttribute<MobileUser, Integer> cityNo;
	public static volatile SingularAttribute<MobileUser, String> address;
	public static volatile SingularAttribute<MobileUser, String> salt;
	public static volatile SingularAttribute<MobileUser, Gender> gender;
	public static volatile SingularAttribute<MobileUser, String> city;
	public static volatile SingularAttribute<MobileUser, String> openId;
	public static volatile SingularAttribute<MobileUser, String> mobile;
	public static volatile SingularAttribute<MobileUser, String> wxId;
	public static volatile SingularAttribute<MobileUser, String> helpCode;
	public static volatile SingularAttribute<MobileUser, String> uid;
	public static volatile SingularAttribute<MobileUser, String> province;
	public static volatile SingularAttribute<MobileUser, Integer> areaNo;
	public static volatile ListAttribute<MobileUser, PatientTag> patientTagList;
	public static volatile SingularAttribute<MobileUser, Integer> provinceNo;
	public static volatile SingularAttribute<MobileUser, String> name;
	public static volatile SingularAttribute<MobileUser, String> sfId;
	public static volatile SingularAttribute<MobileUser, String> udid;
	public static volatile SingularAttribute<MobileUser, String> email;
	public static volatile SingularAttribute<MobileUser, UserStaticEnum> status;

}

