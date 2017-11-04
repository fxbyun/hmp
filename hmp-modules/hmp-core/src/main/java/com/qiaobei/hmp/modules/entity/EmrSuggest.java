package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 诊后建议
 */
@Entity
@Table(name = "emr_suggest")
public class EmrSuggest extends IdEntity {

    @JsonIgnore
    private Emr emr;
    private Long suggestId;
    private String suggestContent;

    public EmrSuggest() {
    }

    public EmrSuggest(Long id) {
        super(id);
    }

    @ManyToOne()
    @JoinColumn(name = "emr_id")
    public Emr getEmr() {
        return emr;
    }

    public void setEmr(Emr emr) {
        this.emr = emr;
    }

    @Column(name = "suggest_id")
    public Long getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(Long suggestId) {
        this.suggestId = suggestId;
    }

    @Column(name = "suggest_content", length = 200)
    public String getSuggestContent() {
        return suggestContent;
    }

    public void setSuggestContent(String suggestContent) {
        this.suggestContent = suggestContent;
    }

}
