package com.qiaobei.hmp.modules.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MsgRechargeDetail.class)
public abstract class MsgRechargeDetail_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<MsgRechargeDetail, Doctor> doctor;
	public static volatile SingularAttribute<MsgRechargeDetail, Date> updateDate;
	public static volatile SingularAttribute<MsgRechargeDetail, String> wxInfoBack;
	public static volatile SingularAttribute<MsgRechargeDetail, Double> money;
	public static volatile SingularAttribute<MsgRechargeDetail, Double> addMoney;
	public static volatile SingularAttribute<MsgRechargeDetail, String> rechargeWay;
	public static volatile SingularAttribute<MsgRechargeDetail, String> wxInfoSend;
	public static volatile SingularAttribute<MsgRechargeDetail, Double> afterMoney;
	public static volatile SingularAttribute<MsgRechargeDetail, String> reMark;
	public static volatile SingularAttribute<MsgRechargeDetail, PayType> havePay;
	public static volatile SingularAttribute<MsgRechargeDetail, String> rechargeNum;
	public static volatile SingularAttribute<MsgRechargeDetail, Date> createDate;

}

