package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ErrorLog.class)
public abstract class ErrorLog_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<ErrorLog, Doctor> doctor;
	public static volatile SingularAttribute<ErrorLog, String> errorUrl;
	public static volatile SingularAttribute<ErrorLog, Date> createTime;
	public static volatile SingularAttribute<ErrorLog, String> errorTitle;
	public static volatile SingularAttribute<ErrorLog, String> errorInfo;
	public static volatile SingularAttribute<ErrorLog, String> type;

}

