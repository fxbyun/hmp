package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.DataFile;

import java.util.List;

public interface DataFileService {

    /**
     * 按logicId和type查找
     */
    DataFile getDataFile(Long logicId, DataFile.Type type);

}
