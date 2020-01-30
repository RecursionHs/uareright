package com.hs.mlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FlightQuery {

    /**
     * 航空公司的list
     */
    private static  List<String>  flightList = Arrays.asList("海南航空","北京航空","成都航空");

    /**
     * 查询的结果
     */
    private static List<String> resultList = new ArrayList<String>();

    public static void main(String[] args) {
        String startAddress = "Bj";
        String endAddress = "SH";
        
        Thread[] td = new Thread[flightList.size()];
        CountDownLatch latch = new CountDownLatch(flightList.size());

        for (int i = 0; i < td.length; i++) {
            //公司
            String companyName = flightList.get(i);
            System.out.printf("查询%s 公司数据\n",companyName);
            td[i] = new Thread(()->{
                //随机数模拟票数
                int ticket = new Random().nextInt(10);
                System.out.printf("查询%s 到%s 航班数据为%s张\n",startAddress,endAddress,ticket);
                try {
                    TimeUnit.SECONDS.sleep(ticket);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resultList.add(companyName + "--" + ticket);
                latch.countDown();
            });
            td[i].start();
        }
        //数据汇总
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=================查询结果如下==================");
        resultList.forEach(System.out::println);

    }
}
