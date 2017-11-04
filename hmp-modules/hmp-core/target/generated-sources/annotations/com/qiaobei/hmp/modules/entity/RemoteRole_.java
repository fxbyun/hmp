package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.RemoteRole.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RemoteRole.class)
public abstract class RemoteRole_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile ListAttribute<RemoteRole, RemoteUser> userList;
	public static volatile SingularAttribute<RemoteRole, Date> createTime;
	public static volatile SingularAttribute<RemoteRole, String> name;
	public static volatile ListAttribute<RemoteRole, RemotePermission> permissionList;
	public static volatile SingularAttribute<RemoteRole, String> description;
	public static volatile SingularAttribute<RemoteRole, Date> updateTime;
	public static volatile SingularAttribute<RemoteRole, Status> status;

}

