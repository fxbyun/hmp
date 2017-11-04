package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.MsgSendHistory.SendType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MsgSendHistory.class)
public abstract class MsgSendHistory_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<MsgSendHistory, Doctor> doctor;
	public static volatile SingularAttribute<MsgSendHistory, Integer> successSize;
	public static volatile SingularAttribute<MsgSendHistory, Integer> sendSize;
	public static volatile SingularAttribute<MsgSendHistory, SendType> msgType;
	public static volatile ListAttribute<MsgSendHistory, MsgSendHistoryDetail> msgSendHistoryDetail;
	public static volatile SingularAttribute<MsgSendHistory, String> context;
	public static volatile SingularAttribute<MsgSendHistory, Double> useMoney;
	public static volatile SingularAttribute<MsgSendHistory, String> title;
	public static volatile SingularAttribute<MsgSendHistory, Date> createDate;

}

