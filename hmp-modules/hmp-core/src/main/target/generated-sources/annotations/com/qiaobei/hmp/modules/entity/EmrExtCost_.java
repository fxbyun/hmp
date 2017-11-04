package com.qiaobei.hmp.modules.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmrExtCost.class)
public abstract class EmrExtCost_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<EmrExtCost, Long> doctorCostId;
	public static volatile SingularAttribute<EmrExtCost, Double> price;
	public static volatile SingularAttribute<EmrExtCost, String> className;
	public static volatile SingularAttribute<EmrExtCost, Emr> emr;

}

