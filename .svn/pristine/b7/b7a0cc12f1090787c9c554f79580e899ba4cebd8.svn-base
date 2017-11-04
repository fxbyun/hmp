package com.qiaobei.hmp.test.dataImpl;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.MedicinePrivate;
import com.qiaobei.hmp.modules.entity.QDoctor;
import com.qiaobei.hmp.modules.entity.QMedicinePrivate;
import com.qiaobei.hmp.modules.service.IaiIntoDetailService;
import com.qiaobei.hmp.service.AdvertingService;
import com.qiaobei.hmp.service.CompanyService;
import com.qiaobei.hmp.service.MedicinePrivateService;
import com.qiaobei.hmp.service.MedicineService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA 2016.4
 * 汉化By http://www.java.sx  (凉生  &&  Sky——Pang)
 * User 凉生
 * Date 2016/12/22 0022
 * Time 16:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-junit.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
public class YouXiaoQi {

    @Autowired
    private AdvertingService advertingService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private MedicineService medicineService;
    @Resource
    private MedicinePrivateService medicinePrivateService;
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private IaiIntoDetailService iaiIntoDetailService;

    @Test
    public void getMedPrivateId() {
        Connection connection = MySqlutil.getConnect();
        List<String> medNameList = Lists.newArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NAME  FROM pandianTable  ");
            while (resultSet.next()) {
                medNameList.add(resultSet.getString("name"));
                System.out.println(resultSet.getString("name"));
            }
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


//        medNameList.forEach(System.out::println);
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QMedicinePrivate qMedicinePrivate = QMedicinePrivate.medicinePrivate;
        QDoctor qDoctor = QDoctor.doctor;
        List<TmpObj> errorList = Lists.newArrayList();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        medNameList.forEach(medName -> {
//            List<MedicinePrivate> medicinePrivateList = jpaQueryFactory.from(qMedicinePrivate).select(qMedicinePrivate).
//                    join(qDoctor).on(
//                    qMedicinePrivate.doctor.id.eq(qDoctor.id)
//            ).where(
//                    qMedicinePrivate.name.eq(medName)
//            ).fetch();
            List<MedicinePrivate> medicinePrivateList =
                    medicinePrivateService.
                            findByDoctorAndMedicineName(new Doctor(125L), medName);
            if (medicinePrivateList.size() == 0 || medicinePrivateList.size() > 1) {
                errorList.add(new TmpObj(medName, medicinePrivateList.size()));
            }
            System.out.println(medName);
            try {
                MedicinePrivate medicinePrivate = medicinePrivateList.get(0);
                Statement statement = connection.createStatement();
                int se = statement.executeUpdate(
                        "update pandianTable set medprivate_id = " +
                                "" + medicinePrivate.getId() + ",chang_jia=" + medicinePrivate.getDefaultCompany().getId()
                                + " where name='" + medName + "' ");
                System.out.println(se);

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        errorList.forEach(tmpObj -> System.out.println(tmpObj.name + "---" + tmpObj.size));


    }

    @Test
    @Transactional
    public void inserIaiIntoDetile() {
        Connection connection = MySqlutil.getConnect();
        Connection connectionService = ServiceMySqlutil.getConnect();

        int tm = 0;
        try {
            Statement statement = connection.createStatement();
            connectionService.setAutoCommit(false);
            PreparedStatement serStatement = connectionService.prepareStatement(
                    "insert into " +
                            "iai_into_detail (" +
                            "iai_into_id" +
                            ",med_id" +
                            ",validity_date" +
                            ",company_id" +
                            ",baying_price" +
                            ",total_number" +
                            ",warn_line" +
                            ",status" +
                            ",detail_type" +
                            ",income_quantity" +
                            ")values (?,?,?,?,?,?,?,?,?,?);");

            ResultSet resultSet = statement.executeQuery("select * from pandiantable");
            while (resultSet.next()) {
                serStatement.setInt(1, 110);
                tm = resultSet.getInt("medprivate_id");
                System.out.println(tm);
                serStatement.setInt(2, resultSet.getInt("medprivate_id"));
                serStatement.setDate(3, resultSet.getDate("date"));
                serStatement.setInt(4, resultSet.getInt("chang_jia"));
                serStatement.setDouble(5, resultSet.getDouble("jinhuojia"));
                serStatement.setDouble(6, resultSet.getDouble("shuliang"));
                serStatement.setInt(7, 1000);
                serStatement.setInt(8, 1);
                serStatement.setInt(9, 0);
                serStatement.setDouble(10, resultSet.getDouble("shuliang"));
                int sie = serStatement.executeUpdate();
                System.out.println("sie" + sie);

            }

        } catch (Exception e) {
            try {
                connectionService.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        try {
            connectionService.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    class TmpObj {
        public String name;
        public int size;

        public TmpObj(String name, int size) {
            this.name = name;
            this.size = size;
        }
    }

}
