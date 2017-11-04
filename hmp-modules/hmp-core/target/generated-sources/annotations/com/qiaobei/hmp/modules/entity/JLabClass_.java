package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.CodeStatus.StatusStr;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JLabClass.class)
public abstract class JLabClass_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<JLabClass, String> createBy;
	public static volatile SingularAttribute<JLabClass, StatusStr> isRemove;
	public static volatile ListAttribute<JLabClass, JClassAdviceDict> jClassAdviceDictList;
	public static volatile SingularAttribute<JLabClass, String> className;
	public static volatile SingularAttribute<JLabClass, Date> createDate;

}

