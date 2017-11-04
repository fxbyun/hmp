package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> salt;
	public static volatile SingularAttribute<User, Gender> gender;
	public static volatile SingularAttribute<User, Date> createTime;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> mobile;
	public static volatile SingularAttribute<User, String> tel;
	public static volatile ListAttribute<User, Role> roleList;
	public static volatile SingularAttribute<User, Integer> age;
	public static volatile SingularAttribute<User, String> email;

}

