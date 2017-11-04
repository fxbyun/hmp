package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.JClassAdviceDict.Advice_Type;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(JDoctorExamLab.class)
public abstract class JDoctorExamLab_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<JDoctorExamLab, Long> subId;
	public static volatile SingularAttribute<JDoctorExamLab, Long> subTwoId;
	public static volatile SingularAttribute<JDoctorExamLab, Long> doctorId;
	public static volatile SingularAttribute<JDoctorExamLab, String> examLabName;
	public static volatile SingularAttribute<JDoctorExamLab, Double> price;
	public static volatile SingularAttribute<JDoctorExamLab, Advice_Type> type;
	public static volatile SingularAttribute<JDoctorExamLab, String> classAdviceDictName;
	public static volatile SingularAttribute<JDoctorExamLab, Long> examLabId;

}

