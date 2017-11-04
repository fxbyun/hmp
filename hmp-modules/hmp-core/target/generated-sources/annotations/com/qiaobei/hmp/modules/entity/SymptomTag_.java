package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SymptomTag.class)
public abstract class SymptomTag_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<SymptomTag, String> doctorName;
	public static volatile SingularAttribute<SymptomTag, String> code;
	public static volatile SingularAttribute<SymptomTag, Long> doctorId;
	public static volatile SingularAttribute<SymptomTag, String> name;
	public static volatile SingularAttribute<SymptomTag, Date> createOn;
	public static volatile SingularAttribute<SymptomTag, String> helpCode;
	public static volatile SingularAttribute<SymptomTag, Long> frequency;

}

