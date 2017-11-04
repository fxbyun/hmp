package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.IaiIntoDetail.DetailStatus;
import com.qiaobei.hmp.modules.entity.IaiIntoDetail.DetailType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IaiIntoDetail.class)
public abstract class IaiIntoDetail_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<IaiIntoDetail, Double> bayingPrice;
	public static volatile SingularAttribute<IaiIntoDetail, DetailType> detailType;
	public static volatile SingularAttribute<IaiIntoDetail, Double> totalNumber;
	public static volatile SingularAttribute<IaiIntoDetail, Long> companyId;
	public static volatile SingularAttribute<IaiIntoDetail, Date> validityDate;
	public static volatile SingularAttribute<IaiIntoDetail, Long> warnLine;
	public static volatile SingularAttribute<IaiIntoDetail, MedicinePrivate> medicine;
	public static volatile SingularAttribute<IaiIntoDetail, Double> incomeQuantity;
	public static volatile SingularAttribute<IaiIntoDetail, IaiInto> iaiInto;
	public static volatile SingularAttribute<IaiIntoDetail, String> barCode;
	public static volatile SingularAttribute<IaiIntoDetail, DetailStatus> status;

}

