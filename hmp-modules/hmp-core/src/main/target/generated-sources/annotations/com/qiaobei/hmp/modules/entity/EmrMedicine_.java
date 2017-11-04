package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Type;
import com.qiaobei.hmp.modules.entity.Medicine.Unit;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmrMedicine.class)
public abstract class EmrMedicine_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<EmrMedicine, String> standard;
	public static volatile SingularAttribute<EmrMedicine, String> useQty;
	public static volatile SingularAttribute<EmrMedicine, String> useUnit;
	public static volatile SingularAttribute<EmrMedicine, String> companyName;
	public static volatile SingularAttribute<EmrMedicine, Emr> emr;
	public static volatile SingularAttribute<EmrMedicine, Double> realQty;
	public static volatile SingularAttribute<EmrMedicine, String> doctorName;
	public static volatile SingularAttribute<EmrMedicine, Double> copies;
	public static volatile SingularAttribute<EmrMedicine, Long> doctorId;
	public static volatile SingularAttribute<EmrMedicine, Double> rate;
	public static volatile SingularAttribute<EmrMedicine, String> usingTime;
	public static volatile SingularAttribute<EmrMedicine, Double> price;
	public static volatile SingularAttribute<EmrMedicine, Company> company;
	public static volatile SingularAttribute<EmrMedicine, Type> medicineType;
	public static volatile SingularAttribute<EmrMedicine, String> multiplexTag;
	public static volatile SingularAttribute<EmrMedicine, Double> unitPrice;
	public static volatile SingularAttribute<EmrMedicine, String> useMode;
	public static volatile SingularAttribute<EmrMedicine, Unit> tjUnit;
	public static volatile SingularAttribute<EmrMedicine, Medicine> medicine;
	public static volatile SingularAttribute<EmrMedicine, String> groupIndex;
	public static volatile SingularAttribute<EmrMedicine, String> medicineName;
	public static volatile SingularAttribute<EmrMedicine, Long> medicinePrivateId;
	public static volatile SingularAttribute<EmrMedicine, Boolean> hasUsage;
	public static volatile SingularAttribute<EmrMedicine, Unit> unit;
	public static volatile SingularAttribute<EmrMedicine, String> specialInstructions;
	public static volatile SingularAttribute<EmrMedicine, Double> qty;
	public static volatile SingularAttribute<EmrMedicine, Unit> realUnit;
	public static volatile SingularAttribute<EmrMedicine, Date> createOn;
	public static volatile SingularAttribute<EmrMedicine, String> useTimes;

}

