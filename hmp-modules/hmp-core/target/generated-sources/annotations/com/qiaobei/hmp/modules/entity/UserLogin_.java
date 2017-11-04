package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserLogin.class)
public abstract class UserLogin_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<UserLogin, Date> loginTime;
	public static volatile SingularAttribute<UserLogin, String> loginIP;
	public static volatile SingularAttribute<UserLogin, Doctor> user;

}

