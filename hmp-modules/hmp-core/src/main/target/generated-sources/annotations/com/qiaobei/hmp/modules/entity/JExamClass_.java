package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.CodeStatus.StatusStr;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JExamClass.class)
public abstract class JExamClass_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<JExamClass, String> createBy;
	public static volatile SingularAttribute<JExamClass, StatusStr> isRemove;
	public static volatile SingularAttribute<JExamClass, String> className;
	public static volatile SingularAttribute<JExamClass, String> type;
	public static volatile ListAttribute<JExamClass, JExamSubclassDict> jExamSubclassDictList;
	public static volatile SingularAttribute<JExamClass, Date> createDate;

}

