package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Indent.class)
public abstract class Indent_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<Indent, String> quantity;
	public static volatile SingularAttribute<Indent, Date> createTime;
	public static volatile SingularAttribute<Indent, Double> price;
	public static volatile SingularAttribute<Indent, Medicine> medicine;
	public static volatile SingularAttribute<Indent, String> company;
	public static volatile SingularAttribute<Indent, Doctor> user;

}

