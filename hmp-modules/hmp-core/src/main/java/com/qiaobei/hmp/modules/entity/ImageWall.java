package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * 形象墙
 */
@Entity
@Table(name = "image_wall")
public class ImageWall extends IdEntity {

    //是否是第一张封面
    public enum ImageLevel {
        Cover,NotCover
    }

    @Column(name = "file_name")
    private String fileName;

    @JsonIgnore
    @Lob
    @Column(name = "content")
    private byte[] content;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "outpatient_service")
    private String outpatientService;

    @Column(name = "level")
    @Enumerated(value = EnumType.ORDINAL)
    private ImageLevel level;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }


    public String getOutpatientService() {
        return outpatientService;
    }

    public void setOutpatientService(String outpatientService) {
        this.outpatientService = outpatientService;
    }

    public ImageLevel getLevel() {
        return level;
    }

    public void setLevel(ImageLevel level) {
        this.level = level;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
