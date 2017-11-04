package com.qiaobei.hmp.modules.entity;

import com.qiaobei.hmp.modules.entity.TableModify.TableModyfiType;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TableModify.class)
public abstract class TableModify_ extends com.qiaobei.hmp.modules.entity.IdEntity_ {

	public static volatile SingularAttribute<TableModify, String> columnNameNewName;
	public static volatile SingularAttribute<TableModify, String> columnType;
	public static volatile SingularAttribute<TableModify, String> createBy;
	public static volatile SingularAttribute<TableModify, TableModyfiType> type;
	public static volatile SingularAttribute<TableModify, TableModyfiType> status;
	public static volatile SingularAttribute<TableModify, String> tableName;
	public static volatile SingularAttribute<TableModify, Date> createDate;
	public static volatile SingularAttribute<TableModify, String> columnName;

}

