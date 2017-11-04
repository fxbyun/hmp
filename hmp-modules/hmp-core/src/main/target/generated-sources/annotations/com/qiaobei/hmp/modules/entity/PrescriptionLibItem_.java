package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Type;
import com.qiaobei.hmp.modules.entity.Medicine.Unit;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PrescriptionLibItem.class)
public abstract class PrescriptionLibItem_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<PrescriptionLibItem, Boolean> hasUsage;
	public static volatile SingularAttribute<PrescriptionLibItem, String> useMode;
	public static volatile SingularAttribute<PrescriptionLibItem, PrescriptionLib> prescriptionLib;
	public static volatile SingularAttribute<PrescriptionLibItem, Long> companyId;
	public static volatile SingularAttribute<PrescriptionLibItem, Unit> unit;
	public static volatile SingularAttribute<PrescriptionLibItem, Double> copies;
	public static volatile SingularAttribute<PrescriptionLibItem, Double> rate;
	public static volatile SingularAttribute<PrescriptionLibItem, Long> medicineId;
	public static volatile SingularAttribute<PrescriptionLibItem, String> companyName;
	public static volatile SingularAttribute<PrescriptionLibItem, Double> qty;
	public static volatile SingularAttribute<PrescriptionLibItem, Type> medicineType;
	public static volatile SingularAttribute<PrescriptionLibItem, String> medicineName;

}

