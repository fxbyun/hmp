package com.qiaobei.hmp.modules.entity;

import com.google.common.collect.Lists;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "notice")
@DynamicInsert()
@DynamicUpdate()
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Notice extends StatusEntity {

    private String subject;
    private String content;
    private Type type;
    private String createBy;
    private Long createById;
    private Date createOn;
    private List<NoticeItem> noticeItemList = Lists.newArrayList();
    public Notice() {
    }

    public Notice(Long id) {
        super(id);
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "notice")
    public List<NoticeItem> getNoticeItemList() {
        return noticeItemList;
    }

    public void setNoticeItemList(List<NoticeItem> noticeItemList) {
        this.noticeItemList = noticeItemList;
    }

    @Column(name = "subject", nullable = false, length = 100)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public enum Type {
        System, User,
    }
}