package com.qiaobei.hmp.modules.entity;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/11/3 0003
 * Time 17:34
 */
@MappedSuperclass
public abstract class CodeStatus extends IdEntity {
    public enum StatusStr {
        Normal,//正常
        Remove//已经删除
    }

    protected StatusStr status = StatusStr.Normal;


    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    public StatusStr getStatus() {
        return status;
    }

    public void setStatus(StatusStr status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodeStatus)) return false;
        if (!super.equals(o)) return false;
        CodeStatus that = (CodeStatus) o;
        return Objects.equal(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), status);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("status", status)
                .toString();
    }
}
