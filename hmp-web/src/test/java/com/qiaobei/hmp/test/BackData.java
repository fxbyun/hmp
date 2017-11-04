package com.qiaobei.hmp.test;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by teemoer@cntv.cn on 2016/6/17 0017.
 */
public class BackData {
    public static void main(String[] args) {

        Connection connectionClod = DataBase.getColdConnection();
        Connection connectionXc = DataBase.getXcConnection();

        try {
            Statement statement = connectionXc.createStatement();

            String sqlStr = "SELECT *  FROM prescription " +
                    "WHERE id IN (3,100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, " +
                    "116, 117, 121)";

            ResultSet rs = statement.executeQuery(sqlStr);
            ArrayList<PrescriptionTmp> fuckBeanArrayList = new ArrayList<>();
            StringBuffer prescriptionIds = new StringBuffer();
            while (rs.next()) {
                PrescriptionTmp prescription = new PrescriptionTmp();
                prescription.setId(rs.getLong("id"));
                prescription.setDoctorId(rs.getLong("doctor_id"));
                prescription.setDoctorName(rs.getString("doctor_name"));
                prescription.setCategoryId(rs.getLong("category_id"));
                prescription.setCategoryName(rs.getString("category_name"));
                prescription.setMedicineType(rs.getInt("medicine_type"));
                prescription.setName(rs.getString("name"));
                prescription.setRemark(rs.getString("remark"));
                prescription.setCreateOn(rs.getDate("create_on"));
                prescription.setStatus(rs.getInt("status"));
                prescription.setDiagnosis(rs.getString("diagnosis"));
                if (prescriptionIds.length() < 1) {
                    prescriptionIds.append(prescription.getId());
                } else {
                    prescriptionIds.append(",").append(prescription.getId());
                }
                fuckBeanArrayList.add(prescription);
                System.out.println(prescription.toString());
            }

            System.err.println("--------------------------------------------------------------------");
            String sqlForPrescriptionItemTemp = "select * from prescription_item where prescription_id in (" +
                    prescriptionIds.toString() + ")";
            ArrayList<PrescriptionItemTemp> prescriptionItemTempArrayList = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(sqlForPrescriptionItemTemp);
            while (resultSet.next()) {
                PrescriptionItemTemp prescriptionItemTemp = new PrescriptionItemTemp();
                prescriptionItemTemp.setId(resultSet.getLong("id"));
                prescriptionItemTemp.setPrescriptionId(resultSet.getLong("prescription_id"));
                prescriptionItemTemp.setMedicineId(resultSet.getLong("medicine_id"));
                prescriptionItemTemp.setMedicineName(resultSet.getString("medicine_name"));
                prescriptionItemTemp.setMedicineType(resultSet.getInt("medicine_type"));
                prescriptionItemTemp.setCompanyId(resultSet.getLong("company_id"));
                prescriptionItemTemp.setCompanyName(resultSet.getString("company_name"));
                prescriptionItemTemp.setQty(resultSet.getDouble("qty"));
                prescriptionItemTemp.setRate(resultSet.getInt("rate"));
                prescriptionItemTemp.setUnit(resultSet.getString("unit"));
                prescriptionItemTemp.setCopies(resultSet.getInt("copies"));
                prescriptionItemTemp.setUseMode(resultSet.getString("use_mode"));
                prescriptionItemTemp.setHasUsage(resultSet.getInt("has_usage"));
                prescriptionItemTemp.setUseTimes(resultSet.getString("use_times"));
                prescriptionItemTemp.setUsingTime(resultSet.getString("using_time"));
                prescriptionItemTemp.setUseQty(resultSet.getString("use_qty"));
                prescriptionItemTemp.setUseUnit(resultSet.getString("use_unit"));
                prescriptionItemTempArrayList.add(prescriptionItemTemp);
                System.out.println(prescriptionItemTemp.getUnit());
            }

            resultSet.close();
            rs.close();
            statement.close();
            connectionXc.close();

            String fixInToSql = " insert into prescription (id,status, category_id, category_name, create_on, " +
                    "diagnosis, doctor_id, doctor_name, " +
                    "medicine_type, name, remark) values (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            connectionClod.setAutoCommit(false);

            PreparedStatement preparedStatement = connectionClod.prepareStatement(fixInToSql);
            for (PrescriptionTmp fuckBean : fuckBeanArrayList) {
                preparedStatement.setLong(1, fuckBean.getId());
                preparedStatement.setInt(2, fuckBean.getStatus());
                preparedStatement.setLong(3, fuckBean.getCategoryId());
                preparedStatement.setString(4, fuckBean.getCategoryName());
                preparedStatement.setDate(5, (Date) fuckBean.getCreateOn());
                preparedStatement.setString(6, fuckBean.getDiagnosis());
                preparedStatement.setLong(7, fuckBean.getDoctorId());
                preparedStatement.setString(8, fuckBean.getDoctorName());
                preparedStatement.setInt(9, fuckBean.getMedicineType());
                preparedStatement.setString(10, fuckBean.getName());
                preparedStatement.setString(11, fuckBean.getRemark());
                int exeEnd = preparedStatement.executeUpdate();
                System.out.println("药方:" + fuckBean.getName() + "  ID:" + fuckBean.getId() + " 执行成功");
            }

            String fixInToSqlSunTmp = "insert into prescription_item (id,company_id, company_name, copies, has_usage," +
                    " medicine_id, medicine_name, " +
                    "medicine_type, prescription_id, qty, rate, unit, use_mode, use_qty, use_times, use_unit, " +
                    "using_time) values (?,?, ?, ?, ?, ?," +
                    " ?," +
                    " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatementSun = connectionClod.prepareStatement(fixInToSqlSunTmp);
            for (PrescriptionItemTemp fuckSun : prescriptionItemTempArrayList) {
                preparedStatementSun.setLong(1, fuckSun.getId());
                preparedStatementSun.setLong(2, fuckSun.getCompanyId());
                preparedStatementSun.setString(3, fuckSun.getCompanyName());
                preparedStatementSun.setInt(4, fuckSun.getCopies());
                preparedStatementSun.setInt(5, fuckSun.getHasUsage());
                preparedStatementSun.setLong(6, fuckSun.getMedicineId());
                preparedStatementSun.setString(7, fuckSun.getMedicineName());
                preparedStatementSun.setInt(8, fuckSun.getMedicineType());
                preparedStatementSun.setLong(9, fuckSun.getPrescriptionId());
                preparedStatementSun.setDouble(10, fuckSun.getQty());
                preparedStatementSun.setInt(11, fuckSun.getRate());
                preparedStatementSun.setString(12, fuckSun.getUnit());
                preparedStatementSun.setString(13, fuckSun.getUseMode());
                preparedStatementSun.setString(14, fuckSun.getUseQty());
                preparedStatementSun.setString(15, fuckSun.getUseTimes());
                preparedStatementSun.setString(16, fuckSun.getUseUnit());
                preparedStatementSun.setString(17, fuckSun.getUsingTime());
                int exeSun = preparedStatementSun.executeUpdate();
                System.out.println("药方Id:" + fuckSun.getPrescriptionId() + "  Id:" + fuckSun.getId() + " 执行成功");
            }

            connectionClod.commit();
            preparedStatement.close();
            preparedStatementSun.close();
        } catch (SQLException e) {
            try {
                connectionClod.rollback();
                connectionClod.close();
            } catch (SQLException e1) {
                System.err.println("回滚失败!");
                e1.printStackTrace();
            }
            e.printStackTrace();
        }


        //System.out.println(getCnASCII(cnStr));


    }
}
