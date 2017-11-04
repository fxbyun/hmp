package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.AppointPatient.SendMessage;
import com.qiaobei.hmp.modules.entity.AppointPatient.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppointPatient.class)
public abstract class AppointPatient_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<AppointPatient, Date> date;
	public static volatile SingularAttribute<AppointPatient, Patient> patient;
	public static volatile SingularAttribute<AppointPatient, SendMessage> hasSendMessage;
	public static volatile SingularAttribute<AppointPatient, AppointList> appointList;
	public static volatile SingularAttribute<AppointPatient, Status> appointStatus;

}

