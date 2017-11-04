package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.CodeStatus.StatusStr;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JExamSubclassDict.class)
public abstract class JExamSubclassDict_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<JExamSubclassDict, String> createBy;
	public static volatile SingularAttribute<JExamSubclassDict, JExamClass> jExamClass;
	public static volatile SingularAttribute<JExamSubclassDict, StatusStr> isRemove;
	public static volatile ListAttribute<JExamSubclassDict, JClassAdviceDict> jClassAdviceDictList;
	public static volatile SingularAttribute<JExamSubclassDict, String> className;
	public static volatile SingularAttribute<JExamSubclassDict, Date> createDate;

}

