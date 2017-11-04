package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.EmrFile.EmrFileType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmrFile.class)
public abstract class EmrFile_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<EmrFile, Doctor> doctor;
	public static volatile SingularAttribute<EmrFile, String> fileName;
	public static volatile SingularAttribute<EmrFile, Date> createOn;
	public static volatile SingularAttribute<EmrFile, Emr> emr;
	public static volatile SingularAttribute<EmrFile, byte[]> content;
	public static volatile SingularAttribute<EmrFile, EmrFileType> fileType;

}

