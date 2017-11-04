package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Adverting.Position;
import com.qiaobei.hmp.modules.entity.Adverting.Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Adverting.class)
public abstract class Adverting_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Adverting, String> createBy;
	public static volatile SingularAttribute<Adverting, Long> orderNo;
	public static volatile SingularAttribute<Adverting, String> name;
	public static volatile SingularAttribute<Adverting, Long> createById;
	public static volatile SingularAttribute<Adverting, Date> createOn;
	public static volatile SingularAttribute<Adverting, Integer> indate;
	public static volatile SingularAttribute<Adverting, Position> position;
	public static volatile SingularAttribute<Adverting, Type> type;
	public static volatile SingularAttribute<Adverting, String> content;
	public static volatile SingularAttribute<Adverting, String> url;

}

