package com.qiaobei.hmp.schedule;

import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.modules.support.DateUtils;
import com.qiaobei.hmp.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Component("appointProcess")
@Transactional
//@Controller
public class AppointProcess {

    @Resource
    private AppointConfigService appointConfigService;

    @Resource
    private AppointWeekConfigService appointWeekConfigService;

    @Resource
    private AppointListService appointListService;

    @Resource
    private AppointTimeConfigService appointTimeConfigService;

    @Resource
    private AppointPatientService appointPatientService;

    @Resource
    private DoctorService doctorService;

    // @RequestMapping(value = "/process", method = RequestMethod.GET)
    public void createAppointList() {
        List<Doctor> doctorList = doctorService.findALl();
        if (CollectionUtils.isEmpty(doctorList)) {
            throw new RuntimeException("数据库中没有任何医生资料");
        }
        //遍历数据中所有医生
        for (Doctor doctor : doctorList) {
            //获得该医生的预约配置
            AppointConfig appointConfig = appointConfigService.findByDoctor(doctor);
            //如果医生关闭了预约，则不产生预约号
            if (appointConfig == null || appointConfig.getOpenStatic() == AppointConfig.Static.Close) {
                continue;
            }
            //获得今天是星期几
            Date today = DateUtils.getDate();
            DateUtils.getWeekOfDate(today);
            //获得该医生的一周设置列表List
//            List<AppointWeekConfig> weekList = appointWeekConfigService.findByDoctor(doctor);
            //获得该从今天开始后，有预约勾选的三天（不足三天则选择下一周的星期）的时间段设置

            Map<Date, AppointWeekConfig> weekSelectMap = Maps.newHashMap();
            int count = 0;
            //今天的天数加1，然后判断该天是星期几，是否有被预约勾选中，有的话计数器加 1.加了三次后，跳出循环
            for (int i = 0; count < 3 && i <= 6; i++) {
                //加1后的日期
                Date day = DateUtils.getTomorrowDate(today, i);
                //加1后的是星期几
                String week = DateUtils.getWeekOfDateEn(day);
                //判断该周几是否被勾选
                AppointWeekConfig.Weekday weekdayEnum = AppointWeekConfig.Weekday.valueOf(week);
                AppointWeekConfig appointWeekConfig = appointWeekConfigService.findByDoctorAndWeekday(doctor, weekdayEnum);
                //如果该星期有被勾选，则加入到被选List中，还有一种是，重新勾选了被勾选中
                if (appointWeekConfig != null) {
                    weekSelectMap.put(day, appointWeekConfig);
                    //计数器加1
                    count++;
                }
            }

            //得到这个Map，这个Map应该只有三个元素，并且里面的有些是已经生产过预约号List的
            //这时候需要判断那些是生产过的，不能根据ProductStatic来判断，需要根据AppointList列表中存在该日期的List
            for (Map.Entry<Date, AppointWeekConfig> entry : weekSelectMap.entrySet()) {
                //如果这天没有勾选，那么也不会生产预约列表
                if (!entry.getValue().getOpenStatic().toString().equals("Open")) {
                    continue;
                }
                //根据日期查找
                List<AppointList> appointLists = appointListService.findByDate(entry.getKey(), doctor);
                if (CollectionUtils.isNotEmpty(appointLists)) {
                    continue;
                }
                //如果AppointList中没有，则是生产过新的预约
                //得到该天配置的时间段集合
                List<AppointTimeConfig> timeConfigList = appointTimeConfigService.findByAppointWeekConfig(entry.getValue());
                for (AppointTimeConfig time : timeConfigList) {
                    //由于AppointTimeConfig表中存时间格式：08:00:00,进行保存会报时间格式有问题
                    Date startTime = DateUtils.parseDate(time.getStartTime(), entry.getKey());
                    Date endTime = DateUtils.parseDate(time.getEndTime(), entry.getKey());
                    //根据该时间段，生成一系列列表
                    List<AppointList> tempList = appointListService.createAppointList(startTime, endTime, entry.getKey(), appointConfig.getPerMin(), appointConfig.getPersonNum(), doctor);
                    //如果时间设置有问题则tempList的集合为空
                    if (tempList.size() != 0) {
                        //批量存储
                        appointListService.saveList(tempList);
                    }
                }
                //根据日期查找刚才产生预约列表
                appointLists = appointListService.findByDate(entry.getKey(), doctor);

                //根据预约列表生产这一天的预约号
                for (AppointList appointList : appointLists) {
                    //根据设置判断预约号数量
                    int personNum = appointConfig.getPersonNum();
                    while (personNum > 0) {
                        //先保存基本信息
                        AppointPatient appointPatient = new AppointPatient();
                        appointPatient.setAppointList(appointList);
                        appointPatient.setAppointStatus(AppointPatient.Status.Undistributed);
                        appointPatient.setHasSendMessage(AppointPatient.SendMessage.Not);
                        //保存预约号信息
                        appointPatientService.saveAppointPatient(appointPatient);
                        personNum--;


                    }
                }

            }
        }


    }

}
