package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SystemLog.class)
public abstract class SystemLog_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<SystemLog, String> actionTime;
	public static volatile SingularAttribute<SystemLog, String> methods;
	public static volatile SingularAttribute<SystemLog, String> module;
	public static volatile SingularAttribute<SystemLog, String> userIP;
	public static volatile SingularAttribute<SystemLog, String> description;
	public static volatile SingularAttribute<SystemLog, String> account;
	public static volatile SingularAttribute<SystemLog, Date> operTime;

}

