package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.RemotePermission.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RemotePermission.class)
public abstract class RemotePermission_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<RemotePermission, String> code;
	public static volatile SingularAttribute<RemotePermission, String> name;
	public static volatile SingularAttribute<RemotePermission, String> description;
	public static volatile ListAttribute<RemotePermission, RemoteRole> roleList;
	public static volatile SingularAttribute<RemotePermission, Integer> parentId;
	public static volatile SingularAttribute<RemotePermission, Status> status;

}

