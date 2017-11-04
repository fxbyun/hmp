package com.qiaobei.hmp.service.impl;

import com.qiaobei.hmp.modules.entity.AppointList;
import com.qiaobei.hmp.modules.entity.AppointTimeConfig;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.repository.AppointListDao;
import com.qiaobei.hmp.service.AppointListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
@Service("appointListService")
@Transactional
public class AppointListServiceImpl implements AppointListService {

    @Resource
    private AppointListDao appointListDao;

    @Override
    public List<AppointList> findByDate(Date date, Doctor doctor) {

        return appointListDao.findByDate(date,doctor);
    }


    @Override
    public List<AppointList> createAppointList(Date startTime, Date endTime, Date day, int perMin) {
        List<AppointList> tempList = new ArrayList();
        while (DateUtils.compareTime(startTime,endTime)){
            AppointList appointList=new AppointList();
            appointList.setStartTime(startTime);
            //开始时间加上15分钟（perMin）
            startTime=DateUtils.addMinute(startTime,perMin);
            appointList.setEndTime(startTime);

            appointList.setDate(day);
            appointList.setPeopleNum(0);
            appointList.setStatus(AppointList.Status.Valid);

            tempList.add(appointList);
        }
        return tempList;
    }

    @Override
    public void saveList(List<AppointList> tempList) {
        appointListDao.save(tempList);
    }

    @Override
    public void save(AppointList appointList) {
        appointListDao.save(appointList);
    }

    @Override
    public Page<AppointList> findThreeDay(Pageable page , String day,Doctor doctor) {
        Date dayTime = null;
        switch (day){
            case "today":
                dayTime = DateUtils.getTomorrowDate(new Date(),0);
                break;
            case "tomorrow":
                dayTime = DateUtils.getTomorrowDate(new Date(),1);
                break;
            case "afterTomorrow":
                dayTime = DateUtils.getTomorrowDate(new Date(),2);
                break;
            default:
                dayTime = DateUtils.getTomorrowDate(new Date(),0);
                break;
        }
        return appointListDao.findByDate(page,dayTime,doctor);
    }

    @Override
    public AppointList findById(Long listId) {
        return appointListDao.findById(listId);
    }
}
