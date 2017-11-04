package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NoticeItem.class)
public abstract class NoticeItem_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<NoticeItem, Date> readOn;
	public static volatile SingularAttribute<NoticeItem, String> doctorName;
	public static volatile SingularAttribute<NoticeItem, Long> doctorId;
	public static volatile SingularAttribute<NoticeItem, Date> sendOn;
	public static volatile SingularAttribute<NoticeItem, Long> sendById;
	public static volatile SingularAttribute<NoticeItem, Notice> notice;
	public static volatile SingularAttribute<NoticeItem, String> sendBy;

}

