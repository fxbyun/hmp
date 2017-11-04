package com.qiaobei.hmp.modules.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "suggest")
@DynamicInsert()
@DynamicUpdate()
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Suggest extends IdEntity {

    private String content;
    private String createBy;
    private Long createById;
    private Date createOn;
    private Long tagId;
    private String tagName;

    public Suggest() {
    }

    public Suggest(Long id) {
        super(id);
    }


    @Column(name = "content", length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "create_by", length = 20)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "create_by_id")
    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", length = 6)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "tag_id")
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Column(name = "tag_name")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}