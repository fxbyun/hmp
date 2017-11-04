package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Tag.TagType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tag.class)
public abstract class Tag_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<Tag, String> departmentName;
	public static volatile SingularAttribute<Tag, Long> departmentId;
	public static volatile SingularAttribute<Tag, String> name;
	public static volatile SingularAttribute<Tag, TagType> type;

}

