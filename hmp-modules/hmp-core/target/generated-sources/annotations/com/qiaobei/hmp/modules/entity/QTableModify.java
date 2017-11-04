package com.qiaobei.hmp.modules.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTableModify is a Querydsl query type for TableModify
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTableModify extends EntityPathBase<TableModify> {

    private static final long serialVersionUID = 959965250L;

    public static final QTableModify tableModify = new QTableModify("tableModify");

    public final QIdEntity _super = new QIdEntity(this);

    public final StringPath columnName = createString("columnName");

    public final StringPath columnNameNewName = createString("columnNameNewName");

    public final StringPath columnType = createString("columnType");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.util.Date> createDate = createDateTime("createDate", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<TableModify.TableModyfiType> status = createEnum("status", TableModify.TableModyfiType.class);

    public final StringPath tableName = createString("tableName");

    public final EnumPath<TableModify.TableModyfiType> type = createEnum("type", TableModify.TableModyfiType.class);

    public QTableModify(String variable) {
        super(TableModify.class, forVariable(variable));
    }

    public QTableModify(Path<? extends TableModify> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTableModify(PathMetadata metadata) {
        super(TableModify.class, metadata);
    }

}

