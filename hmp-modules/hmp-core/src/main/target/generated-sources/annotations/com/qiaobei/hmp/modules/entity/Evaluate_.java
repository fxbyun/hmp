package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Evaluate.Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Evaluate.class)
public abstract class Evaluate_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<Evaluate, String> patientName;
	public static volatile SingularAttribute<Evaluate, String> doctorName;
	public static volatile SingularAttribute<Evaluate, Long> doctorId;
	public static volatile SingularAttribute<Evaluate, Date> createTime;
	public static volatile SingularAttribute<Evaluate, Boolean> readFlag;
	public static volatile SingularAttribute<Evaluate, String> grade;
	public static volatile SingularAttribute<Evaluate, String> patientUid;
	public static volatile SingularAttribute<Evaluate, Emr> emr;
	public static volatile SingularAttribute<Evaluate, Type> type;
	public static volatile SingularAttribute<Evaluate, String> content;

}

