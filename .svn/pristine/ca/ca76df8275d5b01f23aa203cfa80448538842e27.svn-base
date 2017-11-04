package com.qiaobei.hmp.service.impl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.AppointList;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.repository.AppointListDao;
import com.qiaobei.hmp.service.AppointListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/8/31 0031
 * Time 14:54
 */
@Service("appointListService")
@Transactional
public class AppointListServiceImpl implements AppointListService {

    @Resource
    private AppointListDao appointListDao;

    @Override
    public List<AppointList> findByDate(Date date, Doctor doctor) {

        return appointListDao.findByDate(date, doctor);
    }


    @Override
    public List<AppointList> createAppointList(Date startTime, Date endTime, Date day, Integer perMin, Integer peoNum, Doctor doctor) {
        if (null == peoNum) {
            peoNum = 3;
        }
        if (null == perMin) {
            perMin = 15;
        }
        List<AppointList> tempList = Lists.newArrayList();
        while (DateUtils.compareTime(startTime, endTime)) {
            AppointList appointList = new AppointList();
            appointList.setStartTime(startTime);
            //开始时间加上15分钟（perMin）
            startTime = DateUtils.addMinute(startTime, perMin);
            appointList.setEndTime(startTime);

            appointList.setDate(day);
            appointList.setPeopleNum(0);
            appointList.setConfigPeopleNum(peoNum);
            appointList.setRemainder(peoNum);
            appointList.setStatus(AppointList.Status.Valid);
            //设置医生信息
            appointList.setDoctor(doctor);

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
    public Page<AppointList> findThreeDay(Pageable page, String day, Doctor doctor) {
        Date dayTime;
        switch (day) {
            case "today":
                dayTime = DateUtils.getTomorrowDate(new Date(), 0);
                break;
            case "tomorrow":
                dayTime = DateUtils.getTomorrowDate(new Date(), 1);
                break;
            case "afterTomorrow":
                dayTime = DateUtils.getTomorrowDate(new Date(), 2);
                break;
            default:
                dayTime = DateUtils.getTomorrowDate(new Date(), 0);
                break;
        }
        return appointListDao.findByDate(page, dayTime, doctor);
    }

    @Override
    public void delAppointLists(List<AppointList> appointLists) {
        appointListDao.delete(appointLists);
    }

    @Override
    public List<AppointList> findThreeDayBefore() {
        return appointListDao.findThreeDayBefore();
    }

    @Override
    public AppointList findById(Long listId) {
        return appointListDao.findById(listId);
    }
}
