package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Notice.Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Notice.class)
public abstract class Notice_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Notice, String> createBy;
	public static volatile SingularAttribute<Notice, String> subject;
	public static volatile SingularAttribute<Notice, Long> createById;
	public static volatile SingularAttribute<Notice, Date> createOn;
	public static volatile ListAttribute<Notice, NoticeItem> noticeItemList;
	public static volatile SingularAttribute<Notice, Type> type;
	public static volatile SingularAttribute<Notice, String> content;

}

