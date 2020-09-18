package com.hs.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PoiT {
    static String PATH = "D:\\TEMP\\";

    @Test
    public void poiBase03() throws Exception {
        //工作簿
        Workbook workbook = new HSSFWorkbook();
        //sheet页
        Sheet sheet = workbook.createSheet("HSSFWorkbook");
        //一行
        Row row1 = sheet.createRow(0);
        //一列，
        Cell cell_11 = row1.createCell(0);
        Cell cell_12 = row1.createCell(1);
        cell_11.setCellValue("金额");
        cell_12.setCellValue(29);

        //第二行
        Row row2 = sheet.createRow(1);
        //一列，
        Cell cell_21 = row2.createCell(0);
        Cell cell_22 = row2.createCell(1);
        cell_21.setCellValue("日期");
        String sdate = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell_22.setCellValue(sdate);

        FileOutputStream os = new FileOutputStream(PATH + "HSSFWorkbook.xls");
        workbook.write(os);
        os.close();
        System.out.println("excle生成完毕!");
    }

    @Test
    public void poiBase07() throws Exception {
        //工作簿
        Workbook workbook = new XSSFWorkbook();
        //sheet页
        Sheet sheet = workbook.createSheet("XSSFWorkbook");
        //一行
        Row row1 = sheet.createRow(0);
        //一列，
        Cell cell_11 = row1.createCell(0);
        Cell cell_12 = row1.createCell(1);
        cell_11.setCellValue("金额");
        cell_12.setCellValue(29);

        //第二行
        Row row2 = sheet.createRow(1);
        //一列，
        Cell cell_21 = row2.createCell(0);
        Cell cell_22 = row2.createCell(1);
        cell_21.setCellValue("日期");
        String sdate = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell_22.setCellValue(sdate);

        FileOutputStream os = new FileOutputStream(PATH + "XSSFWorkbook.xlsx");
        workbook.write(os);
        os.close();
        System.out.println("excle生成完毕!");
    }

    @Test
    public void poiBase03BigData () throws Exception{
        long startTime = System.currentTimeMillis();
        //工作簿
        Workbook workbook = new HSSFWorkbook();
        //sheet页
        Sheet sheet = workbook.createSheet("HSSFWorkbook03BigData");

        for (int i = 0; i < 65535; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 20; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(i + j);
            }
        }
        long endTime = System.currentTimeMillis();
        FileOutputStream os = new FileOutputStream(PATH + "HSSFWorkbook03BigData.xls");
        workbook.write(os);
        os.close();
        System.out.println("over." + ((double)(endTime - startTime))/1000);
    }

    @Test
    public void poiBase07BigDataS () throws Exception{
        long startTime = System.currentTimeMillis();
        //工作簿
        Workbook workbook = new SXSSFWorkbook();
        //sheet页
        Sheet sheet = workbook.createSheet("poiBase07BigDataS");

        for (int i = 0; i < 65535; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 20; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(i + j);
            }
        }
        long endTime = System.currentTimeMillis();
        FileOutputStream os = new FileOutputStream(PATH + "poiBase07BigDataS.xlsx");
        workbook.write(os);
        os.close();
        //临时文件清理
        ((SXSSFWorkbook)workbook).dispose();
        System.out.println("over." + ((double)(endTime - startTime))/1000);
    }
}
