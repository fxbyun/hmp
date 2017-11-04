package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Unit;
import com.qiaobei.hmp.modules.entity.RetailMedicine.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RetailMedicine.class)
public abstract class RetailMedicine_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<RetailMedicine, String> standard;
	public static volatile SingularAttribute<RetailMedicine, Double> totalPrice;
	public static volatile SingularAttribute<RetailMedicine, MedicinePrivate> medicinePrivate;
	public static volatile SingularAttribute<RetailMedicine, Retail> retail;
	public static volatile SingularAttribute<RetailMedicine, String> barCode;
	public static volatile SingularAttribute<RetailMedicine, Unit> unit;
	public static volatile SingularAttribute<RetailMedicine, Double> copies;
	public static volatile SingularAttribute<RetailMedicine, Double> rate;
	public static volatile SingularAttribute<RetailMedicine, Date> createTime;
	public static volatile SingularAttribute<RetailMedicine, Date> validityDate;
	public static volatile SingularAttribute<RetailMedicine, Double> qty;
	public static volatile SingularAttribute<RetailMedicine, Double> retailPrice;
	public static volatile SingularAttribute<RetailMedicine, Status> status;

}

