package com.qiaobei.hmp.modules.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Role, String> code;
	public static volatile SingularAttribute<Role, String> name;
	public static volatile ListAttribute<Role, Permission> permissionList;
	public static volatile SingularAttribute<Role, String> remark;

}

