package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.AppointPatient.SendMessage;
import com.qiaobei.hmp.modules.entity.AppointPatient.Status;
import com.qiaobei.hmp.modules.entity.AppointReward.RewardStatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AppointExpire.class)
public abstract class AppointExpire_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<AppointExpire, String> patientName;
	public static volatile SingularAttribute<AppointExpire, String> orderId;
	public static volatile SingularAttribute<AppointExpire, Long> patientId;
	public static volatile SingularAttribute<AppointExpire, SendMessage> hasSendMessage;
	public static volatile SingularAttribute<AppointExpire, Long> mobileUserId;
	public static volatile SingularAttribute<AppointExpire, String> message;
	public static volatile SingularAttribute<AppointExpire, Date> appointDate;
	public static volatile SingularAttribute<AppointExpire, RewardStatus> rewardStatus;
	public static volatile SingularAttribute<AppointExpire, Status> appointStatus;
	public static volatile SingularAttribute<AppointExpire, String> mobileNum;
	public static volatile SingularAttribute<AppointExpire, Double> money;
	public static volatile SingularAttribute<AppointExpire, Long> doctorId;
	public static volatile SingularAttribute<AppointExpire, Date> startTime;
	public static volatile SingularAttribute<AppointExpire, Date> endTime;
	public static volatile SingularAttribute<AppointExpire, Date> createDate;
	public static volatile SingularAttribute<AppointExpire, Date> payDate;

}

