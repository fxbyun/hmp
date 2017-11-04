package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.TableModify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/28 0028
 * Time 17:58
 */
public interface TableModifyService {
    Page<TableModify> findAll(String tableName, PageRequest pageRequest);

    TableModify findById(Long id);

    void save(TableModify tableModify);
}
