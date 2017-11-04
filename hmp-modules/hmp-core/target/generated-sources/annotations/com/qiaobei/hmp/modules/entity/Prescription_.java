package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prescription.class)
public abstract class Prescription_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Prescription, String> doctorName;
	public static volatile SingularAttribute<Prescription, Long> doctorId;
	public static volatile SingularAttribute<Prescription, String> name;
	public static volatile SingularAttribute<Prescription, String> diagnosis;
	public static volatile SingularAttribute<Prescription, String> remark;
	public static volatile SingularAttribute<Prescription, Date> createOn;
	public static volatile SingularAttribute<Prescription, Type> medicineType;
	public static volatile SingularAttribute<Prescription, String> categoryName;
	public static volatile ListAttribute<Prescription, PrescriptionItem> prescriptionItemList;
	public static volatile SingularAttribute<Prescription, Long> categoryId;

}

