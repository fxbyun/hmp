package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.Registration.CallStatus;
import com.qiaobei.hmp.modules.entity.Registration.QueueStatus;
import com.qiaobei.hmp.modules.entity.Registration.RegistrationTypeEnum;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Registration.class)
public abstract class Registration_ extends com.qiaobei.hmp.modules.entity.StatusEntity_ {

	public static volatile SingularAttribute<Registration, String> patientName;
	public static volatile SingularAttribute<Registration, RegistrationTypeEnum> haveBindingCard;
	public static volatile SingularAttribute<Registration, Long> appointPatientId;
	public static volatile SingularAttribute<Registration, Long> patientId;
	public static volatile SingularAttribute<Registration, Date> completeOn;
	public static volatile SingularAttribute<Registration, String> patientUid;
	public static volatile SingularAttribute<Registration, String> doctorDeptName;
	public static volatile SingularAttribute<Registration, Integer> sequence;
	public static volatile SingularAttribute<Registration, String> doctorName;
	public static volatile SingularAttribute<Registration, RegistrationTypeEnum> registrationType;
	public static volatile SingularAttribute<Registration, Long> doctorId;
	public static volatile SingularAttribute<Registration, QueueStatus> queueStatus;
	public static volatile SingularAttribute<Registration, CallStatus> callStatus;
	public static volatile SingularAttribute<Registration, String> noNumber;
	public static volatile SingularAttribute<Registration, Date> createOn;
	public static volatile SingularAttribute<Registration, RegistrationTypeEnum> haveSingIn;

}

