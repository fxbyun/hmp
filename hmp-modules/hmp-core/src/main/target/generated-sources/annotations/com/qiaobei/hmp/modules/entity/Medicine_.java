package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Type;
import com.qiaobei.hmp.modules.entity.Medicine.Unit;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Medicine.class)
public abstract class Medicine_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile ListAttribute<Medicine, MedicinePrivate> medicinePrivateList;
	public static volatile SingularAttribute<Medicine, String> useMode;
	public static volatile SingularAttribute<Medicine, String> standard;
	public static volatile SingularAttribute<Medicine, String> useQty;
	public static volatile ListAttribute<Medicine, DoctorMedicine> doctorMedicineList;
	public static volatile SingularAttribute<Medicine, Unit> useUnit;
	public static volatile SingularAttribute<Medicine, Company> defaultCompany;
	public static volatile ListAttribute<Medicine, MedicineCount> medicineCountList;
	public static volatile SingularAttribute<Medicine, String> usage;
	public static volatile SingularAttribute<Medicine, String> specification;
	public static volatile SingularAttribute<Medicine, String> helpCode;
	public static volatile SingularAttribute<Medicine, Type> type;
	public static volatile SingularAttribute<Medicine, Unit> unit;
	public static volatile SingularAttribute<Medicine, Boolean> usageFlag;
	public static volatile ListAttribute<Medicine, Company> companyList;
	public static volatile SingularAttribute<Medicine, String> usingTime;
	public static volatile SingularAttribute<Medicine, String> rate;
	public static volatile SingularAttribute<Medicine, String> name;
	public static volatile SingularAttribute<Medicine, String> defaultCompanyName;
	public static volatile SingularAttribute<Medicine, String> sn;
	public static volatile SingularAttribute<Medicine, String> category;
	public static volatile SingularAttribute<Medicine, String> useTimes;

}

