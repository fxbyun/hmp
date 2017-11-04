package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.AppointConfig.Static;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppointConfig.class)
public abstract class AppointConfig_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<AppointConfig, Doctor> doctor;
	public static volatile SingularAttribute<AppointConfig, Integer> perMin;
	public static volatile SingularAttribute<AppointConfig, Static> openStatic;
	public static volatile SingularAttribute<AppointConfig, Date> flagChangeDate;
	public static volatile SingularAttribute<AppointConfig, Integer> personNum;

}

