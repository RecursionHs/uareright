package com.dao;

import org.springframework.stereotype.Repository;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Repository
public class IndexDaoImpl implements IndexDao{

    @Override
    public void query(String a) {
        System.out.println("查询数据为...");
        System.out.println(a);
    }

    @Override
    public BlockingQueue query(Integer a) {
        BlockingQueue queue = new LinkedBlockingQueue<Integer>();
        if(a != 0){
            a = a >>> 1;
            for(int i=0;i < a;i++){
                queue.add(i);
            }
        }
        return queue;
    }


}
