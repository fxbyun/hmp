package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.VitalSign.Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VitalSign.class)
public abstract class VitalSign_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<VitalSign, String> patientName;
	public static volatile SingularAttribute<VitalSign, String> patientUid;
	public static volatile SingularAttribute<VitalSign, Date> createOn;
	public static volatile SingularAttribute<VitalSign, Emr> emr;
	public static volatile SingularAttribute<VitalSign, Type> type;
	public static volatile SingularAttribute<VitalSign, Double> value;

}

