package com.qiaobei.hmp.modules.repository;

import com.qiaobei.hmp.modules.entity.TableModify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public interface TableModifyDao extends JpaRepository<TableModify, Long>, JpaSpecificationExecutor<TableModify> {

}
