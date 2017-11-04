package com.qiaobei.hmp.test;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.*;
import com.qiaobei.hmp.service.AdvertingService;
import com.qiaobei.hmp.service.CompanyService;
import com.qiaobei.hmp.service.MedicinePrivateService;
import com.qiaobei.hmp.service.MedicineService;
import com.qiaobei.hmp.test.dataImpl.MySqlutil;
import com.qiaobei.hmp.test.dataImpl.Pinyin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

//import com.qiaobei.hmp.test.dataImpl.Pinyin;

/**
 * Created by 连晋 on 2016/9/9 0009. 11:11
 * By IDEA 2016.2.3 汉化: www.java.sx
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-junit.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
public class AutoRemoveAdvingJunit {
    @Autowired
    private AdvertingService advertingService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private MedicineService medicineService;
    @Resource
    private MedicinePrivateService medicinePrivateService;

    @Test
    public void test() {
        List<Adverting> advertingList = advertingService.findAdvingListNeedDel();
        advertingList.forEach(adverting -> {
            if (adverting.getIndate() != 0 && adverting.getValidityDay() <= 0) {
                advertingService.deleteAdvert(adverting);
            }
        });
    }

    @Test
    public void insertMed() {
        Connection connection = MySqlutil.getConnect();
        Statement statement;
        List<OldMedInfo> beanTmpList = Lists.newArrayList();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * FROM tmp n WHERE n.type = 'Western'");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("type"));
                System.out.println(resultSet.getString("name"));
                beanTmpList.add(
                        new OldMedInfo(
                                resultSet.getString("name"),
                                resultSet.getString("guiGe"),
                                resultSet.getDouble("price"),
                                resultSet.getString("changyongdaoweo"),
                                resultSet.getString("type"),
                                resultSet.getString("pindu"),
                                resultSet.getString("yongfa")
                        )
                );
            }
            Statement finalStatement = statement;
            connection.setAutoCommit(false);
            beanTmpList.forEach(
                    beanTmp -> {

//                        Medicine medicine;
//                        medicine = medicineService.getMedicineByName(beanTmp.name);
//                        if (medicine == null) {
//                            medicine = new Medicine();
//                        }
//                        medicine.setCategory("");
//                        medicine.setDefaultCompany(new Company(-1L));
//                        medicine.setDefaultCompanyName("未指定药厂");
//                        medicine.setHelpCode(Pinyin.getPinYinHeadChar(beanTmp.name));
//                        medicine.setName(beanTmp.name);
//                        medicine.setStandard(beanTmp.guiGe);
//                        medicine.setType(Medicine.Type.Western);
//                        medicine.setUseMode(beanTmp.yongFa);
//                        medicine.setUsageFlag(false);
//                        medicine.setUseTimes(beanTmp.useTimes);
//                        medicine.setUseQty("饭后服");
//                        medicine.setUseUnit(Medicine.Unit.g);
//                        medicineService.saveMedicine(medicine, new Doctor(53L));

                        try {
                            //finalStatement.executeUpdate("insert into medicine_company values (" + medicine.getId() + "," + medicine
                            //        .getDefaultCompany().getId() + ")");
                            finalStatement.executeUpdate("update medicine_private p set p.type =0  where p.name='" + beanTmp.name + "' AND p.doctor_id=53;");
//                            finalStatement.executeUpdate("insert into medicine_private (" +
//                                    "doctor_id," +
//                                    "medicine_id," +
//                                    "name," +
//                                    "type," +
//                                    "unit," +
//                                    "default_company_id," +
//                                    "default_company_name," +
//                                    "using_time," +
//                                    "use_mode," +
//                                    "use_times," +
//                                    "use_qty," +
//                                    "use_unit," +
//                                    "usage_flag," +
//                                    "category," +
//                                    "help_code," +
//                                    "standard," +
//                                    "price" +
//                                    ") values (" +
//                                    53 + "," +
//                                    medicine.getId() + ",'" +
//                                    medicine.getName() + "'" +
//                                    ",1," +
//                                    "'" + "g" +
//                                    "',"
//                                    + medicine.getDefaultCompany().getId() + ",'"
//                                    + medicine.getDefaultCompanyName() + "'" +
//                                    ",'饭后服'" +
//                                    ",'" + medicine.getUseMode() + "'" +
//                                    ",'" + medicine.getUseTimes() + "'" +
//                                    ",1" +
//                                    ",'" + medicine.getUseUnit().toString() + "'" +
//                                    ",1" +
//                                    ",''" +
//                                    ",'" + Pinyin.getPinYinHeadChar(medicine.getName()) + "'" +
//                                    ",'" + medicine.getStandard()
//                                    + "'," + beanTmp.price +
//                                    ")");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
            connection.commit();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void insertCompany() {
        Connection connection = MySqlutil.getConnect();
        Statement statement = null;
        List<BeanTmp> beanTmpList = Lists.newArrayList();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * FROM tmp_med");
            while (resultSet.next()) {
                beanTmpList.add(
                        new BeanTmp(
                                resultSet.getString("name"),
                                resultSet.getString("changjia"),
                                resultSet.getString("guige")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement finalStatement = statement;
        beanTmpList.forEach(
                beanTmp -> {
                    Company company = new Company();
                    company.setName(beanTmp.changJia);
                    company.setStatus(StatusEntity.Status.Normal);
                    companyService.save(company);
                    try {
                        finalStatement.executeUpdate("update tmp_med set changjiaid = " + company.getId() + " where changjia='" + company.getName()
                                + "'");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
        );
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    class BeanTmp {
        public String medName;
        public String changJia;
        public String guiGe;
        public Long changJiaId;

        public BeanTmp(String medName, String changJia, String guiGe) {
            this.medName = medName;
            this.changJia = changJia;
            this.guiGe = guiGe;

        }

        public BeanTmp(String medName, String changJia, String guiGe, Long changJiaId) {
            this.medName = medName;
            this.changJia = changJia;
            this.guiGe = guiGe;
            this.changJiaId = changJiaId;
        }

        public BeanTmp() {
        }
    }

    class OldMedInfo {
        public String name;
        public String guiGe;
        public Double price;
        public Medicine.Unit danWei;
        public Medicine.Type type;
        public String useTimes;
        public String yongFa;


        public OldMedInfo(String name, String guiGe, Double price, String danWei, String type, String useTimes, String yongFa) {
            this.name = name;
            this.guiGe = guiGe;
            this.price = price;
            this.danWei = Medicine.Unit.valueOf(danWei);
            this.type = Medicine.Type.valueOf(type);
            this.useTimes = useTimes;
            this.yongFa = yongFa;
        }
    }

}
