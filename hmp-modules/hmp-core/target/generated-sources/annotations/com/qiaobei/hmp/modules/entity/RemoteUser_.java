package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.RemoteUser.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RemoteUser.class)
public abstract class RemoteUser_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<RemoteUser, String> realName;
	public static volatile SingularAttribute<RemoteUser, String> password;
	public static volatile SingularAttribute<RemoteUser, String> salt;
	public static volatile SingularAttribute<RemoteUser, Date> createTime;
	public static volatile SingularAttribute<RemoteUser, String> openId;
	public static volatile SingularAttribute<RemoteUser, String> mobile;
	public static volatile SingularAttribute<RemoteUser, Date> updateTime;
	public static volatile ListAttribute<RemoteUser, RemoteRole> roleList;
	public static volatile SingularAttribute<RemoteUser, String> userName;
	public static volatile SingularAttribute<RemoteUser, Status> status;

}

