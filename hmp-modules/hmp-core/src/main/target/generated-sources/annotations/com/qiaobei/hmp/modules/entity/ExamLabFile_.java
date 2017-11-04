package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.ExamLabFile.Exam_Lab_File_Type;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExamLabFile.class)
public abstract class ExamLabFile_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<ExamLabFile, Exam_Lab_File_Type> types;
	public static volatile SingularAttribute<ExamLabFile, String> fileName;
	public static volatile SingularAttribute<ExamLabFile, byte[]> fileData;
	public static volatile SingularAttribute<ExamLabFile, Date> createOn;
	public static volatile SingularAttribute<ExamLabFile, EmrJClassAdviceDict> emrJClassAdviceDict;

}

