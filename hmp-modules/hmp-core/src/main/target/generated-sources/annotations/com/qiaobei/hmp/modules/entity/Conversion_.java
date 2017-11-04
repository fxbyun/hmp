package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Unit;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Conversion.class)
public abstract class Conversion_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Conversion, Integer> rate;
	public static volatile SingularAttribute<Conversion, Unit> toUnit;
	public static volatile SingularAttribute<Conversion, Medicine> medicine;
	public static volatile SingularAttribute<Conversion, String> medicineName;
	public static volatile SingularAttribute<Conversion, Unit> fromUnit;

}

