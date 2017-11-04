package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Type;
import com.qiaobei.hmp.modules.entity.Medicine.Unit;
import com.qiaobei.hmp.modules.entity.MedicinePrivate.HaveManager;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MedicinePrivate.class)
public abstract class MedicinePrivate_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<MedicinePrivate, String> standard;
	public static volatile SingularAttribute<MedicinePrivate, String> useQty;
	public static volatile SingularAttribute<MedicinePrivate, HaveManager> haveManager;
	public static volatile SingularAttribute<MedicinePrivate, Unit> useUnit;
	public static volatile SingularAttribute<MedicinePrivate, String> usage;
	public static volatile SingularAttribute<MedicinePrivate, Type> type;
	public static volatile SingularAttribute<MedicinePrivate, String> realQty;
	public static volatile SingularAttribute<MedicinePrivate, String> usingTime;
	public static volatile SingularAttribute<MedicinePrivate, Double> rate;
	public static volatile SingularAttribute<MedicinePrivate, Double> price;
	public static volatile SingularAttribute<MedicinePrivate, String> defaultCompanyName;
	public static volatile SingularAttribute<MedicinePrivate, String> sn;
	public static volatile SingularAttribute<MedicinePrivate, String> info;
	public static volatile SingularAttribute<MedicinePrivate, String> useMode;
	public static volatile SingularAttribute<MedicinePrivate, Company> defaultCompany;
	public static volatile SingularAttribute<MedicinePrivate, String> specification;
	public static volatile SingularAttribute<MedicinePrivate, Medicine> medicine;
	public static volatile SingularAttribute<MedicinePrivate, String> helpCode;
	public static volatile SingularAttribute<MedicinePrivate, String> barCode;
	public static volatile SingularAttribute<MedicinePrivate, Doctor> doctor;
	public static volatile SingularAttribute<MedicinePrivate, Unit> unit;
	public static volatile SingularAttribute<MedicinePrivate, Boolean> usageFlag;
	public static volatile SingularAttribute<MedicinePrivate, String> name;
	public static volatile SingularAttribute<MedicinePrivate, Unit> realUnit;
	public static volatile SingularAttribute<MedicinePrivate, String> category;
	public static volatile SingularAttribute<MedicinePrivate, String> useTimes;

}

