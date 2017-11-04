package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.MsgSendHistoryDetail.SendStatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MsgSendHistoryDetail.class)
public abstract class MsgSendHistoryDetail_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<MsgSendHistoryDetail, MsgSendHistory> msgSendHistory;
	public static volatile SingularAttribute<MsgSendHistoryDetail, Patient> patient;
	public static volatile SingularAttribute<MsgSendHistoryDetail, SendStatus> sendStatus;

}

