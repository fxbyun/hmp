package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Medicine.Unit;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConversionApply.class)
public abstract class ConversionApply_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<ConversionApply, String> verifyBy;
	public static volatile SingularAttribute<ConversionApply, Date> applyOn;
	public static volatile SingularAttribute<ConversionApply, Integer> rate;
	public static volatile SingularAttribute<ConversionApply, Date> verifyOn;
	public static volatile SingularAttribute<ConversionApply, Unit> toUnit;
	public static volatile SingularAttribute<ConversionApply, Medicine> medicine;
	public static volatile SingularAttribute<ConversionApply, String> applyBy;
	public static volatile SingularAttribute<ConversionApply, String> medicineName;
	public static volatile SingularAttribute<ConversionApply, Unit> fromUnit;
	public static volatile SingularAttribute<ConversionApply, Long> verifyById;
	public static volatile SingularAttribute<ConversionApply, Conversion> conversion;
	public static volatile SingularAttribute<ConversionApply, Long> applyById;

}

