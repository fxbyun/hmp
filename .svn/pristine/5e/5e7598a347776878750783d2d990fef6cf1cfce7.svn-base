package com.qiaobei.hmp.modules.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.TableModify;
import com.qiaobei.hmp.modules.entity.TableModify_;
import com.qiaobei.hmp.modules.repository.TableModifyDao;
import com.qiaobei.hmp.modules.service.TableModifyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/28 0028
 * Time 17:58
 */
@Service("tableModifyService")
@Transactional
public class TableModifyServiceImpl implements TableModifyService {
    @Resource
    private TableModifyDao tableModifyDao;

    @Override
    public Page<TableModify> findAll(String tableName, PageRequest pageRequest) {
        return tableModifyDao.findAll((root, query, cb) -> {
            List<Predicate> predicateList = Lists.newArrayList();
            if (StringUtils.isNotEmpty(tableName)) {
                predicateList.add(
                        cb.like(
                                root.get(TableModify_.tableName),
                                "%" + tableName + "%"
                        )
                );
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageRequest);
    }

    @Override
    public TableModify findById(Long id) {
        return tableModifyDao.findOne(id);
    }

    @Override
    public void save(TableModify tableModify) {
        tableModifyDao.save(tableModify);
    }
}
