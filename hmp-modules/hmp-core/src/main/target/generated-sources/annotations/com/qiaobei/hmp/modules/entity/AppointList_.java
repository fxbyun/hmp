package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.AppointList.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppointList.class)
public abstract class AppointList_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<AppointList, Date> date;
	public static volatile SingularAttribute<AppointList, Doctor> doctor;
	public static volatile SingularAttribute<AppointList, Date> startTime;
	public static volatile SingularAttribute<AppointList, Integer> configPeopleNum;
	public static volatile SingularAttribute<AppointList, Date> endTime;
	public static volatile SingularAttribute<AppointList, Integer> peopleNum;
	public static volatile SingularAttribute<AppointList, Integer> remainder;
	public static volatile SingularAttribute<AppointList, Status> status;

}

