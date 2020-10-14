package com.hs.exportData;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ExportDataForDb {
    private static final  String PATH = "D:\\TEMP\\";

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Entity> rows = new ArrayList<>(100000);
        try {
            rows = Db.use().query("select * from (" +
                    "select t.*,rownum as row_number_ from P_RT_MEANS_CNT t)" +
                    "where row_number_ > ? and row_number_ <= ?", 1,100000);
            BigExcelWriter writer= ExcelUtil.getBigWriter(PATH + "hutoolExport.xlsx");
            // 一次性写出内容，使用默认样式
            writer.write(rows);
            // 关闭writer，释放内存
            writer.close();
            long endTime = System.currentTimeMillis();
            //391
            System.out.println((endTime-startTime)/1000);



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }

    }
}
