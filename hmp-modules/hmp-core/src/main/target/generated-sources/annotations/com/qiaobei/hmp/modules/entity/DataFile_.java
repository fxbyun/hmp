package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.DataFile.Type;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DataFile.class)
public abstract class DataFile_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<DataFile, String> fileName;
	public static volatile SingularAttribute<DataFile, Type> type;
	public static volatile SingularAttribute<DataFile, Long> logicId;
	public static volatile SingularAttribute<DataFile, byte[]> content;

}

