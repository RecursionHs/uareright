package com.hs.html;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class AnalyseHtml {
    private static final List<PingP> pLists = new Vector<>();
    public static void main(String[] args) {
        String readString = FileUtil.readString("html.txt", "utf-8");
        List<String> stringList = ReUtil.findAll("\\d*\\.\\d*\\.\\d*\\.\\d*", readString, 0);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        System.out.println("size: " + stringList.size());
        CountDownLatch countDownLatch = new CountDownLatch(stringList.size());
        stringList.forEach((str)->{
            threadPool.execute(()->{
                ping(str);
                countDownLatch.countDown();

            });
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
       List<PingP> pingPS = pLists.stream()
               .filter((p)-> p.getPacketLoss() == 0 ? true : false)
               .sorted(Comparator.comparing(PingP::getAveMs).reversed())
               .collect(Collectors.toList());
        System.out.println("=======================开始排序=====================");
        pingPS.stream()
                .forEach((p)->{
                    System.out.println(p.toString());
                });
    }

    public static void ping(String ip){
        Runtime runtime =Runtime.getRuntime();
        PingP pingP = new PingP();
        Process process = null;
        String line = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        //StringBuffer sb = new StringBuffer();
        try {
            process =runtime.exec("ping " + ip);
            //sb.append(ip);
            pingP.setIP(ip);
            is =process.getInputStream();
            isr = new InputStreamReader(is,"GBK");
            br = new BufferedReader(isr);
            while ((line= br.readLine()) != null) {
                if(line.contains("丢失")) {
                    List<String> ns = ReUtil.findAll("\\((\\d*)", line, 1);
                    if(ns.size() > 0){
                        //sb.append(line);
                        pingP.setPacketLoss(Integer.valueOf(ns.get(0)));
                    }
                }
                if(line.contains("平均")) {
                    List<String> ns = ReUtil.findAll("平均 = (\\d*)ms", line, 1);
                    if(ns.size() > 0){
                        //sb.append(line);
                        pingP.setAveMs(Integer.valueOf(ns.get(0)));
                    }
                }
            }
            is.close();
            isr.close();
            br.close();
            //System.out.println(sb.toString());
            System.out.println(pingP.toString());
            pLists.add(pingP);
        } catch (IOException e) {
            System.out.println(e);
            runtime.exit(1);
        }
    }
}
