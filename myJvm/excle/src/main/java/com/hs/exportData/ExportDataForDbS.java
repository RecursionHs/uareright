package com.hs.exportData;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExportDataForDbS {
    private static final String PATH = "D:\\TEMP\\";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        FileOutputStream os = null;
        Workbook workbook = null;

        try {
            List<Entity> title = Db.use().query("select * from P_RT_MEANS_CNT where rownum < ?", 2);
            Set<String> fieldNames = title.get(0).getFieldNames();
            List<Entity> rows = Db.use().query("select * from (" +
                    "select t.*,rownum as row_number_ from P_RT_MEANS_CNT t)" +
                    "where row_number_ > ? and row_number_ <= ?", 1, 100000);
            workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            String FileName = "mdataExport.xlsx";
            os = new FileOutputStream(PATH + FileName);
            for (int j = 0; j < rows.size(); j++) {
                Row row = sheet.createRow(j);
                for (int i = 0; i < fieldNames.size(); i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(rows.get(j).get(fieldNames.toArray()[i]).toString());
                }
            }
            workbook.write(os);
            ((SXSSFWorkbook)workbook).dispose();
            long endTime = System.currentTimeMillis();
            //

            System.out.println((endTime-startTime)/1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                workbook.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}