package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.CodeStatus.StatusStr;
import com.qiaobei.hmp.modules.entity.JClassAdviceDict.Advice_Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JClassAdviceDict.class)
public abstract class JClassAdviceDict_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<JClassAdviceDict, String> createBy;
	public static volatile SingularAttribute<JClassAdviceDict, JExamSubclassDict> jExamSubclassDict;
	public static volatile SingularAttribute<JClassAdviceDict, StatusStr> isRemove;
	public static volatile SingularAttribute<JClassAdviceDict, String> helpCode;
	public static volatile SingularAttribute<JClassAdviceDict, JLabClass> jLabClass;
	public static volatile SingularAttribute<JClassAdviceDict, Advice_Type> adviceType;
	public static volatile SingularAttribute<JClassAdviceDict, String> adviceName;
	public static volatile SingularAttribute<JClassAdviceDict, Date> createDate;

}

