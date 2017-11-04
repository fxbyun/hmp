package com.qiaobei.hmp.repository;


import com.qiaobei.hmp.modules.entity.DataFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DataFileDao extends JpaRepository<DataFile, Long>, JpaSpecificationExecutor<DataFile> {

    DataFile findByLogicIdAndType(Long logicId, DataFile.Type type);

}

