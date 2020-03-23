package test;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class TestList {
    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<String,String>(10);
        hashMap.put("1","11");
        hashMap.put(null,"12");
        hashMap.put("3","13");
        hashMap.put("4","14");
        hashMap.put("5","15");
        hashMap.put("6","16");
        hashMap.put("7","17");
        hashMap.put("8","18");
    }

    @Test
    public void Test1(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        long startT = System.currentTimeMillis();
        for(int i = 0;i<10000000;i++){
            arrayList.add(i);
        }
        long endT = System.currentTimeMillis();
        System.out.println(endT-startT);

    }
    @Test
    public void Test2(){
        LinkedList<Integer> arrayList = new LinkedList<Integer>();
        long startT = System.currentTimeMillis();
        for(int i = 0;i<10000000;i++){
            arrayList.add(i);
        }
        long endT = System.currentTimeMillis();
        System.out.println(endT-startT);

    }

    @Test
    public void Test3(){
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for(int i = 0;i<10000000;i++){
            linkedList.add(i);
        }
        Random random = new Random();
        long startT = System.currentTimeMillis();
        for (int i = 0;i < 10000;i++){
            linkedList.get(random.nextInt(10000000));
        }
        long endT = System.currentTimeMillis();
        System.out.println(endT-startT);
    }

    @Test
    public void Test4(){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0;i<10000000;i++){
            arrayList.add(i);
        }
        Random random = new Random();
        long startT = System.currentTimeMillis();
        for (int i = 0;i < 10000;i++){
            arrayList.get(random.nextInt(10000000));
        }
        long endT = System.currentTimeMillis();
        System.out.println(endT-startT);
    }


}
