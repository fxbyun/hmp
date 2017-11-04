package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.AppointReward.RewardStatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppointReward.class)
public abstract class AppointReward_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<AppointReward, Doctor> doctor;
	public static volatile SingularAttribute<AppointReward, String> patientName;
	public static volatile SingularAttribute<AppointReward, Double> money;
	public static volatile SingularAttribute<AppointReward, String> orderId;
	public static volatile SingularAttribute<AppointReward, Long> patientId;
	public static volatile SingularAttribute<AppointReward, MobileUser> mobileUser;
	public static volatile SingularAttribute<AppointReward, String> mobile;
	public static volatile SingularAttribute<AppointReward, String> message;
	public static volatile SingularAttribute<AppointReward, Date> appointDate;
	public static volatile SingularAttribute<AppointReward, AppointPatient> appointPatient;
	public static volatile SingularAttribute<AppointReward, RewardStatus> status;
	public static volatile SingularAttribute<AppointReward, Date> payDate;

}

