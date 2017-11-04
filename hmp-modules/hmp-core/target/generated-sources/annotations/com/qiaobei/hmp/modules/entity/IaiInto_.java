package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.IaiInto.IaiIntoStatus;
import com.qiaobei.hmp.modules.entity.IaiInto.IaiIntoType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IaiInto.class)
public abstract class IaiInto_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<IaiInto, String> goodsNo;
	public static volatile SingularAttribute<IaiInto, Long> createBy;
	public static volatile SingularAttribute<IaiInto, String> whoCreate;
	public static volatile SingularAttribute<IaiInto, Supplier> supplier;
	public static volatile SingularAttribute<IaiInto, IaiIntoType> iaiIntoType;
	public static volatile SingularAttribute<IaiInto, Double> totalMoney;
	public static volatile ListAttribute<IaiInto, IaiIntoDetail> intoDetailList;
	public static volatile SingularAttribute<IaiInto, String> uuid;
	public static volatile SingularAttribute<IaiInto, Date> createDate;
	public static volatile SingularAttribute<IaiInto, IaiIntoStatus> status;

}

