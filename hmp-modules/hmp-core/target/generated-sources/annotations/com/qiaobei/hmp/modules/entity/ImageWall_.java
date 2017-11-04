package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.ImageWall.ImageLevel;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ImageWall.class)
public abstract class ImageWall_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<ImageWall, Date> date;
	public static volatile SingularAttribute<ImageWall, String> fileName;
	public static volatile SingularAttribute<ImageWall, String> outpatientService;
	public static volatile SingularAttribute<ImageWall, ImageLevel> level;
	public static volatile SingularAttribute<ImageWall, Long> doctorId;
	public static volatile SingularAttribute<ImageWall, byte[]> content;

}

