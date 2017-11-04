package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.AppointWeekConfig.OpenStatic;
import com.qiaobei.hmp.modules.entity.AppointWeekConfig.ProductStatic;
import com.qiaobei.hmp.modules.entity.AppointWeekConfig.Weekday;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppointWeekConfig.class)
public abstract class AppointWeekConfig_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<AppointWeekConfig, Doctor> doctor;
	public static volatile SingularAttribute<AppointWeekConfig, ProductStatic> productStatic;
	public static volatile SingularAttribute<AppointWeekConfig, OpenStatic> openStatic;
	public static volatile SingularAttribute<AppointWeekConfig, Weekday> weekday;

}

