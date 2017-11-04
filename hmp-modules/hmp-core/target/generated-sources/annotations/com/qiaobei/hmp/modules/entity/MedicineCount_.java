package com.qiaobei.hmp.modules.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MedicineCount.class)
public abstract class MedicineCount_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<MedicineCount, Doctor> doctor;
	public static volatile SingularAttribute<MedicineCount, Integer> countSize;
	public static volatile SingularAttribute<MedicineCount, String> diagosisName;
	public static volatile SingularAttribute<MedicineCount, Medicine> medicine;

}

