package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Suggest.class)
public abstract class Suggest_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<Suggest, String> createBy;
	public static volatile SingularAttribute<Suggest, Long> tagId;
	public static volatile SingularAttribute<Suggest, Long> createById;
	public static volatile SingularAttribute<Suggest, Date> createOn;
	public static volatile SingularAttribute<Suggest, String> tagName;
	public static volatile SingularAttribute<Suggest, String> content;

}

