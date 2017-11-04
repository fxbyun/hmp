package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 文件
 */
@Entity
@Table(name = "data_file")
public class DataFile extends IdEntity {

    public enum Type {
        License, Avatar, Advert, Adverting
    }

    @Column(name = "file_name", length = 50)
    private String fileName;
    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private Type type;
    //保存用户ID
    @Column(name = "logic_id")
    private Long logicId;
    @JsonIgnore
    @Lob
    @Column(name = "content")
    private byte[] content;

    public DataFile() {
    }

    public DataFile(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getLogicId() {
        return logicId;
    }

    public void setLogicId(Long logicId) {
        this.logicId = logicId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
