package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.AppointList;
import com.qiaobei.hmp.modules.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface AppointListService {
    public List<AppointList> findByDate(Date date, Doctor doctor);
    public List<AppointList> createAppointList(Date startTime, Date endTime, Date day, int perMin);
    public void saveList(List<AppointList> tempList);

    public void save(AppointList appointList);
    public Page<AppointList> findThreeDay(Pageable page, String day,Doctor doctor);

    public AppointList findById(Long listId);
}
