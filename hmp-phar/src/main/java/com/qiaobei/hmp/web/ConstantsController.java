package com.qiaobei.hmp.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qiaobei.hmp.modules.entity.Gender;
import com.qiaobei.hmp.modules.entity.Medicine;
import com.qiaobei.hmp.modules.entity.VitalSign;

import java.util.*;

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
    protected static final Map<String, String> GROUP_ARRYS = Maps.newHashMap();
    protected static final List<String> XW_ARRYS = Lists.newArrayList();


    protected static final List<String> MEDICINE_USE_MODE_LIST = Lists.newArrayList();
    protected static final List<String> MEDICINE_USING_TIME_LIST = Lists.newArrayList();
    protected static final List<String> MEDICINE_USE_TIMES_LIST = Lists.newArrayList();
    protected static final List<String> WESTERN_MEDICINE_CATE_LIST = Lists.newArrayList();
    protected static final List<String> CHINESE_MEDICINE_CATE_LIST = Lists.newArrayList();

    protected static final List<String> MEDICINE_USE_MODE__NEED_ALONE_PRINT_LIST = Lists.newArrayList();

    //中医理疗的单位
    protected static final EnumMap<Medicine.Unit, String> THERAPY_UNITS = Maps.newEnumMap(Medicine.Unit.class);




    static {
        GENDER_MAP.put(Gender.Male, "男");
        GENDER_MAP.put(Gender.Female, "女");
        GENDER_MAP.put(Gender.All, "全部");

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
        VITAL_SIGN_LABELS.put(VitalSign.Type.Bw, "体重");
        VITAL_SIGN_LABELS.put(VitalSign.Type.Ns, "尿酸");

        VITAL_SIGN_UNITS.put(VitalSign.Type.Lbp, "");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Hbp, "mmHg");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Glu, "mg/ml");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Hr, "次/分");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Br, "次/分");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Bt, "℃");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Bw, "kg");
        VITAL_SIGN_UNITS.put(VitalSign.Type.Ns, "umol/L");

        GROUP_ARRYS.put("1", "一");
        GROUP_ARRYS.put("2", "二");
        GROUP_ARRYS.put("3", "三");
        GROUP_ARRYS.put("4", "四");
        GROUP_ARRYS.put("5", "五");
        GROUP_ARRYS.put("6", "六");
        GROUP_ARRYS.put("7", "七");
        GROUP_ARRYS.put("8", "八");
        GROUP_ARRYS.put("9", "九");


        List<VitalSign> list = Lists.newArrayList();
        list.add(new VitalSign(VitalSign.Type.Lbp));
        list.add(new VitalSign(VitalSign.Type.Hbp));
        list.add(new VitalSign(VitalSign.Type.Glu));
        list.add(new VitalSign(VitalSign.Type.Hr));
        list.add(new VitalSign(VitalSign.Type.Br));
        list.add(new VitalSign(VitalSign.Type.Bt));
        list.add(new VitalSign(VitalSign.Type.Bw));
        list.add(new VitalSign(VitalSign.Type.Ns));
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

        /**
         * UPDATE BY  ZW 2016年5月4日 17:19:43
         */
        List<String> netAlonePrint = Lists.newArrayList();
        netAlonePrint.add("煎服");
        netAlonePrint.add("注射/滴注");
        netAlonePrint.add("灌肠");
        netAlonePrint.add("雾化");
        netAlonePrint.add("贴敷");

        MEDICINE_USE_MODE__NEED_ALONE_PRINT_LIST.addAll(Collections.unmodifiableList(netAlonePrint));

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

        List<String> westernCateList = Lists.newArrayList();
        westernCateList.add("儿童用药");
        westernCateList.add("抗菌消炎类");
        westernCateList.add("清热解毒类");
        westernCateList.add("解热镇痛类");
        westernCateList.add("呼吸系统类");
        westernCateList.add("呼吸系统类");
        westernCateList.add("抗感冒类");
        westernCateList.add("消化系统");
        westernCateList.add("泌尿系统类");
        westernCateList.add("妇科用药");
        westernCateList.add("心脑血管类");
        westernCateList.add("内分泌类");
        westernCateList.add("外用药");
        westernCateList.add("抗过敏类");
        westernCateList.add("其他");
        WESTERN_MEDICINE_CATE_LIST.addAll(Collections.unmodifiableList(westernCateList));

        List<String> chineseCateList = Lists.newArrayList();
        chineseCateList.add("解表药");
        chineseCateList.add("清热药");
        chineseCateList.add("化痰止咳平喘药");
        chineseCateList.add("平肝熄风药");
        chineseCateList.add("祛风湿药");
        chineseCateList.add("活血化瘀药");
        chineseCateList.add("行气药");
        chineseCateList.add("止血药");
        chineseCateList.add("芳香化湿药");
        chineseCateList.add("消食药");
        chineseCateList.add("利水渗湿药");
        chineseCateList.add("安神药");
        chineseCateList.add("补虚药");
        chineseCateList.add("泻下药");
        chineseCateList.add("其他");
        CHINESE_MEDICINE_CATE_LIST.addAll(Collections.unmodifiableList(chineseCateList));


        XW_ARRYS.addAll(Arrays.asList(getXw()));

        //中医理疗
        THERAPY_UNITS.put(Medicine.Unit.needle, "针");
        THERAPY_UNITS.put(Medicine.Unit.acupoint, "穴位");
        THERAPY_UNITS.put(Medicine.Unit.part, "部位");
        THERAPY_UNITS.put(Medicine.Unit.ge, "个");
        THERAPY_UNITS.put(Medicine.Unit.skill, "手法");
        THERAPY_UNITS.put(Medicine.Unit.therapyUnit, "单位");
    }

    private static String[] getXw() {
        String s[] = {"请选择",
                "神阙穴",
                "涌泉穴",
                "膻中穴",
                "肺俞穴",
                "天突穴",
                "列缺穴",
                "风府穴",
                "大椎穴",
                "陶道穴",
                "",
                "玉枕穴",
                "大杼穴",
                "风门穴",
                "风池穴",
                "当阳穴",
                "颞颥穴",
                "太阳穴",
                "百虫窝穴",
                "京骨穴",
                "天柱穴",
                "小海穴",
                "少海穴",
                "通里穴",
                "阴郄穴",
                "神门穴",
                "少府穴",
                "曲泽穴",
                "筑宾穴",
                "上星穴",
                "尺泽穴",
                "孔最穴",
                "鱼际穴",
                "虎口穴",
                "前谷穴",
                "百劳穴",
                "灸痨穴",
                "身柱穴",
                "灵台穴",
                "阳白穴",
                "头临泣穴",
                "五处穴",
                "颔厌穴",
                "悬颅穴",
                "悬厘穴",
                "曲鬓穴",
                "浮白穴",
                "脑空穴",
                "头窍阴穴",
                "完骨穴",
                "浊浴穴",
                "五枢穴",
                "足五里穴",
                "胆囊穴",
                "光明穴",
                "中封穴",
                "足临泣穴",
                "地五会穴",
                "侠溪穴",
                "曲池穴",
                "手三里穴",
                "下廉穴",
                "合谷穴",
                "三间穴",
                "二白穴",
                "下极俞穴",
                "下腰穴",
                "尾穷骨穴",
                "长强穴",
                "阳纲穴",
                "肓门穴",
                "小肠俞穴",
                "阑尾穴",
                "下巨虚穴",
                "解溪穴",
                "冲阳穴",
                "内庭穴",
                "瘈脉穴",
                "消泺穴",
                "天井穴",
                "支沟穴",
                "外关穴",
                "阳池穴",
                "渊腋穴",
                "夹承浆穴",
                "地合穴",
                "龈交穴",
                "颊里穴",
                "角孙穴",
                "耳尖穴",
                "颧髎穴",
                "肘尖穴",
                "温溜穴",
                "偏历穴",
                "阳溪穴",
                "二间穴",
                "臂间穴",
                "八邪穴",
                "大指甲根穴",
                "乳上穴",
                "腰俞穴",
                "昆仑穴",
                "外踝尖穴",
                "八风穴",
                "内踝尖穴",
                "气舍穴",
                "水突穴",
                "天府穴",
                "小指尖穴",
                "璇玑穴",
                "华盖穴",
                "紫宫穴",
                "玉堂穴",
                "肋头穴",
                "胸堂穴",
                "俞府穴",
                "彧中穴",
                "神藏穴",
                "灵墟穴",
                "神封穴",
                "气户穴",
                "库房穴",
                "屋翳穴",
                "膺窗穴",
                "云门穴",
                "中府穴",
                "周荣穴",
                "天溪穴",
                "崇骨穴",
                "灸哮穴",
                "定喘穴",
                "魄户穴",
                "譩譆穴",
                "丰隆穴",
                "上脘穴",
                "中脘穴",
                "建里穴",
                "下脘穴",
                "脐中四边穴",
                "幽门穴",
                "不容穴",
                "承满穴",
                "梁门穴",
                "关门穴",
                "太乙穴",
                "长谷穴",
                "食窦穴",
                "腹哀穴",
                "接脊穴",
                "四缝穴",
                "百会穴",
                "石关穴",
                "商曲穴",
                "气海穴",
                "石门穴",
                "关元穴",
                "胃上穴",
                "大巨穴",
                "提托穴",
                "脊中穴",
                "命门穴",
                "腰阳关穴",
                "脾俞穴",
                "肾俞穴",
                "中膂俞穴",
                "意舍穴",
                "胃仓穴",
                "志室穴",
                "京门穴",
                "环跳穴",
                "足三里穴",
                "仆参穴",
                "束骨穴",
                "太溪穴",
                "商丘穴",
                "公孙穴",
                "太白穴",
                "膏肓穴",
                "腰眼穴",
                "小儿龟胸穴",
                "脐上脐下穴",
                "大赫穴",
                "横骨穴",
                "照海穴",
                "然谷穴",
                "龙颔穴",
                "三角灸穴",
                "大横穴",
                "腹结穴",
                "胃俞穴",
                "气海俞穴",
                "大肠俞穴",
                "关元俞穴",
                "会阳穴",
                "关仪穴",
                "地机穴",
                "漏谷穴",
                "大都穴",
                "鼻交頞中穴",
                "发际穴",
                "神庭穴",
                "前顶穴",
                "本神穴",
                "率骨穴",
                "天冲穴",
                "后顶穴",
                "强间穴",
                "滑肉门穴",
                "筋缩穴",
                "太冲穴",
                "行间穴",
                "足心穴",
                "里内庭穴",
                "天容穴",
                "人迎穴",
                "缺盆穴",
                "极泉穴",
                "天泉穴",
                "侠白穴",
                "青灵穴",
                "经渠穴",
                "中泉穴",
                "中魁穴",
                "中庭穴",
                "步廊穴",
                "胸乡穴",
                "辄筋穴",
                "天池穴",
                "乳根穴",
                "期门穴",
                "日月穴",
                "大包穴",
                "鸠尾穴",
                "巨阙穴",
                "腹通谷穴",
                "阴都穴",
                "肓俞穴",
                "天枢穴",
                "外陵穴",
                "章门穴",
                "府舍穴",
                "气冲穴",
                "羊矢穴",
                "急脉穴",
                "至阳穴",
                "中枢穴",
                "血压点穴",
                "厥阴俞穴",
                "督俞穴",
                "胃管下俞穴",
                "肝俞穴",
                "胆俞穴",
                "肘椎穴",
                "膈关穴",
                "魂门穴",
                "痞根穴",
                "后腋穴",
                "上巨虚穴",
                "阳陵泉穴",
                "外丘穴",
                "中都穴",
                "太渊穴",
                "养老穴",
                "阴交穴",
                "膈俞穴",
                "竹杖穴",
                "阳刚穴",
                "闾上穴",
                "血海穴",
                "三阴交穴",
                "合阳穴",
                "中注穴",
                "经中穴",
                "四满穴",
                "气穴",
                "气门穴",
                "绝孕穴",
                "维胞穴",
                "归来穴",
                "子宫穴",
                "冲门穴",
                "带脉穴",
                "维道穴",
                "上髎穴",
                "次髎穴",
                "中髎穴",
                "下髎穴",
                "白环俞穴",
                "阴廉穴",
                "阴包穴",
                "曲泉穴",
                "阴谷穴",
                "蠡沟穴",
                "交信穴",
                "营池穴",
                "水泉穴",
                "独阴穴",
                "水分穴",
                "利尿穴",
                "中极穴",
                "水道穴",
                "曲骨穴",
                "三焦俞穴",
                "胞肓穴",
                "膀胱俞穴",
                "淋泉穴",
                "委阳穴",
                "箕门穴",
                "阴陵泉穴",
                "复溜穴",
                "大钟穴",
                "陷谷穴",
                "安眠穴",
                "伴星穴",
                "燕口穴",
                "支正穴",
                "阳谷穴",
                "手逆注穴",
                "郄门穴",
                "间使穴",
                "内关穴",
                "灵道穴",
                "大陵穴",
                "臣觉穴",
                "巨阙俞穴",
                "神道穴",
                "神堂穴",
                "心俞穴",
                "腰奇穴",
                "女膝穴",
                "足通谷穴",
                "水沟穴",
                "兑端穴",
                "承浆穴",
                "悬命穴",
                "内迎香穴",
                "四神聪穴",
                "劳宫穴",
                "后溪穴",
                "少泽穴",
                "少冲穴",
                "关冲穴",
                "中冲穴",
                "商阳穴",
                "少商穴",
                "十宣穴",
                "十王穴",
                "夺命穴",
                "乳中穴",
                "会阴穴",
                "委中穴",
                "金门穴",
                "隐白穴",
                "大敦穴",
                "厉兑穴",
                "足窍阴穴",
                "至阴穴",
                "气端穴",
                "利目穴",
                "睛明穴",
                "攒竹穴",
                "瞳子髎穴",
                "球后穴",
                "承泣穴",
                "四白穴",
                "鱼腰穴",
                "上明穴",
                "丝竹空穴",
                "睛中穴",
                "眉冲穴",
                "承光穴",
                "目窗穴",
                "头维穴",
                "翳明穴",
                "天牖穴",
                "拳尖穴",
                "大骨空穴",
                "凤眼穴",
                "利鼻穴",
                "迎香穴",
                "上迎香穴",
                "禾髎穴",
                "素髎穴",
                "囟会穴",
                "通天穴",
                "承灵穴",
                "散笑穴",
                "鼻流穴",
                "利耳穴",
                "耳门穴",
                "听宫穴",
                "颅息穴",
                "听会穴",
                "会宗穴",
                "利口舌咽喉穴",
                "正营穴",
                "脑户穴",
                "哑门穴",
                "颊车穴",
                "大迎穴",
                "上廉泉穴",
                "廉泉穴",
                "洪音穴",
                "扶突穴",
                "天鼎穴",
                "唇里穴",
                "聚泉穴",
                "海泉穴",
                "金津穴",
                "玉液穴",
                "龙玄穴",
                "通利诸窍穴",
                "巨髎穴",
                "印堂穴",
                "曲差穴",
                "上关穴",
                "耳和髎穴",
                "络却穴",
                "下关穴",
                "翳风穴",
                "天窗穴",
                "四渎穴",
                "三阳络穴",
                "中渚穴",
                "液门穴",
                "小骨空穴",
                "肩髎穴",
                "臂中穴",
                "伏兔穴",
                "阴市穴",
                "髋骨穴",
                "梁丘穴",
                "鹤顶穴",
                "膝眼穴",
                "犊鼻穴",
                "条口穴",
                "陵后穴",
                "膝关穴",
                "牵正穴",
                "地仓穴",
                "肩髎穴",
                "肩贞穴",
                "臑会穴",
                "臂臑穴",
                "手五里穴",
                "肘髎穴",
                "上廉穴",
                "清冷渊穴",
                "手踝穴",
                "腕骨穴",
                "肩前穴",
                "泽前穴",
                "腰痛点穴",
                "威灵穴",
                "精灵穴",
                "落枕穴",
                "五虎穴",
                "新设穴",
                "颈臂穴",
                "肩井穴",
                "天髎穴",
                "巨骨穴",
                "秉风穴",
                "曲垣穴",
                "天宗穴",
                "肩中俞穴",
                "肩外俞穴",
                "附分穴",
                "悬枢穴",
                "肩头穴",
                "臑俞穴",
                "夹脊穴",
                "新建穴",
                "居髎穴",
                "十七椎穴",
                "秩边穴",
                "髀关穴",
                "拇趾里横纹穴",
                "风市穴",
                "中渎穴",
                "膝阳关穴",
                "阳交穴",
                "阳辅穴",
                "悬钟穴",
                "丘墟穴",
                "承扶穴",
                "殷门穴",
                "浮郄穴",
                "膝旁穴",
                "承筋穴",
                "承山穴",
                "飞扬穴",
                "跗阳穴",
                "申脉穴",
                "阿是穴"};
        return s;
    }
}
