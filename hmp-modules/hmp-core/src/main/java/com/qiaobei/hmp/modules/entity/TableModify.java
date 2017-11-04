package com.qiaobei.hmp.modules.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/28 0028
 * Time 17:46
 */
@Entity
@Table(name = "table_modify")
public class TableModify extends IdEntity {
    private String tableName;
    private Date createDate;
    private String createBy;
    private TableModyfiType type;
    private String columnName;
    private String columnNameNewName;
    private String columnType;
    private TableModyfiType status;

    public enum TableModyfiType {
        Add_Column,
        Edit_Column,
        Delete_Column,
        Add_Table,//新增表
        Edit_Table,
        Delete_Table,
        Have_Be_Chang_On_Server,
        Have_NOT_Be_Chang_On_Server
    }

    public TableModyfiType getStatus() {
        return status;
    }

    public void setStatus(TableModyfiType status) {
        this.status = status;
    }

    public String getColumnNameNewName() {
        return columnNameNewName;
    }

    public void setColumnNameNewName(String columnNameNewName) {
        this.columnNameNewName = columnNameNewName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


    @Enumerated(EnumType.STRING)
    public TableModyfiType getType() {
        return type;
    }

    public void setType(TableModyfiType type) {
        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
