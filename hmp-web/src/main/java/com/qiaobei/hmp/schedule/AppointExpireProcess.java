package com.qiaobei.hmp.schedule;

import com.qiaobei.hmp.modules.entity.AppointExpire;
import com.qiaobei.hmp.modules.entity.AppointList;
import com.qiaobei.hmp.modules.entity.AppointPatient;
import com.qiaobei.hmp.modules.entity.AppointReward;
import com.qiaobei.hmp.modules.service.AppointExpireService;
import com.qiaobei.hmp.service.AppointListService;
import com.qiaobei.hmp.service.AppointPatientService;
import com.qiaobei.hmp.service.AppointRewardService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/10/10 0010
 * Time 15:04
 */
//@Component("appointExpireProcess")
@Controller
public class AppointExpireProcess {

    @Resource
    private AppointListService appointListService;

    @Resource
    private AppointRewardService appointRewardService;

    @Resource
    private AppointPatientService appointPatientService;

    @Resource
    private AppointExpireService appointExpireService;

    @RequestMapping(value = "/expire", method = RequestMethod.GET)
    public void saveAppointExpire() {
        //查找昨天的预约appoint,将数据搬到appoint_expire表中

        List<AppointReward> rewardList = appointRewardService.findYesterdayList();
        if (CollectionUtils.isNotEmpty(rewardList)) {
            for (AppointReward reward : rewardList) {
                AppointExpire appExpire = new AppointExpire(reward);
                appointExpireService.save(appExpire);
            }
        }

        //先删appointPatient,再删appointList
        List<AppointPatient> appointPatientList = appointPatientService.findThreeDayBefore();
        if (CollectionUtils.isNotEmpty(appointPatientList)) {
            //如果patient不为空，那么代表这个预约号被人定了，所以要先设为空，以免影响到reward
//            Iterator<AppointPatient> iter = appointPatientList.iterator();
//            while (iter.hasNext()){
//                AppointPatient appointPatient = iter.next();
//                if(appointPatient.getPatient()!=null){
//                    appointPatient.setPatient(null);
//                }
//                appointPatientService.saveAppointPatient(appointPatient);
//            }

            delAppointPatient(appointPatientList);
        }

        List<AppointList> appointLists = appointListService.findThreeDayBefore();
        appointListService.delAppointLists(appointLists);

    }

    /*删除appointPatient #doing*/
    private void delAppointPatient(List<AppointPatient> appointPatientList) {

        appointPatientService.delete(appointPatientList);
    }

}
