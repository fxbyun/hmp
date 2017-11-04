package com.qiaobei.hmp.modules.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import org.springside.modules.utils.AnnotationUtil;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "javassistLazyInitializer", "revisionEntity", "handler"}, ignoreUnknown = true)
@MappedSuperclass
public abstract class IdEntity implements Serializable {
    private static final long serialVersionUID = 3330350039201346884L;

    protected Long id;

    public IdEntity() {
    }

    public IdEntity(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdEntity)) return false;
        IdEntity idEntity = (IdEntity) o;
        return Objects.equal(id, idEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * @param o 枚举变量
     * @return 返回MetaData的Value值
     */
    @Transient
    public String getMetaValue(Enum o) {
        return AnnotationUtil.getMetaValue(o);
    }
}
