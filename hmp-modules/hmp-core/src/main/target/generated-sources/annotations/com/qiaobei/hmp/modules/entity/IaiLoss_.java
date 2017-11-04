package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.IaiLoss.LossStatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IaiLoss.class)
public abstract class IaiLoss_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<IaiLoss, String> whoCreate;
	public static volatile SingularAttribute<IaiLoss, Long> createId;
	public static volatile SingularAttribute<IaiLoss, Double> totalMoney;
	public static volatile ListAttribute<IaiLoss, IaiLossDetail> iaiLossDetailList;
	public static volatile SingularAttribute<IaiLoss, String> lossNo;
	public static volatile SingularAttribute<IaiLoss, String> remark;
	public static volatile SingularAttribute<IaiLoss, String> uuid;
	public static volatile SingularAttribute<IaiLoss, Date> createDate;
	public static volatile SingularAttribute<IaiLoss, LossStatus> status;

}

