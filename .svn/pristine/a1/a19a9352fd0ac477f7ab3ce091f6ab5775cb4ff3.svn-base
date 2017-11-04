package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.VitalSign;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by iceyanbin on 2015-12-07.
 */
public class ConstantsController extends BaseController {

    protected static final List<VitalSign> VITAL_SIGN_LIST = Lists.newArrayList();
    protected static final EnumMap<Gender, String> GENDER_MAP = Maps.newEnumMap(Gender.class);
    protected static final EnumMap<Medicine.Type, String> MEDICINE_TYPES = Maps.newEnumMap(Medicine.Type.class);
    protected static final EnumMap<Medicine.Unit, String> MEDICINE_UNITS = Maps.newEnumMap(Medicine.Unit.class);
    protected static final EnumMap<VitalSign.Type, String> VITAL_SIGN_LABELS = Maps.newEnumMap(VitalSign.Type.class);
    protected static final EnumMap<VitalSign.Type, String> VITAL_SIGN_UNITS = Maps.newEnumMap(VitalSign.Type.class);


    protected static final List<String> MEDICINE_USE_MODE_LIST = Lists.newArrayList();
    protected static final List<String> MEDICINE_USING_TIME_LIST = Lists.newArrayList();
    protected static final List<String> MEDICINE_USE_TIMES_LIST = Lists.newArrayList();

    static {
        GENDER_MAP.put(Gender.Male, "男");
        GENDER_MAP.put(Gender.Female, "女");

        MEDICINE_TYPES.put(Medicine.Type.Western, "西药及中成药");
        MEDICINE_TYPES.put(Medicine.Type.Chinese, "中草药");

        MEDICINE_UNITS.put(Medicine.Unit.bxs, "盒");
        MEDICINE_UNITS.put(Medicine.Unit.btl, "瓶");
        MEDICINE_UNITS.put(Medicine.Unit.pkg, "包/排");
        MEDICINE_UNITS.put(Medicine.Unit.grs, "粒/片");
        MEDICINE_UNITS.put(Medicine.Unit.pcs, "支");
        MEDICINE_UNITS.put(Medicine.Unit.g, "g");
        MEDICINE_UNITS.put(Medicine.Unit.mg, "mg");
        MEDICINE_UNITS.put(Medicine.Unit.ml, "ml");
        MEDICINE_UNITS.put(Medicine.Unit.oth, "单位");

        VITAL_SIGN_LABELS.put(VitalSign.Type.Lbp, "血压");
        VITAL_SIGN_LABELS.put(VitalSign.Type.Hbp, "/");
        VITAL_SIGN_LABELS.put(VitalSign.Type.Glu, "血糖");
        VITAL_SIGN_LABELS.put(VitalSign.Type.Hr, "心率");
        VITAL_SIGN_LABELS.put(VitalSign.Type.Br, "呼吸");
        VITAL_SIGN_LABELS.put(VitalSign.Type.Bt, "体温");

        VITAL_SIGN_UNITS.put(VitalSign.Type.Lbp, "");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Hbp, "mmHg");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Glu, "mg/ml");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Hr, "次/分");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Br, "次/分");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Bt, "℃");

        List<VitalSign> list = Lists.newArrayList();
        list.add(new VitalSign(VitalSign.Type.Lbp));
        list.add(new VitalSign(VitalSign.Type.Hbp));
        list.add(new VitalSign(VitalSign.Type.Glu));
        list.add(new VitalSign(VitalSign.Type.Hr));
        list.add(new VitalSign(VitalSign.Type.Br));
        list.add(new VitalSign(VitalSign.Type.Bt));
        VITAL_SIGN_LIST.addAll(Collections.unmodifiableList(list));

        List<String> useModes = Lists.newArrayList();
        useModes.add("口服");
        useModes.add("含服");
        useModes.add("冲服");
        useModes.add("嚼服");
        useModes.add("煎服");
        useModes.add("注射");
        useModes.add("滴注");
        useModes.add("雾化");
        useModes.add("贴敷");
        useModes.add("针灸");
        useModes.add("拔罐");
        useModes.add("按摩");
        useModes.add("红外照射");
        useModes.add("冲洗");
        useModes.add("灌肠");
        useModes.add("研末");
        useModes.add("外用");
        useModes.add("蓝色疗法");
        MEDICINE_USE_MODE_LIST.addAll(Collections.unmodifiableList(useModes));

        List<String> usingTime = Lists.newArrayList();
        usingTime.add("饭后服");
        usingTime.add("饭前服");
        usingTime.add("睡前服");
        usingTime.add("每晚服");
        usingTime.add("上午服");
        usingTime.add("下午服");
        usingTime.add("需要时使用");
        usingTime.add("立即");
        MEDICINE_USING_TIME_LIST.addAll(Collections.unmodifiableList(usingTime));

        List<String> useTimes = Lists.newArrayList();
        useTimes.add("每日1次");
        useTimes.add("每日2次");
        useTimes.add("每日3次");
        useTimes.add("每日4次");
        useTimes.add("两日1次");
        useTimes.add("不适用");
        MEDICINE_USE_TIMES_LIST.addAll(Collections.unmodifiableList(useTimes));


    }
}
