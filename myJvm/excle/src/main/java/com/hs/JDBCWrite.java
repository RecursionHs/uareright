package com.hs;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class JDBCWrite {
    static String PATH = "D:\\TEMP\\";

    public static void main(String[] args)  {
        //查询
/*        List<Entity> result = Db.use().query("select * from P_RT_MEANS_CNT where rownum < ?", 10000);
        Set<String> fieldNames = result.get(0).getFieldNames();
        for(Entity entity:result){
            for (int i = 0;i < fieldNames.size();i++){
                System.out.print(entity.get(fieldNames.toArray()[i]) + " | ");
            }
            System.out.println();
        }*/
        FileOutputStream os = null;
        Workbook workbook = null;
        try {
            List<Entity> title = Db.use().query("select * from P_RT_MEANS_CNT where rownum < ?", 2);
            Set<String> fieldNames = title.get(0).getFieldNames();
            os = new FileOutputStream(PATH + "oracleData.xlsx");
            //工作簿
            workbook = new SXSSFWorkbook();
            //sheet页
            Sheet sheet = workbook.createSheet("oracleDateOutput");


            for (int i = 0; i < 10; i++) {
                List<Entity> result = Db.use().query("select * from (" +
                        "select t.*,rownum as row_number_ from P_RT_MEANS_CNT t)" +
                        "where row_number_ > ? and row_number_ <= ?", i*1000,(i + 1)*1000);
                //forToWriteData(result,1000 * i,fieldNames,os,sheet,workbook);
                //forToWriteData(result,1000 * i ,fieldNames,"oracleData.xlsx");
                forToWriteData(result,1000 * i ,fieldNames,"oracleData.xlsx");
                result.clear();
            }
            System.out.println("over!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


        }
    }

    public static void forToWriteData(List<Entity> result, int startRow, Set<String> fieldNames, FileOutputStream os,Sheet sheet,Workbook workbook) throws IOException {
        long startTime = System.currentTimeMillis();


        int maxRowNum = startRow + result.size();
        for(int j = startRow;j < maxRowNum;j++){
            Row row = sheet.createRow(j);
            for(int i = 0;i < fieldNames.size() - 1;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(result.get(j).get(fieldNames.toArray()[i]).toString());
            }
        }


        long endTime = System.currentTimeMillis();
        //FileOutputStream os = new FileOutputStream(PATH + "oracleData.xlsx");
        workbook.write(os);
        //os.close();
        //临时文件清理
        ((SXSSFWorkbook)workbook).dispose();
        System.out.println("over." + ((double)(endTime - startTime))/1000);
    }

    public static void forToWriteData(List<Entity> result, int startRow, Set<String> fieldNames, FileOutputStream os) throws IOException {
        long startTime = System.currentTimeMillis();

        //工作簿
        Workbook workbook = new SXSSFWorkbook();
        //sheet页
        Sheet sheet = workbook.createSheet("oracleDateOutput");


        int maxRowNum = startRow + result.size();
        for(int j = startRow;j < maxRowNum ;j++){
            Row row = sheet.createRow(j);
            for(int i = 0;i < fieldNames.size() - 1;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(result.get(j - startRow).get(fieldNames.toArray()[i]).toString());
            }
        }


        long endTime = System.currentTimeMillis();
        //FileOutputStream os = new FileOutputStream(PATH + "oracleData.xlsx");
        workbook.write(os);
        //os.close();
        //临时文件清理
        ((SXSSFWorkbook)workbook).dispose();
        System.out.println("over." + ((double)(endTime - startTime))/1000);
    }

    public static void forToWriteData(List<Entity> result, int startRow, Set<String> fieldNames,String FileName) throws IOException {
        long startTime = System.currentTimeMillis();

        //工作簿
        Workbook workbook = new SXSSFWorkbook();
        //sheet页
        Sheet sheet = workbook.createSheet("oracleDateOutput");
        FileOutputStream os = new FileOutputStream(PATH + FileName);


        int maxRowNum = startRow + result.size();
        for(int j = startRow;j < maxRowNum ;j++){
            Row row = sheet.createRow(j);
            for(int i = 0;i < fieldNames.size() - 1;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(result.get(j - startRow).get(fieldNames.toArray()[i]).toString());
            }
        }


        long endTime = System.currentTimeMillis();
        //FileOutputStream os = new FileOutputStream(PATH + "oracleData.xlsx");
        workbook.write(os);
        os.close();
        //临时文件清理
        ((SXSSFWorkbook)workbook).dispose();
        System.out.println("over." + ((double)(endTime - startTime))/1000);
    }
}
