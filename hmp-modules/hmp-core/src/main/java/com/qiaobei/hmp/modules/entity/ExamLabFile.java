package com.qiaobei.hmp.modules.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/11 0011
 * Time 11:08
 */
@Entity
@Table(name = "exam_lab_file")
public class ExamLabFile extends IdEntity {
    private String fileName;
    private byte[] fileData;
    private EmrJClassAdviceDict emrJClassAdviceDict;
    private Exam_Lab_File_Type types;
    private Date createOn = new Date();

    @Column(name = "create_on")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Column(name = "types")
    @Enumerated(value = EnumType.STRING)
    public Exam_Lab_File_Type getTypes() {
        return types;
    }

    public void setTypes(Exam_Lab_File_Type types) {
        this.types = types;
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
    @Column(name = "file_data")
    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "emr_j_class_advice_dict_id")
    public EmrJClassAdviceDict getEmrJClassAdviceDict() {
        return emrJClassAdviceDict;
    }

    public void setEmrJClassAdviceDict(EmrJClassAdviceDict emrJClassAdviceDict) {
        this.emrJClassAdviceDict = emrJClassAdviceDict;
    }

    public enum Exam_Lab_File_Type {
        Jpg,
        Png,
        Pdf
    }
}
