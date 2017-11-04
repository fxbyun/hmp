package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Retail.Charge_Status;
import com.qiaobei.hmp.modules.entity.Retail.Retail_Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Retail.class)
public abstract class Retail_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile ListAttribute<Retail, RetailMedicine> retailMedicineList;
	public static volatile SingularAttribute<Retail, Doctor> doctor;
	public static volatile SingularAttribute<Retail, Double> allMedCost;
	public static volatile SingularAttribute<Retail, String> orderId;
	public static volatile SingularAttribute<Retail, Patient> patient;
	public static volatile SingularAttribute<Retail, Boolean> ifBinding;
	public static volatile SingularAttribute<Retail, Date> chargeTime;
	public static volatile SingularAttribute<Retail, Double> realCost;
	public static volatile SingularAttribute<Retail, Pharmacist> pharmacist;
	public static volatile SingularAttribute<Retail, Charge_Status> chargeStatus;
	public static volatile SingularAttribute<Retail, Retail_Status> retailStatus;

}

