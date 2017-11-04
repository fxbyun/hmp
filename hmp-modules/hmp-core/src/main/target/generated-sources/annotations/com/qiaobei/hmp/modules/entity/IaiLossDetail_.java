package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.IaiLossDetail.DetailStatus;
import com.qiaobei.hmp.modules.entity.IaiLossDetail.IaiLossType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IaiLossDetail.class)
public abstract class IaiLossDetail_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<IaiLossDetail, Double> bayingPrice;
	public static volatile SingularAttribute<IaiLossDetail, String> standard;
	public static volatile SingularAttribute<IaiLossDetail, DetailStatus> detailStatus;
	public static volatile SingularAttribute<IaiLossDetail, Double> totalNumber;
	public static volatile SingularAttribute<IaiLossDetail, Long> emrId;
	public static volatile SingularAttribute<IaiLossDetail, MedicinePrivate> medicine;
	public static volatile SingularAttribute<IaiLossDetail, String> units;
	public static volatile SingularAttribute<IaiLossDetail, String> barCode;
	public static volatile SingularAttribute<IaiLossDetail, Long> emrMedId;
	public static volatile SingularAttribute<IaiLossDetail, IaiLossType> lossType;
	public static volatile SingularAttribute<IaiLossDetail, Long> companyId;
	public static volatile SingularAttribute<IaiLossDetail, Long> subDoctorId;
	public static volatile SingularAttribute<IaiLossDetail, IaiLoss> iaiLoss;
	public static volatile SingularAttribute<IaiLossDetail, Date> validityDate;
	public static volatile SingularAttribute<IaiLossDetail, Long> iaiIntoDetailId;
	public static volatile SingularAttribute<IaiLossDetail, Long> warnLine;
	public static volatile SingularAttribute<IaiLossDetail, Date> createDate;

}

