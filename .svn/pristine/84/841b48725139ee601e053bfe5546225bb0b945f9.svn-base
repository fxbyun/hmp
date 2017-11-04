package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qiaobei.hmp.modules.support.OrdinalEnum;
import org.springside.modules.utils.MetaData;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/19 0019
 * Time 16:14
 */
@Entity
@Table(name = "emr_file")
public class EmrFile extends IdEntity {
    private Emr emr;
    private String fileName;
    private Date createOn;
    private Doctor doctor;
    private EmrFileType fileType;

    private byte[] content;

    @JsonIgnore
    @Lob
    @Column(name = "content")
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Column(name = "file_type")
    @Enumerated(value = EnumType.STRING)
    public EmrFileType getFileType() {
        return fileType;
    }

    public void setFileType(EmrFileType fileType) {
        this.fileType = fileType;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "emr_id")
    public Emr getEmr() {
        return emr;
    }

    public void setEmr(Emr emr) {
        this.emr = emr;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "doctor_id")
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    @Basic
    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "create_on")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }


    public enum EmrFileType implements OrdinalEnum {
        @MetaData(value = "西药")
        Western {
            @Override
            public Integer getValue() {
                return 0;
            }

            @Override
            public String getName() {
                return "西药";
            }
        },
        @MetaData(value = "中药")
        Chinese {
            @Override
            public Integer getValue() {
                return 1;
            }

            @Override
            public String getName() {
                return "中药";
            }
        },
        @MetaData(value = "中医理疗")
        ChineseTherapy {
            @Override
            public Integer getValue() {
                return 2;
            }

            @Override
            public String getName() {
                return "中医理疗";
            }
        },
        @MetaData(value = "检验")
        JianYan {
            @Override
            public Integer getValue() {
                return 3;
            }

            @Override
            public String getName() {
                return "检验";
            }
        },
        @MetaData(value = "检查")
        JianCha {
            @Override
            public Integer getValue() {
                return 4;
            }

            @Override
            public String getName() {
                return "检查";
            }
        }
    }
}
