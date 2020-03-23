package com.hs.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class queryStringFromPath {
    private static List<File> files = new ArrayList<File>();


    public static void main(String[] args) {
        //放文件夹中的所有文件对象
        File file = new File("G:\\个人文件\\AI面相源代码1.2.1\\AI面相源代码\\hc_face");
        List<File> allFile = getAllFile(file);
        //allFile.stream().map(file1 -> file1.getAbsolutePath()).forEach(System.out::println);
        //读文件查找指定字符串
        for (File f: allFile) {
            try {
                searchString(f,"secret");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

     public static List<File> getAllFile(File file){
         if(file.isDirectory()){
             File[] filesArray = file.listFiles();
             for(File f : filesArray){
                 if(f.isDirectory()){
                     getAllFile(f);
                 }else{
                     files.add(f);
                 }
             }
         }else{
             files.add(file);
         }
         return files;
     }

     public static void searchString(File file,String key)throws IOException {
         List<String> keyList = Arrays.asList("php", "xml", "dat", "bat", "css", "js", "html", "xml");
         String name = file.getName();
         String[] split = name.split("\\.");

         if(split.length > 1){
             //判断是否是指定的类型
             if(keyList.contains(split[1])){
                 FileReader fileReader = new FileReader(file);
                 BufferedReader br = new BufferedReader(fileReader);
                 String s = null;
                 int linNum = 1;
                 while((s = br.readLine()) != null){
                     if(s.indexOf(key) > 0){
                         System.out.println("找到了: " + file.getAbsolutePath()  + "在:" + linNum + "行");
                     }
                     linNum++;
                 }
                 fileReader.close();
             }
         }



     }
}
