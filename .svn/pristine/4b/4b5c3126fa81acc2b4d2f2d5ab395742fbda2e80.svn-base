package com.qiaobei.hmp.service;


import com.qiaobei.hmp.modules.entity.DataFile;
import com.qiaobei.hmp.modules.entity.User;

import java.util.List;

public interface DataFileService {

    /**
     * 按logicId和type查找
     */
    DataFile getDataFile(Long logicId, DataFile.Type type);

    byte[] getImagByUrl(String url);

    List<DataFile> findSysCenterImg(User user);
}
