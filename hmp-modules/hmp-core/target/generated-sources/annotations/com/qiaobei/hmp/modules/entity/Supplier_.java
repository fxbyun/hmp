package com.qiaobei.hmp.modules.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Supplier.class)
public abstract class Supplier_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<Supplier, Doctor> doctor;
	public static volatile SingularAttribute<Supplier, String> address;
	public static volatile SingularAttribute<Supplier, String> name;
	public static volatile SingularAttribute<Supplier, String> mobile;

}

