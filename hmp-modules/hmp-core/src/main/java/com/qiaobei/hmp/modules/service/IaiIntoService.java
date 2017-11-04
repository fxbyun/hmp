package com.qiaobei.hmp.modules.service;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.IaiInto;
import com.qiaobei.hmp.modules.entity.IaiIntoDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2016/9/23 0023.
 */
public interface IaiIntoService {
    Page<IaiInto> findIaiIntoPage(String medName, Pageable pageabl2e);

    Page<IaiInto> findIaiIntoPage(Doctor doctor,Pageable pageable);
    void save(IaiInto iaiInto);

    IaiInto findById(Long iaiIntoId);

    Page<IaiInto> findIaiIntoPage(Doctor doctor,String medName,Pageable pageable);

    IaiInto findByUuid(String uuid);

    void delIaiInto(Long id);

    Page<IaiInto> findIaiIntoPageByGoodsNo(Doctor doctor,String goodsNo,IaiInto.IaiIntoStatus status, IaiInto.IaiIntoType iaiIntoType,Pageable pageable);

    Page<IaiInto> findIaiIntoPageByGoodsNo(Doctor doctor,String goodsNo,IaiInto.IaiIntoType iaiIntoType,Pageable pageable);

    //找到医生的智能订单（类型：智能补货订单REPLENISH，保存状态）
    List<IaiInto> findByCreateByAndIaiIntoTypeAndStatus(Long createBy,IaiInto.IaiIntoType iaiIntoType,IaiInto.IaiIntoStatus iaiIntoStatus);

    Page<IaiInto> findByCreateByAndIaiIntoTypeAndStatus(Long createBy,IaiInto.IaiIntoType iaiIntoType,IaiInto.IaiIntoStatus iaiIntoStatus,Pageable pageable);

    long findLast(Long doctorId);
}
