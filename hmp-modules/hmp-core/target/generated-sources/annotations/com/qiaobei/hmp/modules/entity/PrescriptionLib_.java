package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PrescriptionLib.class)
public abstract class PrescriptionLib_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<PrescriptionLib, Long> doctorId;
	public static volatile ListAttribute<PrescriptionLib, PrescriptionLibItem> prescriptionLibItemList;
	public static volatile SingularAttribute<PrescriptionLib, String> name;
	public static volatile SingularAttribute<PrescriptionLib, String> diagnosis;
	public static volatile SingularAttribute<PrescriptionLib, String> remark;
	public static volatile SingularAttribute<PrescriptionLib, Date> createOn;
	public static volatile SingularAttribute<PrescriptionLib, Type> medicineType;
	public static volatile SingularAttribute<PrescriptionLib, String> categoryName;
	public static volatile SingularAttribute<PrescriptionLib, Long> parentId;
	public static volatile SingularAttribute<PrescriptionLib, Long> categoryId;

}

