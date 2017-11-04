package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Pharmacist.IsChiefNurse;
import com.qiaobei.hmp.modules.entity.Pharmacist.PersonType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pharmacist.class)
public abstract class Pharmacist_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<Pharmacist, Date> birthday;
	public static volatile SingularAttribute<Pharmacist, String> address;
	public static volatile SingularAttribute<Pharmacist, String> salt;
	public static volatile SingularAttribute<Pharmacist, Gender> gender;
	public static volatile SingularAttribute<Pharmacist, String> mobile;
	public static volatile SingularAttribute<Pharmacist, String> wxId;
	public static volatile SingularAttribute<Pharmacist, String> password;
	public static volatile SingularAttribute<Pharmacist, Long> doctorId;
	public static volatile SingularAttribute<Pharmacist, String> name;
	public static volatile SingularAttribute<Pharmacist, String> sfId;
	public static volatile SingularAttribute<Pharmacist, PersonType> personType;
	public static volatile SingularAttribute<Pharmacist, IsChiefNurse> isChiefNurse;
	public static volatile SingularAttribute<Pharmacist, String> account;
	public static volatile SingularAttribute<Pharmacist, String> email;

}

