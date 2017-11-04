package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Advert.Position;
import com.qiaobei.hmp.modules.entity.Advert.Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Advert.class)
public abstract class Advert_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Advert, String> createBy;
	public static volatile SingularAttribute<Advert, String> name;
	public static volatile SingularAttribute<Advert, Long> createById;
	public static volatile SingularAttribute<Advert, Date> createOn;
	public static volatile SingularAttribute<Advert, Integer> indate;
	public static volatile SingularAttribute<Advert, Position> position;
	public static volatile SingularAttribute<Advert, Type> type;
	public static volatile SingularAttribute<Advert, String> content;
	public static volatile SingularAttribute<Advert, String> url;

}

