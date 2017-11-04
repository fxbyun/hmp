package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppointTimeConfig.class)
public abstract class AppointTimeConfig_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<AppointTimeConfig, AppointWeekConfig> appointWeekConfig;
	public static volatile SingularAttribute<AppointTimeConfig, Date> startTime;
	public static volatile SingularAttribute<AppointTimeConfig, Date> endTime;

}

