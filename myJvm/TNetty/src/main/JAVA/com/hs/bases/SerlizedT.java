package com.hs.bases;

import java.io.*;

public class SerlizedT {
    public static void main(String[] args) {
        Boolean a = true;
        System.out.println(a);
        FileWriter writer = null;
        File file = new File("./intext.txt");
        try {
            writer = new FileWriter(file,true);
            writer.write("dwqdwqsasd" + a + "\r\n");

        }catch (IOException  e){
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
