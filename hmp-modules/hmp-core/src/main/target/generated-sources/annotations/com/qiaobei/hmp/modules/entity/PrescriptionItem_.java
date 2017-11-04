package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Type;
import com.qiaobei.hmp.modules.entity.Medicine.Unit;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PrescriptionItem.class)
public abstract class PrescriptionItem_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<PrescriptionItem, String> useMode;
	public static volatile SingularAttribute<PrescriptionItem, String> standard;
	public static volatile SingularAttribute<PrescriptionItem, String> useQty;
	public static volatile SingularAttribute<PrescriptionItem, Long> medicineId;
	public static volatile SingularAttribute<PrescriptionItem, String> useUnit;
	public static volatile SingularAttribute<PrescriptionItem, String> companyName;
	public static volatile SingularAttribute<PrescriptionItem, String> groupIndex;
	public static volatile SingularAttribute<PrescriptionItem, String> medicineName;
	public static volatile SingularAttribute<PrescriptionItem, Boolean> hasUsage;
	public static volatile SingularAttribute<PrescriptionItem, Long> companyId;
	public static volatile SingularAttribute<PrescriptionItem, Unit> unit;
	public static volatile SingularAttribute<PrescriptionItem, Double> copies;
	public static volatile SingularAttribute<PrescriptionItem, Prescription> prescription;
	public static volatile SingularAttribute<PrescriptionItem, Double> rate;
	public static volatile SingularAttribute<PrescriptionItem, String> usingTime;
	public static volatile SingularAttribute<PrescriptionItem, String> specialInstructions;
	public static volatile SingularAttribute<PrescriptionItem, Double> qty;
	public static volatile SingularAttribute<PrescriptionItem, Type> medicineType;
	public static volatile SingularAttribute<PrescriptionItem, String> multiplexTag;
	public static volatile SingularAttribute<PrescriptionItem, String> useTimes;

}

