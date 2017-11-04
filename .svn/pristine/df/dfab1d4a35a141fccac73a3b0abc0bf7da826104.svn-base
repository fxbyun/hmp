package com.qiaobei.hmp.test;

import com.google.common.collect.Lists;
import com.qiaobei.hmp.test.dataImpl.MySqlutil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class poimod {

    public static void main(String[] args) throws Exception {
        poimod po = new poimod();
//        po.redXlsx();
//		po.redXlsx();

    }

    @Test
    public void redXlsx() {
        POIFSFileSystem fs;
        HSSFWorkbook wb = null;
        try {
            fs = new POIFSFileSystem(new FileInputStream(new File("D:\\desktop\\DS\\贾医生数据整理\\药品目录.xls")));
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert wb != null;
        Sheet sheet = wb.getSheetAt(0);
//        int startWhere = sheet.getFirstRowNum();
        int startWhere = 1;
        int endWhere = sheet.getLastRowNum();
        List<BeanTmp> beanTmpList = Lists.newArrayList();
        for (; startWhere < endWhere; startWhere++) {
            String name = sheet.getRow(startWhere).getCell(0).getStringCellValue();
            String home = sheet.getRow(startWhere).getCell(1).getStringCellValue();
            String trye = sheet.getRow(startWhere).getCell(2).getStringCellValue();
            beanTmpList.add(new BeanTmp(name, home, trye));
            System.out.println("药品名称: " + name + "-生产厂家: " + home + "-规格: " + trye);
        }

        Connection connection = MySqlutil.getConnect();
        String inserSql = "INSERT INTO tmp_med (name,changjia,guige) VALUES (?,?,?);";
        PreparedStatement ps = null;

        try {
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(inserSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement finalPs = ps;
        beanTmpList.forEach(
                beanTmp -> {
                    try {
                        assert finalPs != null;
                        finalPs.setString(1, beanTmp.medName);
                        finalPs.setString(2, beanTmp.changJia);
                        finalPs.setString(3, beanTmp.guiGe);
                        finalPs.executeUpdate();
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

    @Test
    public void readXlxsTest() {
        POIFSFileSystem fs;
        HSSFWorkbook wb = null;
        try {
            fs = new POIFSFileSystem(new FileInputStream(new File("C:\\Users\\teemo_000\\Desktop\\桂芝门诊药品目录.xls")));
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert wb != null;
        Sheet sheet = wb.getSheetAt(0);
//        int startWhere = sheet.getFirstRowNum();
        int startWhere = 1;
        int endWhere = sheet.getLastRowNum();
        List<BeanTmp> beanTmpList = Lists.newArrayList();
        int count = 0;
        for (; startWhere < endWhere; startWhere++) {
            String name = sheet.getRow(startWhere).getCell(1).getStringCellValue();
            String home = sheet.getRow(startWhere).getCell(2).getStringCellValue();
            String trye = sheet.getRow(startWhere).getCell(3).getStringCellValue().replace("\'", "");
            String unit = sheet.getRow(startWhere).getCell(4).getStringCellValue();
            Double price = sheet.getRow(startWhere).getCell(5).getNumericCellValue();
//            beanTmpList.add(new BeanTmp(name,home,trye));
//            System.out.println("药品名称:" + name + "--厂家:" + home + " --规格: " + trye + " --单位:" + unit + " --价格:" + price);
            Connection connection = MySqlutil.getConnect();
            try {
//                PreparedStatement ps = connection.prepareStatement("SELECT name FROM" +
//                        " medicine_private WHERE NAME =? AND doctor_id=125 GROUP BY name");
                PreparedStatement ps = connection.prepareStatement("UPDATE medicine_private SET standard =?  WHERE doctor_id=125 AND name=? ;");
                ps.setString(1, trye);
                ps.setString(2, name);
                int resultSet = ps.executeUpdate();
//                if (resultSet.next()) {
//                    count++;
//                    resultSet.getString("name");
////                    System.err.println(resultSet.getString("name") + "--- 已经存在于数据库");
//                } else {
//
//                    System.out.println(name + "--不存在于数据库");
//
//                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        System.out.println(count);
    }

    class BeanTmp {
        public String medName;
        public String changJia;
        public String guiGe;

        public BeanTmp(String medName, String changJia, String guiGe) {
            this.medName = medName;
            this.changJia = changJia;
            this.guiGe = guiGe;
        }

        public BeanTmp() {
        }
    }

}
