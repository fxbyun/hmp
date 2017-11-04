package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.JClassAdviceDict.Advice_Type;
import com.qiaobei.hmp.modules.entity.StatusEntity.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmrJClassAdviceDict.class)
public abstract class EmrJClassAdviceDict_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<EmrJClassAdviceDict, String> examLabName;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Emr> emr;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Advice_Type> type;
	public static volatile SingularAttribute<EmrJClassAdviceDict, String> resultInfo;
	public static volatile SingularAttribute<EmrJClassAdviceDict, String> adviceName;
	public static volatile ListAttribute<EmrJClassAdviceDict, ExamLabFile> examLabFileList;
	public static volatile SingularAttribute<EmrJClassAdviceDict, String> tmpFileNameId;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Patient> patient;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Double> price;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Long> exp3;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Date> createOn;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Long> exp2;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Long> exp1;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Date> updateOn;
	public static volatile SingularAttribute<EmrJClassAdviceDict, Status> status;
	public static volatile SingularAttribute<EmrJClassAdviceDict, String> info;

}

